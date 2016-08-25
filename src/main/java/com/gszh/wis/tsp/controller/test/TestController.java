package com.gszh.wis.tsp.controller.test;

import com.gszh.wis.tsp.dao.TaskJobCronDAO;
import com.gszh.wis.tsp.dao.TaskJobStateDAO;
import com.gszh.wis.tsp.model.StaticValue;
import com.gszh.wis.tsp.model.TaskJobCron;
import com.gszh.wis.tsp.model.TaskJobState;
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
    private TaskJobStateDAO taskJobStateDAO;
    @Autowired
    private TaskJobManageService taskJobManageService;

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
        TaskJobState po = new TaskJobState();
        System.out.println(this.taskJobStateDAO.getStateCount(po)+"======================");
        po.setJobGroup("group");
        po.setJobName("job");
        po.setFireTime(new Date());
        po.setJobState(StaticValue.TRIGGER_FIRED_COMPLETE);
        int i = this.taskJobStateDAO.insert(po);
        System.out.println("success +"+i);
        System.out.println(this.taskJobStateDAO.getStateCount(po)+"======================");
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
