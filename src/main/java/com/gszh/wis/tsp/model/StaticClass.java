package com.gszh.wis.tsp.model;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/18.
 */
public class StaticClass {
    public static final String TRIGGER_FIRED_NORMAL="开始运行";
    public static final String TRIGGER_FIRED_WAITING="依赖等待";
    public static final String TRIGGER_FIRED_WAITING_OUT="等待超时，所等待任务key为：";
    public static final String TRIGGER_FIRED_COMPLETE="结束完成";
    public static final String TRIGGER_FIRED_EXCEPTION="错过时间，调用 misfired 策略";
    public static final String TRIGGER_INSTANCE_PAUSE="暂停操作";
    public static final String TRIGGER_INSTANCE_RESUME="恢复运行";
    public static final String TRIGGER_INSTANCE_STOP="任务中断";

    //串行任务池
    public static List<String> poolList=new ArrayList<String>();
    //并行任务载体
    public static String parallel="com.gszh.wis.tsp.task.Parallel.ParallelJob";
    static {
        for (int i=1;i<=20;i++){
            poolList.add("com.gszh.wis.tsp.task.serial.SerialJob"+i);
        }
    }

    /**
     * md5 加密
     * @param str
     * @return
     */
    public static String md5Str(String str){
        String reStr = null;
        try {
            //加密器
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b&0xff;
                //如果小于16，补位一个0
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }

            reStr = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }
}
