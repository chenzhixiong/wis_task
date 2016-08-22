package com.gszh.wis.quartz.dao;

import com.gszh.wis.quartz.model.TaskJobCron;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/2.
 */
public interface TaskJobCronDAO {

    /**
     * 查询 cron定时任务 列表
     * @return
     */
    List<TaskJobCron> getAll();

    /**
     * 查询指定的 cron定时任务 信息
     * @param po
     * @return
     */
    List<TaskJobCron> getTask(TaskJobCron po);

    /**
     * 添加一个新的任务
     * @param po
     * @return
     */
    int insert(TaskJobCron po);

    /**
     * 更新任务配置
     * @param po
     * @return
     */
    int update(TaskJobCron po);

    /**
     * 删除任务
     * @param po
     * @return
     */
    int delete(TaskJobCron po);
}
