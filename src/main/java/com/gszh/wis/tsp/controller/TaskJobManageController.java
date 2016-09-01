package com.gszh.wis.tsp.controller;

import com.gszh.wis.tsp.model.TaskJobCron;
import com.gszh.wis.tsp.service.TaskJobManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 定时任务管理器
 * Created by chenzhixiong on 2016/8/11.
 */
@Controller
public class TaskJobManageController {

    @Autowired
    private TaskJobManageService taskJobManageService;

    /**
     * 开启全部定时器(开机自启的定时器)
     */
    @RequestMapping("/startAll")
    public void startAll() {
        this.taskJobManageService.startAll();
    }

    /**
     * 根据任务key开启指定任务
     *
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/startOne")
    public void startOne(String jobName, String jobGroup) {
        if (jobGroup != null && !"".equals(jobGroup.trim()) && jobName != null && !"".equals(jobName.trim())) {
            TaskJobCron po = new TaskJobCron();
            po.setJobName(jobName);
            po.setJobGroup(jobGroup);
            this.taskJobManageService.startOne(po);
        }
    }

    /**
     * 添加任务
     *
     * @param po
     */
    @RequestMapping("/addOne")
    public void addOne(TaskJobCron po) {
        if (po != null) {
            this.taskJobManageService.addOne(po);
        }
    }

    /**
     * 修改任务
     *
     * @param po
     */
    @RequestMapping("/updateOne")
    public void updateOne(TaskJobCron po) {
        if (po != null) {
            this.taskJobManageService.updateOne(po);
        }
    }

    /**
     * 根据任务key暂停指定任务
     *
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/pauseOne")
    public void pauseOne(String jobName, String jobGroup) {
        if (jobGroup != null && !"".equals(jobGroup.trim()) && jobName != null && !"".equals(jobName.trim())) {
            TaskJobCron po = new TaskJobCron();
            po.setJobName(jobName);
            po.setJobGroup(jobGroup);
            this.taskJobManageService.pauseOne(po);
        }
    }

    /**
     * 根据任务key恢复指定任务运行（若是任务本身不是暂停状态，那么本操作没有任何效果）
     *
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/resumeOne")
    public void resumeOne(String jobName, String jobGroup) {
        if (jobGroup != null && !"".equals(jobGroup.trim()) && jobName != null && !"".equals(jobName.trim())) {
            TaskJobCron po = new TaskJobCron();
            po.setJobName(jobName);
            po.setJobGroup(jobGroup);
            this.taskJobManageService.resumeOne(po);
        }
    }

    /**
     * 根据任务key删除指定任务
     *
     * @param jobName
     * @param jobGroup
     */
    @RequestMapping("/deleteOne")
    public void deleteOne(String jobName, String jobGroup) {
        if (jobGroup != null && !"".equals(jobGroup.trim()) && jobName != null && !"".equals(jobName.trim())) {
            TaskJobCron po = new TaskJobCron();
            po.setJobName(jobName);
            po.setJobGroup(jobGroup);
            this.taskJobManageService.deleteOne(po);
        }
    }

    /**
     * 暂停实例
     *
     * @param instanceNo
     */
    @RequestMapping("/pauseInstance")
    public void pauseInstance(String instanceNo) {
        taskJobManageService.pasueInstance(instanceNo);
    }

    /**
     * 恢复实例
     *
     * @param instanceNo
     */
    @RequestMapping("/resumeInstance")
    public void resumeInstance(String instanceNo) {
        taskJobManageService.resumeInstance(instanceNo);
    }

    /**
     * 停止实例
     *
     * @param instanceNo
     */
    @RequestMapping("/stopInstance")
    public void stopInstance(String instanceNo) {
        taskJobManageService.stopInstance(instanceNo);
    }
}
