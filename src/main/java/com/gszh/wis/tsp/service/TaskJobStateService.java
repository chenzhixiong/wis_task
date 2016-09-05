package com.gszh.wis.tsp.service;

import com.gszh.wis.tsp.model.TaskJobState;
import com.gszh.wis.tsp.model.TaskJobStateHistory;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/9/5.
 */
public interface TaskJobStateService {

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
    int insertState(TaskJobState po);

    /**
     * 状态更新
     * @param po
     */
    int updateState(TaskJobState po);


    /**
     * 状态插入记录
     * @param po
     */
    int insertHistory(TaskJobStateHistory po);

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
