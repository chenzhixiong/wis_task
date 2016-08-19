package quartz.service;

import quartz.model.TaskJobCron;

/**
 * Created by chenzhixiong on 2016/8/11.
 */
public interface TaskJobManageService {

    /**
     * 注册监听器
     */
    public void regeistListener();

    /**
     * 开启全部定时器(开机自启的定时器)
     */
    void startAll();

    /**
     * 根据任务key开启指定任务
     * @param po
     */
    void startOne(TaskJobCron po);

    /**
     * 添加任务
     * @param po
     */
    void addOne(TaskJobCron po);

    /**
     * 修改任务
     * @param po
     */
    void updateOne(TaskJobCron po);

    /**
     * 根据任务key暂停指定任务
     * @param po
     */
    void pauseOne(TaskJobCron po);

    /**
     * 根据任务key恢复指定任务运行（若是任务本身不是暂停状态，那么本操作没有任何效果）
     * @param po
     */
    void resumeOne(TaskJobCron po);

    /**
     * 根据任务key删除指定任务
     * @param po
     */
    void deleteOne(TaskJobCron po);

    /**
     * scheduler 注册任务
     */
    void addTaskToScheduler(TaskJobCron po);
}
