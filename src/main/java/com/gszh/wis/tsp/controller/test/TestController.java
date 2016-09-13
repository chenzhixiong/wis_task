package com.gszh.wis.tsp.controller.test;

import com.gszh.wis.tsp.dao.TaskJobCronDAO;
import com.gszh.wis.tsp.dao.TaskJobEventDAO;
import com.gszh.wis.tsp.dao.TaskJobStateDAO;
import com.gszh.wis.tsp.dao.TaskJobStateHistoryDAO;
import com.gszh.wis.tsp.model.*;
import com.gszh.wis.tsp.model.tool.StaticClass;
import com.gszh.wis.tsp.service.TaskJobCronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by chenzhixiong on 2016/8/2.
 */
@Controller
public class TestController {

    @Autowired
    private TaskJobCronService taskJobService;
    @Autowired
    private TaskJobCronDAO taskJobCronDAO;
    @Autowired
    private TaskJobStateHistoryDAO taskJobStateHistoryDAO;
    @Autowired
    private TaskJobEventDAO taskJobEventDAO;
    @Autowired
    private TaskJobStateDAO taskJobStateDAO;

    @RequestMapping("/testEvent")
    public void testEvent() {
        TaskJobEvent po = new TaskJobEvent();
        po.setJobName("test");
        po.setTime(new Date());
        po.setJobChName("");
        po.setJobClass("");
        po.setEntityClass("");
        po.setJobFile("fjidi");
        this.taskJobEventDAO.insert(po);
        System.out.println(this.taskJobEventDAO.getEvent(po));
        po.setTime(new Date(1730000000000L));
        this.taskJobEventDAO.update(po);
        System.out.println(this.taskJobEventDAO.getEvent(po));
        this.taskJobEventDAO.delete(po);
        System.out.println(this.taskJobEventDAO.getEvent(po));
    }

    /**
     * 测试状态更新
     */
    @RequestMapping("/testState")
    public void testState() {
        TaskJobState po = new TaskJobState();
        po.setJobGroup("group");
        po.setJobName("job");
        po.setFireTimeLong(new Date().getTime());
        po.setJobState(StaticClass.TRIGGER_FIRED_COMPLETE);
        int i = this.taskJobStateDAO.insert(po);
        System.out.println(this.taskJobStateDAO.getStateCount(po) + "===============");
        System.out.println("insert +" + i);
        int j = this.taskJobStateDAO.update(po);
        System.out.println("update +" + i);
        System.out.println(this.taskJobStateDAO.getState(po));
    }

    /**
     * 查询 cron定时任务列表
     */
    @RequestMapping("/testParam")
    public void testParam() {
        TaskJobCron po = new TaskJobCron();
        po.setJobGroup("1");
        po.setJobName("1");
//        JobDataMap map = this.taskJobManageService.getJobParam(po);
//        System.out.println(map.size());
    }

    /**
     * 查询 cron定时任务列表
     */
    @RequestMapping("/test")
    public void test() {
        System.out.println(this.taskJobService.getAll());
    }


    @RequestMapping("/testone")
    public void testone() {
        TaskJobStateHistory po = new TaskJobStateHistory();
        System.out.println(this.taskJobStateHistoryDAO.getStateHistoryCount(po) + "======================");
        po.setJobGroup("group");
        po.setJobName("job");
        po.setFireTimeLong(new Date().getTime());
        po.setJobState(StaticClass.TRIGGER_FIRED_COMPLETE);
        int i = this.taskJobStateHistoryDAO.insert(po);
        System.out.println("success +" + i);
        System.out.println(this.taskJobStateHistoryDAO.getStateHistoryCount(po) + "======================");
    }

    /**
     * 查询 cron定时任务列表
     */
    private void getTask() {
        TaskJobCron po = new TaskJobCron();
        po.setJobGroup("1");
        po.setJobName("1");
        System.out.println(this.taskJobCronDAO.getTask(po));
    }

}
