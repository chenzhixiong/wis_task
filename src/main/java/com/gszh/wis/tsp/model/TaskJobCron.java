package com.gszh.wis.tsp.model;

import java.util.Date;

/**
 * cron 表达式的任务类
 * Created by chenzhixiong on 2016/8/2.
 */
public class TaskJobCron {
    private Integer id;
    private String jobName;
    private String jobGroup;
    private String jobChName;
    private String jobFile;
    private String jobClass;
    private String entityClass;
    private String cronExpression;
    private Integer priority;
    private Integer misfire;
    private Integer ifBoot;
    private Integer isRelyOn;
    private Integer relyWaitTime;
    private String RelyOn;
    private Date startTime;
    private Date endTime;
    private String jobType;
    private String description;
    private String createTime;
    private String updateTime;
    private String username;
    private String password;

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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getMisfire() {
        return misfire;
    }

    public void setMisfire(Integer misfire) {
        this.misfire = misfire;
    }

    public Integer getIfBoot() {
        return ifBoot;
    }

    public void setIfBoot(Integer ifBoot) {
        this.ifBoot = ifBoot;
    }

    public Integer getIsRelyOn() {
        return isRelyOn;
    }

    public void setIsRelyOn(Integer isRelyOn) {
        this.isRelyOn = isRelyOn;
    }

    public String getRelyOn() {
        return RelyOn;
    }

    public void setRelyOn(String relyOn) {
        RelyOn = relyOn;
    }

    public Integer getRelyWaitTime() {
        return relyWaitTime;
    }

    public void setRelyWaitTime(Integer relyWaitTime) {
        this.relyWaitTime = relyWaitTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "TaskJobCron{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobChName='" + jobChName + '\'' +
                ", jobFile='" + jobFile + '\'' +
                ", jobClass='" + jobClass + '\'' +
                ", entityClass='" + entityClass + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", priority=" + priority +
                ", misfire=" + misfire +
                ", ifBoot=" + ifBoot +
                ", isRelyOn=" + isRelyOn +
                ", relyWaitTime=" + relyWaitTime +
                ", RelyOn='" + RelyOn + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", jobType='" + jobType + '\'' +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
