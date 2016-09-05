package com.gszh.wis.tsp.service.impl;

import com.gszh.wis.tsp.dao.TaskJobStateDAO;
import com.gszh.wis.tsp.dao.TaskJobStateHistoryDAO;
import com.gszh.wis.tsp.model.TaskJobState;
import com.gszh.wis.tsp.model.TaskJobStateHistory;
import com.gszh.wis.tsp.service.TaskJobStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/9/5.
 */
@Service
public class TaskJobStateServiceImpl implements TaskJobStateService {

    @Autowired
    private TaskJobStateHistoryDAO taskJobStateHistoryDAO;
    @Autowired
    private TaskJobStateDAO taskJobStateDAO;

    /**
     * 获取状态信息
     *
     * @param po
     * @return
     */
    @Override
    public List<TaskJobState> getState(TaskJobState po) {
        return this.taskJobStateDAO.getState(po);
    }

    /**
     * 查询状态信息
     *
     * @param po
     * @return
     */
    @Override
    public int getStateCount(TaskJobState po) {
        return this.taskJobStateDAO.getStateCount(po);
    }

    /**
     * 状态插入
     *
     * @param po
     */
    @Override
    public int insertState(TaskJobState po) {
        return this.taskJobStateDAO.insert(po);
    }

    /**
     * 状态更新
     *
     * @param po
     */
    @Override
    public int updateState(TaskJobState po) {
        return this.taskJobStateDAO.update(po);
    }

    /**
     * 状态插入记录
     *
     * @param po
     */
    @Override
    public int insertHistory(TaskJobStateHistory po) {
        return this.taskJobStateHistoryDAO.insert(po);
    }

    /**
     * 查询特定状态的任务实例是否存在
     *
     * @param po
     * @return
     */
    @Override
    public int getStateHistoryCount(TaskJobStateHistory po) {
        return this.taskJobStateHistoryDAO.getStateHistoryCount(po);
    }

    /**
     * 查询某个任务实例
     *
     * @param po
     */
    @Override
    public List<TaskJobStateHistory> getStateHistory(TaskJobStateHistory po) {
        return this.taskJobStateHistoryDAO.getStateHistory(po);
    }
}
