package com.gszh.wis.tsp.model;

import java.util.Date;

/**
 * 任务实例状态记录表
 * Created by chenzhixiong on 2016/8/18.
 */
public class TaskJobStateHistory {
    private Integer id;
    private String instanceNo;
    private String jobName;
    private String jobGroup;
    private Long fireTimeLong;
    private String jobState;
    private Date recordTime;

    //冗余（非数据库字段）
    private Long larger;
    private Long smaller;

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

    public Long getFireTimeLong() {
        return fireTimeLong;
    }

    public void setFireTimeLong(Long fireTimeLong) {
        this.fireTimeLong = fireTimeLong;
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

    public Long getSmaller() {
        return smaller;
    }

    public void setSmaller(Long smaller) {
        this.smaller = smaller;
    }

    public Long getLarger() {
        return larger;
    }

    public void setLarger(Long larger) {
        this.larger = larger;
    }

    @Override
    public String toString() {
        return "TaskJobStateHistory{" +
                "id=" + id +
                ", instanceNo='" + instanceNo + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", fireTimeLong=" + fireTimeLong +
                ", jobState='" + jobState + '\'' +
                ", recordTime=" + recordTime +
                ", larger=" + larger +
                ", smaller=" + smaller +
                '}';
    }
}
