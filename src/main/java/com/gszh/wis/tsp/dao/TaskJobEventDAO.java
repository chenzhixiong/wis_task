package com.gszh.wis.tsp.dao;

import com.gszh.wis.tsp.model.TaskJobEvent;

import java.util.List;

/**
 * Created by chenzhixiong on 2016/9/7.
 */
public interface TaskJobEventDAO {
    List<TaskJobEvent> getEvent(TaskJobEvent po);
    int insert(TaskJobEvent po);
    int update(TaskJobEvent po);
    int delete(TaskJobEvent po);
}
