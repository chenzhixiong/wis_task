package com.gszh.wis.tsp.listener;

import com.gszh.wis.tsp.model.StaticClass;
import com.gszh.wis.tsp.model.TaskJobState;
import com.gszh.wis.tsp.model.TaskJobStateHistory;
import com.gszh.wis.tsp.service.TaskJobStateService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/12.
 */
public class AllTriggerListener implements TriggerListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private TaskJobStateService taskJobStateService;

    private TaskJobStateHistory po = new TaskJobStateHistory();
    private TaskJobStateHistory poRely = new TaskJobStateHistory();
    private TaskJobState tj = new TaskJobState();

    public AllTriggerListener(TaskJobStateService taskJobStateService) {
        this.taskJobStateService=taskJobStateService;
    }

    public String getName() {
        return "AllTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        logger.info(trigger.getKey() + " fired!");
        //插入任务实例状态
        insertState(trigger, StaticClass.TRIGGER_FIRED_NORMAL);
        insertStateHistory(trigger, StaticClass.TRIGGER_FIRED_NORMAL);

        JobDataMap jobDataMap = trigger.getJobDataMap();
        int isRelyOn = (Integer) jobDataMap.get(trigger.getKey() + "isRelyOn");
        if (isRelyOn > 0) {
            relyWait(trigger, context, jobDataMap);
        }
    }


    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        logger.error(trigger.getKey() + " misfired");
        insertStateHistory(trigger, StaticClass.TRIGGER_FIRED_EXCEPTION);
        updateState(trigger, StaticClass.TRIGGER_FIRED_EXCEPTION);
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        insertStateHistory(trigger, StaticClass.TRIGGER_FIRED_COMPLETE);
        updateState(trigger, StaticClass.TRIGGER_FIRED_COMPLETE);
        logger.info(trigger.getKey() + " complete!");
    }

    /**
     * 记录触发器的变更历史
     *
     * @param trigger
     * @param state
     */
    private void insertStateHistory(Trigger trigger, String state) {
//        TaskJobState po = new TaskJobState();
        po.setJobName(trigger.getKey().getName());
        po.setJobGroup(trigger.getKey().getGroup());
        po.setFireTimeLong(trigger.getPreviousFireTime().getTime());
        po.setJobState(state);
        po.setInstanceNo(trigger.getKey() + new Long(trigger.getPreviousFireTime().getTime()).toString());
        int i = this.taskJobStateService.insertHistory(po);
    }

    /**
     * 记录触发器的运行状态
     *
     * @param trigger
     * @param state
     */
    private void insertState(Trigger trigger, String state) {
        tj.setJobName(trigger.getKey().getName());
        tj.setJobGroup(trigger.getKey().getGroup());
        tj.setFireTimeLong(trigger.getPreviousFireTime().getTime());
        tj.setJobState(state);
        tj.setInstanceNo(trigger.getKey() + new Long(trigger.getPreviousFireTime().getTime()).toString());
        int i = this.taskJobStateService.insertState(tj);
    }

    /**
     * 更新触发器的运行状态
     *
     * @param trigger
     * @param state
     */
    private void updateState(Trigger trigger, String state) {
        tj.setJobState(state);
        tj.setInstanceNo(trigger.getKey() + new Long(trigger.getPreviousFireTime().getTime()).toString());
        int i = this.taskJobStateService.updateState(tj);
    }

    /**
     * 依赖等待
     *
     * @param trigger
     * @param context
     * @param jobDataMap
     */
    private void relyWait(Trigger trigger, JobExecutionContext context, JobDataMap jobDataMap) {
        try {
            //存在依赖，暂停任务，并记录
            context.getScheduler().pauseTrigger(trigger.getKey());
            insertStateHistory(trigger, StaticClass.TRIGGER_FIRED_WAITING);

            //读取依赖任务信息，以及任务配置

            String relyOn = jobDataMap.getString(trigger.getKey() + "relyOn");
            String[] relyArray = relyOn.split(";");
            List<TriggerKey> triggerKeys = new ArrayList<TriggerKey>();
            for (String rely : relyArray) {
                if (rely != null && !"".trim().equals(rely)) {
                    TriggerKey triggerKey = TriggerKey.triggerKey(rely.split("\\.")[0], rely.split("\\.")[1]);
                    triggerKeys.add(triggerKey);
                }
            }
//            TaskJobState poRely = new TaskJobState();
            //遍历依赖任务，等待依赖任务执行
            for (TriggerKey key : triggerKeys) {
                boolean flag = true;
                Integer relyWaitTime = jobDataMap.getInt(trigger.getKey() + "relyWaitTime");
                //依赖任务信息补充
                poRely.setJobName(key.getName());
                poRely.setJobGroup(key.getGroup());
                poRely.setJobState(StaticClass.TRIGGER_FIRED_COMPLETE);
                poRely.setLarger(trigger.getPreviousFireTime().getTime());
//                poRely.setRecordTime(new Date(trigger.getPreviousFireTime().getTime() - (trigger.getNextFireTime().getTime() - trigger.getPreviousFireTime().getTime())));
                poRely.setSmaller(context.getPreviousFireTime().getTime());
                //依赖任务执行完成或等待超时，将跳出 while 循环
                while (true) {
                    try {
                        //查询依赖任务是否执行
                        if (this.taskJobStateService.getStateHistoryCount(poRely) == 1) {
                            flag = false;
                            break;
                        }
                        relyWaitTime--;
                        if (relyWaitTime == -1) {
                            logger.error(key + " 等待超时！");
                            insertStateHistory(trigger, StaticClass.TRIGGER_FIRED_WAITING_OUT + key);
                            break;
                        }
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            context.getScheduler().resumeTrigger(trigger.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        } finally {
            //多线程中，告知 C3p0 操作结束，停止 logback 日志向缓冲区输出任何内容
            //如果没做这个响应将会导致日志异常打印
            return;
        }
    }
}
