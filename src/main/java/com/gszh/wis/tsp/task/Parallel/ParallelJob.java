package com.gszh.wis.tsp.task.Parallel;

import com.gszh.wis.tsp.task.base.ControllableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhixiong on 2016/8/29.
 */
public class ParallelJob implements ControllableJob {

    private volatile Thread thisThread;
    private volatile String threadFlag = "";
    private volatile boolean state = false;

    String entityClass;
//    String jobChName;
//    String jobFile;
//    String username;
//    String password;

    public ParallelJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        thisThread = Thread.currentThread();
        JobKey key = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        entityClass = jobDataMap.getString("entityClass");

        //Java 反射机制调用类方法
        try {
            Class classType = Class.forName(entityClass);
            Object object = classType.newInstance();
            System.out.println(object.getClass().getName());
            Method method = classType.getMethod("execute", Map.class);
            method.invoke(object, new HashMap<String, Object>());
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
        if (!state) {
            try {
                synchronized (threadFlag) {
                    thisThread.wait();
                }
                state=true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 恢复实例
     */
    @Override
    public void resumeInstance() {
        if (state) {
            synchronized (threadFlag) {
                thisThread.notify();
            }
            state=false;
        }
    }

    /**
     * 停止实例
     */
    @Override
    public void stopInstance() {
        thisThread.stop();
    }
}
