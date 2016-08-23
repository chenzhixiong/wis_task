package com.gszh.wis.quartz.service.impl;

import com.gszh.wis.quartz.dao.TaskJobCronDAO;
import com.gszh.wis.quartz.dao.TaskJobStateDAO;
import com.gszh.wis.quartz.listener.AllJobListener;
import com.gszh.wis.quartz.listener.AllTriggerListener;
import com.gszh.wis.quartz.listener.MySchedulerListener;
import com.gszh.wis.quartz.model.TaskJobCron;
import com.gszh.wis.quartz.service.TaskJobManageService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/11.
 */
@Service
public class TaskJobManageServiceImpl implements TaskJobManageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskJobCronDAO taskJobCronDAO;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private TaskJobStateDAO taskJobStateDAO;


    /**
     * 注册监听器
     */
    @Override
    public void regeistListener() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());
            scheduler.getListenerManager().addJobListener(new AllJobListener());
            scheduler.getListenerManager().addTriggerListener(new AllTriggerListener(taskJobStateDAO));
            logger.info("Regeist all Listeners!");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启全部定时器(开机自启的定时器)
     */
    @Override
    public void startAll() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            //清空定时任务队列，重新从数据库中读取任务列表
            scheduler.clear();
            List<TaskJobCron> list = this.taskJobCronDAO.getAll();
            if (list != null && list.size() > 0) {
                for (TaskJobCron po : list) {
                    if (po.getIfBoot() == 0)
                        addTaskToScheduler(po);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据任务key开启指定任务
     *
     * @param po
     */
    @Override
    public void startOne(TaskJobCron po) {
        List<TaskJobCron> list = this.taskJobCronDAO.getTask(po);
        if (list.size() == 1) {
            addTaskToScheduler(list.get(0));
        } else {
            logger.error("该任务不存在！");
        }
    }

    /**
     * 添加任务
     *
     * @param po
     */
    @Override
    public void addOne(TaskJobCron po) {
        TaskJobCron param = new TaskJobCron();
        param.setJobName(po.getJobName());
        param.setJobGroup(po.getJobGroup());
        List<TaskJobCron> list = this.taskJobCronDAO.getTask(param);
        if (list.size() == 0) {
            addTaskToScheduler(po);
            int count = this.taskJobCronDAO.insert(po);
        } else {
            logger.error("该任务key已存在！");
        }
    }

    /**
     * 修改任务
     *
     * @param po
     */
    @Override
    public void updateOne(TaskJobCron po) {
        List<TaskJobCron> list = this.taskJobCronDAO.getTask(po);
        if (list.size() == 1) {
            addTaskToScheduler(po);
            int count = this.taskJobCronDAO.update(po);
        } else {
            logger.error("修改失败");
        }
    }

    /**
     * 根据任务key暂停指定任务
     *
     * @param po
     */
    @Override
    public void pauseOne(TaskJobCron po) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(po.getJobName(), po.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据任务key恢复指定任务运行（若是任务本身不是暂停状态，那么本操作没有任何效果）
     *
     * @param po
     */
    @Override
    public void resumeOne(TaskJobCron po) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.resumeTrigger(TriggerKey.triggerKey(po.getJobName(), po.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据任务key删除指定任务
     *
     * @param po
     */
    @Override
    public void deleteOne(TaskJobCron po) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            pauseOne(po);
            scheduler.deleteJob(JobKey.jobKey(po.getJobName(), po.getJobGroup()));
            int count = this.taskJobCronDAO.delete(po);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * scheduler 注册任务并启动
     *
     * @param po
     */
    @Override
    public void addTaskToScheduler(TaskJobCron po) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            JobDataMap parameters = new JobDataMap();
            //判断实体类是否有参数
//            if (po.getParameters() != null && !"".equals(po.getParameters().trim())) {
//                parameters = putJobDataMap(po.getParameters());
//            }
            /*创建执行任务，可设置如下参数
            * 1：JobKey 任务标识
            * 2：entityClass 任务执行类
            * 3：isRelyOn 是否存在依赖
            * 4：relyOn 所依赖的其他任务标识
            * 5：relyWaitTime 允许依赖等待时长*/
            JobDetail jobDetail = null;
            JobKey jobKey = JobKey.jobKey(po.getJobName(), po.getJobGroup());
            //若任务已存在，则更新配置，若不存在，则创建一个
            if (scheduler.checkExists(jobKey)) {
                jobDetail = scheduler.getJobDetail(jobKey).getJobBuilder()
                        .newJob((Class<? extends Job>) Class.forName(po.getEntityClass()))
                        .withIdentity(jobKey)
                        .build();
            } else {
                jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(po.getEntityClass()))
                        .withIdentity(jobKey)
                        .build();
            }

            /*设置 cronExpression 表达式*/
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(po.getCronExpression());
            /* misfire 策略选择，即错过触发时间后，任务的后续执行方式
            0：等待下一触发时间点；
            1：保证应由的执行次数后，在按照预设的时间点执行；
            2：立即执行一次，然后等待下一触发时间点*/
            if (po.getMisfire() == 0) {
                cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
            } else if (po.getMisfire() == 1) {
                cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
            } else if (po.getMisfire() == 2) {
                cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
            }

            /*创建触发器，可设置如下参数
            * 1：priority 不同触发器同一时刻触发，优先触发的权重，1-10，越大越优先
            * 2：startTime 任务启动时间
            * 3：endTime 任务结束时间*/
            CronTrigger trigger = null;
            TriggerKey triggerKey = TriggerKey.triggerKey(po.getJobName(), po.getJobGroup());
            //若触发器已存在，则更新配置，若不存在，则创建一个
            if (scheduler.checkExists(triggerKey)) {
                trigger = scheduler.getTrigger(triggerKey).getTriggerBuilder().newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(cronScheduleBuilder)
                        .withPriority(po.getPriority())
                        .usingJobData(jobKey + "isRelyOn", po.getIsRelyOn())
                        .usingJobData(jobKey + "relyOn", po.getRelyOn())
                        .usingJobData(jobKey + "relyWaitTime", po.getRelyWaitTime())
                        .startAt(po.getStartTime() != null ? po.getStartTime() : new Date())
                        .endAt(po.getEndTime())
                        .build();
                if(parameters!=null&&!parameters.isEmpty()){
                    trigger.getJobDataMap().putAll(parameters);
                }
            } else {
                trigger = (CronTrigger) TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(cronScheduleBuilder)
                        .withPriority(po.getPriority())
                        .usingJobData(jobKey + "isRelyOn", po.getIsRelyOn())
                        .usingJobData(jobKey + "relyOn", po.getRelyOn())
                        .usingJobData(jobKey + "relyWaitTime", po.getRelyWaitTime())
                        .startAt(po.getStartTime() != null ? po.getStartTime() : new Date())
                        .endAt(po.getEndTime())
                        .build();
                if(parameters!=null&&!parameters.isEmpty()){
                    trigger.getJobDataMap().putAll(parameters);
                }
            }

            //任务注册到调度程序中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将形如：key:value;key:value 的字符串转为 JobDataMap
     * @param po
     * @return
     */
    private JobDataMap putJobDataMap(String po) {
        JobDataMap parameters = new JobDataMap();
        String[] params = po.split(";");
        if (params != null && params.length!=0) {
            for (String param : params) {
                String[] pa = param.split(":");
                parameters.put(pa[0], pa[1]);
            }
        }
        return parameters;
    }
}
