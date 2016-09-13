package com.gszh.wis.tsp.dao;

import com.gszh.wis.tsp.model.TaskJobCron;
import com.gszh.wis.tsp.model.TaskJobEvent;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/2.
 */
public interface TaskJobCronDAO {

    /**
     * 查询 cron定时任务 列表
     *
     * @return
     */
    List<TaskJobCron> getAllCronJob();

    /**
     * 查询指定的 cron定时任务 信息
     *
     * @param po
     * @return
     */
    List<TaskJobCron> getCronJob(TaskJobCron po);

    /**
     * 添加一个新的任务
     *
     * @param po
     * @return
     */
    int insertCronJob(TaskJobCron po);

    /**
     * 更新任务配置
     *
     * @param po
     * @return
     */
    int updateCronJob(TaskJobCron po);

    /**
     * 删除任务
     *
     * @param po
     * @return
     */
    int deleteCronJob(TaskJobCron po);

    /**
     * 存储过程：清空 qrtz_* 的表
     */
    void clearDB();

    /**
     * 查询事件任务配置
     * @param po
     * @return
     */
    List<TaskJobEvent> getEvent(TaskJobEvent po);

    /**
     * 插入事件任务配置
     * @param po
     * @return
     */
    int insertEvent(TaskJobEvent po);

    /**
     * 更新事件任务配置
     * @param po
     * @return
     */
    int updateEvent(TaskJobEvent po);

    /**
     * 删除事件任务配置
     * @param po
     * @return
     */
    int deleteEvent(TaskJobEvent po);
}