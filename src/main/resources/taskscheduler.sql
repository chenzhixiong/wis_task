/*
Navicat MySQL Data Transfer

Source Server         : chen
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : taskscheduler

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-08-23 11:40:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS qrtz_job_details;
CREATE TABLE qrtz_job_details (
  SCHED_NAME varchar(120) NOT NULL,
  JOB_NAME varchar(200) NOT NULL,
  JOB_GROUP varchar(200) NOT NULL,
  DESCRIPTION varchar(250) DEFAULT NULL,
  JOB_CLASS_NAME varchar(250) NOT NULL,
  IS_DURABLE varchar(1) NOT NULL,
  IS_NONCONCURRENT varchar(1) NOT NULL,
  IS_UPDATE_DATA varchar(1) NOT NULL,
  REQUESTS_RECOVERY varchar(1) NOT NULL,
  JOB_DATA blob,
  PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_triggers;
CREATE TABLE qrtz_triggers (
  SCHED_NAME varchar(120) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  JOB_NAME varchar(200) NOT NULL,
  JOB_GROUP varchar(200) NOT NULL,
  DESCRIPTION varchar(250) DEFAULT NULL,
  NEXT_FIRE_TIME bigint(13) DEFAULT NULL,
  PREV_FIRE_TIME bigint(13) DEFAULT NULL,
  PRIORITY int(11) DEFAULT NULL,
  TRIGGER_STATE varchar(16) NOT NULL,
  TRIGGER_TYPE varchar(8) NOT NULL,
  START_TIME bigint(13) NOT NULL,
  END_TIME bigint(13) DEFAULT NULL,
  CALENDAR_NAME varchar(200) DEFAULT NULL,
  MISFIRE_INSTR smallint(2) DEFAULT NULL,
  JOB_DATA blob,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  KEY SCHED_NAME (SCHED_NAME,JOB_NAME,JOB_GROUP),
  CONSTRAINT qrtz_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP) REFERENCES qrtz_job_details (SCHED_NAME, JOB_NAME, JOB_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS qrtz_calendars;
CREATE TABLE qrtz_calendars (
  SCHED_NAME varchar(120) NOT NULL,
  CALENDAR_NAME varchar(200) NOT NULL,
  CALENDAR blob NOT NULL,
  PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_fired_triggers;
CREATE TABLE qrtz_fired_triggers (
  SCHED_NAME varchar(120) NOT NULL,
  ENTRY_ID varchar(95) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  INSTANCE_NAME varchar(200) NOT NULL,
  FIRED_TIME bigint(13) NOT NULL,
  SCHED_TIME bigint(13) NOT NULL,
  PRIORITY int(11) NOT NULL,
  STATE varchar(16) NOT NULL,
  JOB_NAME varchar(200) DEFAULT NULL,
  JOB_GROUP varchar(200) DEFAULT NULL,
  IS_NONCONCURRENT varchar(1) DEFAULT NULL,
  REQUESTS_RECOVERY varchar(1) DEFAULT NULL,
  PRIMARY KEY (SCHED_NAME,ENTRY_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS qrtz_locks;
CREATE TABLE qrtz_locks (
  SCHED_NAME varchar(120) NOT NULL,
  LOCK_NAME varchar(40) NOT NULL,
  PRIMARY KEY (SCHED_NAME,LOCK_NAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS qrtz_paused_trigger_grps;
CREATE TABLE qrtz_paused_trigger_grps (
  SCHED_NAME varchar(120) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS qrtz_scheduler_state;
CREATE TABLE qrtz_scheduler_state (
  SCHED_NAME varchar(120) NOT NULL,
  INSTANCE_NAME varchar(200) NOT NULL,
  LAST_CHECKIN_TIME bigint(13) NOT NULL,
  CHECKIN_INTERVAL bigint(13) NOT NULL,
  PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_simple_triggers;
CREATE TABLE qrtz_simple_triggers (
  SCHED_NAME varchar(120) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  REPEAT_COUNT bigint(7) NOT NULL,
  REPEAT_INTERVAL bigint(12) NOT NULL,
  TIMES_TRIGGERED bigint(10) NOT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_simple_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_simprop_triggers;
CREATE TABLE qrtz_simprop_triggers (
  SCHED_NAME varchar(120) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  STR_PROP_1 varchar(512) DEFAULT NULL,
  STR_PROP_2 varchar(512) DEFAULT NULL,
  STR_PROP_3 varchar(512) DEFAULT NULL,
  INT_PROP_1 int(11) DEFAULT NULL,
  INT_PROP_2 int(11) DEFAULT NULL,
  LONG_PROP_1 bigint(20) DEFAULT NULL,
  LONG_PROP_2 bigint(20) DEFAULT NULL,
  DEC_PROP_1 decimal(13,4) DEFAULT NULL,
  DEC_PROP_2 decimal(13,4) DEFAULT NULL,
  BOOL_PROP_1 varchar(1) DEFAULT NULL,
  BOOL_PROP_2 varchar(1) DEFAULT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_simprop_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_blob_triggers;
CREATE TABLE qrtz_blob_triggers (
  SCHED_NAME varchar(120) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  BLOB_DATA blob,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_blob_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_cron_triggers;
CREATE TABLE qrtz_cron_triggers (
  SCHED_NAME varchar(120) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  CRON_EXPRESSION varchar(200) NOT NULL,
  TIME_ZONE_ID varchar(80) DEFAULT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_cron_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for task_job_cron
-- ----------------------------
DROP TABLE IF EXISTS job_cron_triggers;
CREATE TABLE job_cron_triggers (
  id int(11) NOT NULL AUTO_INCREMENT,
  job varchar(200) NOT NULL COMMENT '任务实体',
  jobName varchar(100) NOT NULL COMMENT '任务标注名称',
  jobGroup varchar(100) NOT NULL COMMENT '任务组',
  triggerName varchar(100) NOT NULL COMMENT '触发器标注名称',
  triggerGroup varchar(100) NOT NULL COMMENT '触发器组',
  cronExpression varchar(20) NOT NULL COMMENT 'cron表达式',
  description varchar(200) DEFAULT NULL COMMENT '任务描述',
  createTime timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  updateTime timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  creater varchar(255) DEFAULT NULL COMMENT '创建者',
  ifBoot int(11) DEFAULT NULL COMMENT '是否开机自启（开机自启：0，非开机自启：1）',
  PRIMARY KEY (id),
  UNIQUE KEY jobKey (jobName,jobGroup),
  UNIQUE KEY triggerKey (triggerName,triggerGroup)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_cron_triggers
-- ----------------------------
INSERT INTO job_cron_triggers VALUES ('8', 'quartz.task.HelloJob', 'job1', 'group1', 'job1', 'group1', '0/10 * * * * ?', '测试', '2016-08-10 10:07:04', null, null, '0');
INSERT INTO job_cron_triggers VALUES ('9', 'quartz.task.HelloJob2', 'job2', 'group1', 'job2', 'group1', '0/10 * * * * ?', '测试', '2016-08-10 10:07:04', '2016-08-10 10:49:25', '', '0');
-- ----------------------------
-- Table structure for task_job_param
-- ----------------------------
DROP TABLE IF EXISTS task_job_param;
CREATE TABLE task_job_param (
  id int(11) NOT NULL AUTO_INCREMENT,
  job_name varchar(50) DEFAULT NULL COMMENT '任务名称',
  job_group varchar(50) DEFAULT NULL COMMENT '任务分组',
  param_name varchar(200) DEFAULT NULL COMMENT '参数名称',
  param_value varchar(200) DEFAULT NULL COMMENT '参数值',
  param_desc varchar(200) DEFAULT NULL COMMENT '参数含义',
  create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_job_param
-- ----------------------------

-- ----------------------------
-- Table structure for task_job_state
-- ----------------------------
DROP TABLE IF EXISTS task_job_state;
CREATE TABLE task_job_state (
  id int(11) NOT NULL AUTO_INCREMENT,
  instance_no varchar(50) DEFAULT NULL,
  job_name varchar(50) NOT NULL COMMENT '任务名称\r\n注：任务分组，任务名称   字符串的拼接值是唯一的',
  job_group varchar(50) NOT NULL COMMENT '任务分组\r\n注：任务分组，任务名称   字符串的拼接值是唯一的',
  fire_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '计划的触发时间',
  job_state varchar(50) NOT NULL COMMENT '任务状态：\r\n执行，等待，异常，完成',
  record_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_job_state
-- ----------------------------
