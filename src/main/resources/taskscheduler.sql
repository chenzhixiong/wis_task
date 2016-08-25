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
DROP TABLE IF EXISTS task_job_cron;
CREATE TABLE task_job_cron (
  id int(11) NOT NULL AUTO_INCREMENT,
  job_name varchar(50) NOT NULL COMMENT '任务名称\r\n注：任务分组，任务名称   字符串的拼接值是唯一的\r\n\r\n例如：已经插入了 一条记录 的 jobName 和 jobGroup 为 （job1,group1）,那么下次就无法再将 （job1,group1）插入， 而（job2,group1）或（job1,group2）却是允许插入的',
  job_group varchar(50) NOT NULL COMMENT '任务分组\r\n注：任务分组，任务名称   字符串的拼接值是唯一的\r\n\r\n例如：已经插入了 一条记录 的 jobName 和 jobGroup 为 （job1,group1）,那么下次就无法再将 （job1,group1）插入， 而（job2,group1）或（job1,group2）却是允许插入的',
  entity_class varchar(200) NOT NULL COMMENT '任务类\r\n值为 全类名(完整包路径.类名) \r\n例如： com.gszh.HelloJob',
--  parameters varchar(500) DEFAULT NULL COMMENT '任务的入参；\r\n格式：\r\nkey:value;key:value;\r\nvalue 本身的类型需要任务实现类来解析。\r\n',
  cron_expression varchar(20) NOT NULL COMMENT 'cron 时间表达式\r\n\r\n例如：0/5 * * * * ? 表示每5秒执行一次',
  priority int(11) NOT NULL DEFAULT '5' COMMENT '任务优先权限\r\n默认为5\r\n两个任务在同一时间触发，值越大越优先执行\r\n1<=priority<=10',
  misfire int(11) NOT NULL DEFAULT '0' COMMENT '错失触发时间后执行的方案，默认为0；\r\n      \r\n0：withMisfireHandlingInstructionDoNothing\r\n——不触发立即执行\r\n——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行\r\n\r\n1：withMisfireHandlingInstructionIgnoreMisfires\r\n——以错过的第一个频率时间立刻开始执行\r\n——重做错过的所有频率周期后\r\n——当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行\r\n\r\n2：withMisfireHandlingInstructionFireAndProceed\r\n——以当前时间为触发频率立刻触发一次执行\r\n——然后按照Cron频率依次执行\r\n\r\n',
  if_boot int(11) NOT NULL DEFAULT '0' COMMENT '是否开机启动，0：开机启动；1：手动启动',
  is_rely_on int(11) NOT NULL COMMENT '是否存在任务依赖，0：存在；1：不存在',
  rely_wait_time int(11) NOT NULL COMMENT '任务依赖等待时间，超时则取消等待\r\n等待时间设置需小于1h',
  rely_on varchar(500) NOT NULL COMMENT '任务一级依赖；\r\n书写规范：任务分组.任务名称;\r\n样例：group1.job1;group2.job2;',
  start_time timestamp NULL DEFAULT NULL COMMENT '任务启动时间（为空时，则默认为每次程序的启动时间）',
  end_time timestamp NULL DEFAULT NULL COMMENT '任务结束时间（为null时，默认为不限制结束时间）',
  job_type varchar(50) DEFAULT NULL COMMENT '任务类别',
  description varchar(200) DEFAULT NULL COMMENT '任务描述',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  creater varchar(50) DEFAULT NULL COMMENT '任务添加者',
  updater varchar(50) DEFAULT NULL COMMENT '任务修改者',
  PRIMARY KEY (id),
  UNIQUE KEY jobkey (job_name,job_group) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_job_cron
-- ----------------------------
INSERT INTO task_job_cron VALUES ('1', 'job1', 'group1', 'com.gszh.wis.tsp.task.HelloJob', null, '0/15 * * * * ?', '5', '0', '0', '0', '0', 'group1.job2', null, null, null, '测试', '2016-08-08 12:00:55', '2016-08-23 10:50:59', null, null);
INSERT INTO task_job_cron VALUES ('2', 'job2', 'group1', 'com.gszh.wis.tsp.task.SecondJob', null, '0/15 * * * * ?', '5', '0', '0', '1', '0', '', null, null, null, '', '2016-08-08 12:00:55', '2016-08-23 10:56:42', null, null);

-- ----------------------------
-- Table structure for task_job_param
-- ----------------------------
DROP TABLE IF EXISTS task_job_param;
CREATE TABLE task_job_param (
  id int(11) NOT NULL,
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
