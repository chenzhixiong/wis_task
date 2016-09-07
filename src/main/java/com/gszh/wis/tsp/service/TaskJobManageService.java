package com.gszh.wis.tsp.service;

import com.gszh.wis.tsp.model.TaskJobCron;
import com.gszh.wis.tsp.model.TaskJobEvent;
import org.quartz.JobKey;

import java.util.Date;

/**
 * Created by chenzhixiong on 2016/8/11.
 */
public interface TaskJobManageService {

    /**
     * 注册监听器
     */
    public void regeistListener(TaskJobStateService taskJobStateService);

    /**
     * 开启全部定时器(开机自启的定时器)
     */
    void startAll();

    /**
     * 根据任务key开启指定任务
     * @param po
     */
    void startCronJob(TaskJobCron po);

    /**
     * 添加任务
     * @param po
     */
    void addCronJob(TaskJobCron po);

    /**
     * 修改任务
     *
     * @param po
     */
    void updateCronJob(TaskJobCron po);

    /**
     * 根据任务key暂停指定任务
     * @param po
     */
    void pauseCronJob(TaskJobCron po);

    /**
     * 根据任务key恢复指定任务运行（若是任务本身不是暂停状态，那么本操作没有任何效果）
     * @param po
     */
    void resumeCronJob(TaskJobCron po);

    /**
     * 根据任务key删除指定任务
     * @param po
     */
    void deleteCronJob(TaskJobCron po);

//    /**
//     * scheduler 注册任务
//     */
//    void addCronTaskToScheduler(TaskJobCron po);

    /**
     * 暂停实例
     * @param instanceNo
     */
    void pasueInstance(String instanceNo);

    /**
     * 恢复实例
     * @param instanceNo
     */
    void resumeInstance(String instanceNo);

    /**
     * 停止实例
     * @param instanceNo
     */
    void stopInstance(String instanceNo);

    /**
     * 添加事件任务
     * @param po
     */
    void addEventJob(TaskJobEvent po);
    /**
     * 启动事件任务
     * @param po
     */
    void startEventJob(TaskJobEvent po);
    /**
     * 修改事件任务
     * @param po
     */
    void updateEventJob(TaskJobEvent po);
    /**
     * 删除事件任务
     * @param po
     */
    void deleteEventJob(TaskJobEvent po);

}
