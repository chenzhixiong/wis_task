package com.gszh.wis.tsp.controller.test;

import com.gszh.wis.tsp.dao.TaskJobCronDAO;
import com.gszh.wis.tsp.dao.TaskJobStateDAO;
import com.gszh.wis.tsp.dao.TaskJobStateHistoryDAO;
import com.gszh.wis.tsp.model.StaticClass;
import com.gszh.wis.tsp.model.TaskJobCron;
import com.gszh.wis.tsp.model.TaskJobState;
import com.gszh.wis.tsp.model.TaskJobStateHistory;
import com.gszh.wis.tsp.service.TaskJobCronService;
import com.gszh.wis.tsp.service.TaskJobManageService;
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
    private TaskJobManageService taskJobManageService;
    @Autowired
    private TaskJobStateDAO taskJobStateDAO;

    /**
     * 测试状态更新
     */
    @RequestMapping("/testState")
    public void testState(){
        TaskJobState po = new TaskJobState();
        po.setJobGroup("group");
        po.setJobName("job");
        po.setFireTimeLong(new Date().getTime());
        po.setJobState(StaticClass.TRIGGER_FIRED_COMPLETE);
        int i = this.taskJobStateDAO.insert(po);
        System.out.println(this.taskJobStateDAO.getStateCount(po)+"===============");
        System.out.println("insert +"+i);
        int j = this.taskJobStateDAO.update(po);
        System.out.println("update +"+i);
        System.out.println(this.taskJobStateDAO.getState(po));
    }

    /**
     * 查询 cron定时任务列表
     */
    @RequestMapping("/testParam")
    public void testParam(){
        TaskJobCron po=new TaskJobCron();
        po.setJobGroup("1");
        po.setJobName("1");
//        JobDataMap map = this.taskJobManageService.getJobParam(po);
//        System.out.println(map.size());
    }

    /**
     * 查询 cron定时任务列表
     */
    @RequestMapping("/test")
    public void test(){
        System.out.println(this.taskJobService.getAll());
    }


    @RequestMapping("/testone")
    public void testone(){
        TaskJobStateHistory po = new TaskJobStateHistory();
        System.out.println(this.taskJobStateHistoryDAO.getStateHistoryCount(po)+"======================");
        po.setJobGroup("group");
        po.setJobName("job");
        po.setFireTimeLong(new Date().getTime());
        po.setJobState(StaticClass.TRIGGER_FIRED_COMPLETE);
        int i = this.taskJobStateHistoryDAO.insert(po);
        System.out.println("success +"+i);
        System.out.println(this.taskJobStateHistoryDAO.getStateHistoryCount(po)+"======================");
    }

    /**
     * 查询 cron定时任务列表
     */
    private void getTask() {
        TaskJobCron po=new TaskJobCron();
        po.setJobGroup("1");
        po.setJobName("1");
        System.out.println(this.taskJobCronDAO.getTask(po));
    }

}
