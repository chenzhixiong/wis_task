package com.gszh.wis.quartz.dao;

import com.gszh.wis.quartz.model.TaskJobParam;
import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/23.
 */
public interface TaskJobParamDAO{

    /**
     * 查询任务执行需要的参数
     * @param po
     * @return
     */
    List<TaskJobParam> getJobParam(TaskJobParam po);

    int insert(TaskJobParam po);

    int delete(TaskJobParam po);

    int update(TaskJobParam po);
}
