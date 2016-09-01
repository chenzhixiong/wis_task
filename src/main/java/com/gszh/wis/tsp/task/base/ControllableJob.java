package com.gszh.wis.tsp.task.base;

import org.quartz.Job;

/**
 * Created by chenzhixiong on 2016/8/29.
 */
public interface ControllableJob extends Job {

    /**
     * 暂停实例
     */
    void pasueInstance();

    /**
     * 恢复实例
     */
    void resumeInstance();

    /**
     * 停止实例
     */
    void stopInstance();
}
