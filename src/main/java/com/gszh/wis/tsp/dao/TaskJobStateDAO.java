package com.gszh.wis.tsp.dao;

import com.gszh.wis.tsp.model.TaskJobState;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/18.
 */
public interface TaskJobStateDAO {

    int insert(TaskJobState po);

    int delete(TaskJobState po);

    int update(TaskJobState po);

    /**
     * 查询特定状态的任务实例是否存在
     * @param po
     * @return
     */
    int getStateCount(TaskJobState po);

    /**
     * 查询某个任务实例
     */
    List<TaskJobState> getState(TaskJobState po);

}
