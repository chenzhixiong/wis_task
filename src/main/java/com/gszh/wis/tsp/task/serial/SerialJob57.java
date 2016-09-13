package com.gszh.wis.tsp.task.serial;

import com.gszh.wis.tsp.model.TaskJobState;
import com.gszh.wis.tsp.model.TaskJobStateHistory;
import com.gszh.wis.tsp.model.tool.StaticClass;
import com.gszh.wis.tsp.service.TaskJobStateService;
import com.gszh.wis.tsp.task.base.ControllableJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhixiong on 2016-09-09.
 */
@DisallowConcurrentExecution
public class SerialJob57 implements ControllableJob {

   private Thread thisThread;
   private String threadFlag = "";
   private boolean state = true;
   private Object object;

   private Map<String, Object> map = new HashMap<String, Object>();
   String entityClass;
//    String jobChName;
//    String jobFile;
//    String username;
//    String password;

   @Autowired
   private TaskJobStateService taskJobStateService;
   private TaskJobState jobState=new TaskJobState();
   private TaskJobStateHistory jobStateHistory=new TaskJobStateHistory();

   public SerialJob57() {
   }

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {
       thisThread = Thread.currentThread();
       JobKey key = context.getJobDetail().getKey();
       JobDataMap jobDataMap = context.getMergedJobDataMap();
       entityClass = jobDataMap.getString("entityClass");
       System.out.println(map.size());
       map.clear();
       map = jobDataMap.getWrappedMap();
       if(!map.containsKey("fireTime"))
           map.put("fireTime", context.getFireTime());
       String instanceNo=context.getTrigger().getKey() + new Long(context.getTrigger().getPreviousFireTime().getTime()).toString();
       if(!map.containsKey("instanceNo"))
           map.put("instanceNo",instanceNo);
       jobState.setInstanceNo(instanceNo);
       jobStateHistory.setInstanceNo(instanceNo);
       jobStateHistory.setJobName(context.getJobDetail().getKey().getName());
       jobStateHistory.setJobGroup(context.getJobDetail().getKey().getGroup());
       jobStateHistory.setFireTimeLong(context.getFireTime().getTime());

       //Java 反射机制调用类方法
       try {
           Class classType = Class.forName(entityClass);
           object = classType.newInstance();
           System.out.println(object.getClass().getName());
           Method method = classType.getMethod("execute", Map.class);
           method.invoke(object, map);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (NoSuchMethodException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       }
   }

   /**
    * 暂停实例
    */
   @Override
   public void pasueInstance() {
       if (state) {
           //向任务实例发送消息
           try {
               Class classType = Class.forName(entityClass);
               System.out.println(object.getClass().getName());
               Method method = classType.getMethod("pause", Map.class);
               method.invoke(object, map);
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (NoSuchMethodException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }
           state = false;
           jobState.setJobState(StaticClass.TRIGGER_INSTANCE_PAUSE);
           jobStateHistory.setJobState(StaticClass.TRIGGER_INSTANCE_PAUSE);
           this.taskJobStateService.updateState(jobState);
           this.taskJobStateService.insertHistory(jobStateHistory);
           thisThread.suspend();
       }
   }

   /**
    * 恢复实例
    */
   @Override
   public void resumeInstance() {
       if (!state) {
           state = true;
           thisThread.resume();
           //向任务实例发送消息
           try {
               Class classType = Class.forName(entityClass);
               System.out.println(object.getClass().getName());
               Method method = classType.getMethod("resume", Map.class);
               method.invoke(object, map);
               jobState.setJobState(StaticClass.TRIGGER_INSTANCE_RESUME);
               jobStateHistory.setJobState(StaticClass.TRIGGER_INSTANCE_RESUME);
               this.taskJobStateService.updateState(jobState);
               this.taskJobStateService.insertHistory(jobStateHistory);
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (NoSuchMethodException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }
       }

   }

   /**
    * 停止实例
    */
   @Override
   public void stopInstance() {
       //向任务实例发送消息
       try {
           Class classType = Class.forName(entityClass);
           System.out.println(object.getClass().getName());
           Method method = classType.getMethod("stop", Map.class);
           method.invoke(object, map);
           jobState.setJobState(StaticClass.TRIGGER_INSTANCE_STOP);
           jobStateHistory.setJobState(StaticClass.TRIGGER_INSTANCE_STOP);
           this.taskJobStateService.updateState(jobState);
           this.taskJobStateService.insertHistory(jobStateHistory);
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (NoSuchMethodException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }

       try {
           thisThread.stop();
       } catch (ThreadDeath e) {
           e.printStackTrace();
       }
   }
}
