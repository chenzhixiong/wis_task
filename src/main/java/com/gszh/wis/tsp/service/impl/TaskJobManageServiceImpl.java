package com.gszh.wis.tsp.service.impl;

import com.gszh.wis.tsp.dao.TaskJobCronDAO;
import com.gszh.wis.tsp.dao.TaskJobParamDAO;
import com.gszh.wis.tsp.dao.TaskJobStateHistoryDAO;
import com.gszh.wis.tsp.listener.AllJobListener;
import com.gszh.wis.tsp.listener.AllTriggerListener;
import com.gszh.wis.tsp.listener.MySchedulerListener;
import com.gszh.wis.tsp.model.*;
import com.gszh.wis.tsp.service.TaskJobManageService;
import com.gszh.wis.tsp.service.TaskJobStateService;
import com.gszh.wis.tsp.task.base.ControllableJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private TaskJobStateHistoryDAO taskJobStateHistoryDAO;
    @Autowired
    private TaskJobParamDAO taskJobParamDAO;

    /**
     * 注册监听器
     */
    @Override
    public void regeistListener(TaskJobStateService taskJobStateService) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            //清空定时任务队列，重新从数据库中读取任务列表
            scheduler.clear();
            scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());
            scheduler.getListenerManager().addJobListener(new AllJobListener());
            scheduler.getListenerManager().addTriggerListener(new AllTriggerListener(taskJobStateService));
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
        List<TaskJobCron> list = this.taskJobCronDAO.getAll();
        //清空 qrtz_* 的表
        this.taskJobCronDAO.clearDB();
        if (list != null && list.size() > 0) {
            for (TaskJobCron po : list) {
                if (po.getIfBoot() == 1)
                    addCronTaskToScheduler(po);
            }
        }
    }

    /**
     * 根据任务key开启指定任务
     *
     * @param po
     */
    @Override
    public void startCronJob(TaskJobCron po) {
        List<TaskJobCron> list = this.taskJobCronDAO.getTask(po);
        if (list.size() == 1) {
            addCronTaskToScheduler(list.get(0));
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
    public void addCronJob(TaskJobCron po) {
        TaskJobCron param = new TaskJobCron();
        param.setJobName(po.getJobName());
        param.setJobGroup(po.getJobGroup());
        List<TaskJobCron> list = this.taskJobCronDAO.getTask(param);
        if (list.size() == 0) {
            addCronTaskToScheduler(po);
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
    public void updateCronJob(TaskJobCron po) {
        List<TaskJobCron> list = this.taskJobCronDAO.getTask(po);
        if (list.size() == 1) {
            addCronTaskToScheduler(po);
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
    public void pauseCronJob(TaskJobCron po) {
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
    public void resumeCronJob(TaskJobCron po) {
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
    public void deleteCronJob(TaskJobCron po) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            pauseCronJob(po);
            scheduler.deleteJob(JobKey.jobKey(po.getJobName(), po.getJobGroup()));
            int count = this.taskJobCronDAO.delete(po);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停实例
     *
     * @param instanceNo
     */
    @Override
    public void pasueInstance(String instanceNo) {
        workInstance(instanceNo, 0);
    }

    /**
     * 恢复实例
     *
     * @param instanceNo
     */
    @Override
    public void resumeInstance(String instanceNo) {
        workInstance(instanceNo, 1);
    }

    /**
     * 停止实例
     *
     * @param instanceNo
     */
    @Override
    public void stopInstance(String instanceNo) {
        workInstance(instanceNo, 2);
    }

    /**
     * 添加事件任务
     *
     * @param po
     */
    @Override
    public void addEventJob(TaskJobEvent po) {

    }

    /**
     * 启动事件任务
     *
     * @param po
     */
    @Override
    public void startEventJob(TaskJobEvent po) {

    }

    /**
     * 修改事件任务
     *
     * @param po
     */
    @Override
    public void updateEventJob(TaskJobEvent po) {

    }

    /**
     * 删除事件任务
     *
     * @param po
     */
    @Override
    public void deleteEventJob(TaskJobEvent po) {

    }


    /**
     * scheduler 注册 cron 任务并启动
     *
     * @param po
     */
    public void addCronTaskToScheduler(TaskJobCron po) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.start();
            JobDataMap parameters = new JobDataMap();
            if ("0".equals(po.getJobClass().trim())) {
                po.setJobClass(jobPoolMangage());
            } else if ("1".equals(po.getJobClass().trim())) {
                po.setJobClass(StaticClass.parallel);
            } else {
                logger.error("未设置任务载体！");
                return;
            }

            //获取实体类参数
            parameters = getJobParam(po);
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
                        .newJob((Class<? extends Job>) Class.forName(po.getJobClass()))
                        .withIdentity(jobKey)
                        .usingJobData("entityClass", po.getEntityClass())
                        .usingJobData("jobFile", po.getJobFile())
                        .usingJobData("jobChName", po.getJobChName())
                        .usingJobData("username", po.getUsername())
                        .usingJobData("password", po.getPassword())
                        .build();
                if (parameters != null && !parameters.isEmpty()) {
                    jobDetail.getJobDataMap().putAll(parameters);
                }
            } else {
                jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(po.getJobClass()))
                        .withIdentity(jobKey)
                        .usingJobData("entityClass", po.getEntityClass())
                        .usingJobData("jobFile", po.getJobFile())
                        .usingJobData("jobChName", po.getJobChName())
                        .usingJobData("username", po.getUsername())
                        .usingJobData("password", po.getPassword())
                        .build();
                if (parameters != null && !parameters.isEmpty()) {
                    jobDetail.getJobDataMap().putAll(parameters);
                }
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
     * 对任务实例的操作（线程）
     *
     * @param instanceNo
     * @param flag
     */
    private void workInstance(String instanceNo, int flag) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TaskJobStateHistory po = new TaskJobStateHistory();
        po.setInstanceNo(instanceNo);
        List<TaskJobStateHistory> list = this.taskJobStateHistoryDAO.getStateHistory(po);
        if (list != null && list.size() > 0) {
            po = list.get(0);
        }
        try {
            //判断 实例是否存在
            if (po != null && po.getJobName() != null && po.getJobGroup() != null
                    && !"".equals(po.getJobName().trim()) && !"".equals(po.getJobGroup().trim())) {
                JobKey key = JobKey.jobKey(po.getJobName(), po.getJobGroup());
                Long fireTimeLong = po.getFireTimeLong();
                List<JobExecutionContext> jobs = scheduler.getCurrentlyExecutingJobs();
                System.out.println(jobs);
                JobDetail jobDetail = null;
                Job job = null;
                //遍历正在运行的实例，查找 instanceNo 对应的实例
                for (JobExecutionContext jec : jobs) {
                    jobDetail = jec.getJobDetail();
                    System.out.println(jec.getFireTime().getTime());
                    System.out.println(fireTimeLong);
                    if (key.equals(jobDetail.getKey()) && jec.getFireTime().getTime() - fireTimeLong < 1000) {
                        job = jec.getJobInstance();
                        if (job instanceof ControllableJob) {
                            switch (flag) {
                                case 0:
                                    ((ControllableJob) job).pasueInstance();
                                    break;
                                case 1:
                                    ((ControllableJob) job).resumeInstance();
                                    break;
                                case 2:
                                    ((ControllableJob) job).stopInstance();
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            throw new UnableToInterruptJobException(
                                    "Job " + jobDetail.getKey() +
                                            " can not be controlled, it did not implemented the interface ControllableJob " +
                                            InterruptableJob.class.getName());
                        }
                    }
                }
            } else {
                logger.error("The instance of instanceNo(" + instanceNo + ") is not exist!");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询任务类的参数
     *
     * @param po
     * @return
     */
    private JobDataMap getJobParam(TaskJobCron po) {
        JobDataMap parameters = new JobDataMap();
        TaskJobParam paramPo = new TaskJobParam(po.getJobName(), po.getJobGroup());
        List<TaskJobParam> list = this.taskJobParamDAO.getJobParam(paramPo);
        if (list != null && list.size() > 0) {
            for (TaskJobParam param : list) {
                parameters.put(param.getParamName(), param.getParamValue());
            }
        }
        return parameters;
    }

    /**
     * 分配串行化任务执行类
     *
     * @return
     */
    private String jobPoolMangage() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<String> jobList = new ArrayList<String>();
        List<String> poolList = new ArrayList<String>();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.<JobKey>anyGroup());
            for (JobKey jobKey : jobKeys) {
                String jobClass = scheduler.getJobDetail(jobKey).getJobClass().getName();
                jobList.add(jobClass);
            }
            poolList.addAll(StaticClass.poolList);
            jobList = getIntersection(poolList, jobList);
            poolList.removeAll(jobList);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return poolList.get(0);
    }

    /**
     * 求余 bList%aList
     *
     * @param aList
     * @param bList
     * @return
     */
    private List<String> getIntersection(List<String> aList, List<String> bList) {
        List<String> cList = new ArrayList<String>();
        //清除 bList 中与 aList 无关的元素
        cList.addAll(bList);
        bList.removeAll(aList);
        cList.removeAll(bList);
        bList.clear();
        bList.addAll(cList);
        //遍历 bList 每次去除一组与 aList 相同的元素
        while (bList.size() > aList.size()) {
            for (String a : aList) {
                for (int i = 0; i < bList.size(); i++) {
                    if (bList.get(i).equals(a)) {
                        bList.remove(i);
                        break;
                    }
                }
            }
        }
        return bList;
    }

}
