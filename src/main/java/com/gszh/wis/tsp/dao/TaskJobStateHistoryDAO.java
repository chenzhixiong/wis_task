package com.gszh.wis.tsp.dao;

import com.gszh.wis.tsp.model.TaskJobStateHistory;

import java.util.List;

/**
 * 任务实例状态记录DAO
 * Created by chenzhixiong on 2016/8/18.
 */
public interface TaskJobStateHistoryDAO {

    int insert(TaskJobStateHistory po);

    int delete(TaskJobStateHistory po);

    int update(TaskJobStateHistory po);

    /**
     * 查询特定状态的任务实例是否存在
     * @param po
     * @return
     */
    int getStateHistoryCount(TaskJobStateHistory po);

    /**
     * 查询某个任务实例
     */
    List<TaskJobStateHistory> getStateHistory(TaskJobStateHistory po);

}
