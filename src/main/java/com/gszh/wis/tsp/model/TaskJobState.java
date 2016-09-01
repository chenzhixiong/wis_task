package com.gszh.wis.tsp.model;

import java.util.Date;

/**
 * 任务状态记录表
 * Created by chenzhixiong on 2016/8/18.
 */
public class TaskJobState {
    private Integer id;
    private String instanceNo;
    private String jobName;
    private String jobGroup;
    private Date fireTime;
    private String jobState;
    private Date recordTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstanceNo() {
        return instanceNo;
    }

    public void setInstanceNo(String instanceNo) {
        this.instanceNo = instanceNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Date getFireTime() {
        return fireTime;
    }

    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "TaskJobState{" +
                "id=" + id +
                ", instanceNo='" + instanceNo + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", fireTime=" + fireTime +
                ", jobState='" + jobState + '\'' +
                ", recordTime=" + recordTime +
                '}';
    }
}
