package quartz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quartz.dao.TaskJobCronDAO;
import quartz.model.TaskJobCron;
import quartz.service.TaskJobCronService;

import java.util.List;

/**
 * 定时设置
 * Created by chenzhixiong on 2016/7/28.
 */
@Service
public class TaskJobCronServiceImpl implements TaskJobCronService {

    @Autowired
    private TaskJobCronDAO taskJobCronDAO;

    /**
     * 查询 cron定时任务列表
     * @return
     */
    @Override
    public List<TaskJobCron> getAll() {
        return this.taskJobCronDAO.getAll();
    }
}
