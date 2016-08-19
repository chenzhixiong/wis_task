package quartz.service;

import quartz.model.TaskJobCron;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/7/28.
 */
public interface TaskJobCronService {
    /**
     * 查询 cron定时任务列表
     * @return
     */
    List<TaskJobCron> getAll();
}
