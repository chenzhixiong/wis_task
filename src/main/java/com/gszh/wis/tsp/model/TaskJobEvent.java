package com.gszh.wis.tsp.model;

import java.util.Date;

/**
 * 事件任务（常驻任务）
 * Created by chenzhixiong on 2016/9/7.
 */
public class TaskJobEvent {
    private Integer id;
    private String jobName;
    private String jobGroup;
    private String jobChName;
    private String jobFile;
    private String jobClass;
    private String entityClass;
    private String jobType;
    private String description;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Date time;
    private String timeType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getJobChName() {
        return jobChName;
    }

    public void setJobChName(String jobChName) {
        this.jobChName = jobChName;
    }

    public String getJobFile() {
        return jobFile;
    }

    public void setJobFile(String jobFile) {
        this.jobFile = jobFile;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    @Override
    public String toString() {
        return "TaskJobEvent{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobChName='" + jobChName + '\'' +
                ", jobFile='" + jobFile + '\'' +
                ", jobClass='" + jobClass + '\'' +
                ", entityClass='" + entityClass + '\'' +
                ", jobType='" + jobType + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", time=" + time +
                ", timeType='" + timeType + '\'' +
                '}';
    }
}
