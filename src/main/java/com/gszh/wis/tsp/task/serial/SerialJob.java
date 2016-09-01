package com.gszh.wis.tsp.task.serial;

import com.gszh.wis.tsp.task.base.ControllableJob;
import org.quartz.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhixiong on 2016/8/29.
 */
@DisallowConcurrentExecution
public class SerialJob implements ControllableJob {

    private volatile Thread thisThread;
    private String threadFlag = "";
    private volatile boolean state = true;

    private Map<String, Object> map = new HashMap<String, Object>();
    String entityClass;
//    String jobChName;
//    String jobFile;
//    String username;
//    String password;

    public SerialJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        thisThread = Thread.currentThread();
        JobKey key = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        entityClass = jobDataMap.getString("entityClass");
        map.clear();
        map = jobDataMap.getWrappedMap();
        map.put("fireTime", context.getFireTime());
        //Java 反射机制调用类方法
        try {
            Class classType = Class.forName(entityClass);
            Object object = classType.newInstance();
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
            state = false;
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
        }
    }

    /**
     * 停止实例
     */
    @Override
    public void stopInstance() {
        try{
            thisThread.stop();}
        catch (ThreadDeath e){
            e.printStackTrace();
        }
    }
}
