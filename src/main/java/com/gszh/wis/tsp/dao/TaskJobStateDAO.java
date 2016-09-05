package com.gszh.wis.tsp.dao;

import com.gszh.wis.tsp.model.TaskJobState;

import java.util.List;

/**
 * 任务实例状态DAO
 * Created by chenzhixiong on 2016/9/2.
 */
public interface TaskJobStateDAO {

    /**
     * 获取状态信息
     * @param po
     * @return
     */
    List<TaskJobState> getState(TaskJobState po);

    /**
     * 查询状态信息
     * @param po
     * @return
     */
    int getStateCount(TaskJobState po);

    /**
     * 状态插入
     * @param po
     */
    int insert(TaskJobState po);

    /**
     * 状态更新
     * @param po
     */
    int update(TaskJobState po);

}
