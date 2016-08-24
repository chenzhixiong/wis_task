package com.gszh.wis.quartz.listener;

import com.gszh.wis.quartz.dao.TaskJobStateDAO;
import com.gszh.wis.quartz.model.StaticValue;
import com.gszh.wis.quartz.model.TaskJobState;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/12.
 */
public class AllTriggerListener implements TriggerListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private TaskJobStateDAO taskJobStateDAO;
//    private TaskJobState po;
//    private TaskJobState poRely;

    public AllTriggerListener(TaskJobStateDAO taskJobStateDAO) {
        this.taskJobStateDAO = taskJobStateDAO;
    }

    public String getName() {
        return "AllTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        logger.info(trigger.getKey() + " fired!");
        insetState(trigger, StaticValue.TRIGGER_FIRED_NORMAL);
        JobDataMap jobDataMap = trigger.getJobDataMap();
        int isRelyOn = (Integer) jobDataMap.get(trigger.getKey() + "isRelyOn");
        if (isRelyOn == 0) {
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
        insetState(trigger, StaticValue.TRIGGER_FIRED_EXCEPTION);
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        logger.info(trigger.getKey() + " complete!");
        insetState(trigger, StaticValue.TRIGGER_FIRED_COMPLETE);
    }

    /**
     * 记录触发器的变更信息
     *
     * @param trigger
     * @param state
     */
    private void insetState(Trigger trigger, String state) {
        TaskJobState po = new TaskJobState();
        po.setJobName(trigger.getKey().getName());
        po.setJobGroup(trigger.getKey().getGroup());
        po.setFireTime(trigger.getPreviousFireTime());
        po.setJobState(state);
        int i = this.taskJobStateDAO.insert(po);
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
            insetState(trigger, StaticValue.TRIGGER_FIRED_WAITING);

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
            TaskJobState poRely = new TaskJobState();
            //遍历依赖任务，等待依赖任务执行
            for (TriggerKey key : triggerKeys) {
                boolean flag = true;
                Integer relyWaitTime = jobDataMap.getInt(trigger.getKey() + "relyWaitTime");
                //依赖任务信息补充
                poRely.setJobName(key.getName());
                poRely.setJobGroup(key.getGroup());
                poRely.setJobState(StaticValue.TRIGGER_FIRED_COMPLETE);
                poRely.setFireTime(trigger.getPreviousFireTime());
                poRely.setRecordTime(new Date(trigger.getPreviousFireTime().getTime() - (trigger.getNextFireTime().getTime() - trigger.getPreviousFireTime().getTime())));

                //依赖任务执行完成或等待超时，将跳出 while 循环
                while (true) {
                    try {
                        //查询依赖任务是否执行
                        if (this.taskJobStateDAO.getStateCount(poRely) == 1) {
                            flag = false;
                            break;
                        }
                        relyWaitTime--;
                        if (relyWaitTime == -1) {
                            logger.error(key + " 等待超时！");
                            insetState(trigger,StaticValue.TRIGGER_FIRED_WAITING_OUT+key);
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
