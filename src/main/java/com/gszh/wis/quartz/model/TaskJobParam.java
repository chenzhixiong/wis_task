package com.gszh.wis.quartz.model;

/**
 * Created by chenzhixiong on 2016/8/23.
 */
public class TaskJobParam {
    private Integer id;
    private String jobName;
    private String jobGroup;
    private String paramName;
    private String paramValue;
    private String paramDesc;
    private String createTime;
    private String updateTime;

    public TaskJobParam() {

    }
    public TaskJobParam(String jobGroup, String jobName) {
        this.jobGroup = jobGroup;
        this.jobName = jobName;
    }

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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
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

    @Override
    public String toString() {
        return "TaskJobParam{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", paramName='" + paramName + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", paramDesc='" + paramDesc + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
