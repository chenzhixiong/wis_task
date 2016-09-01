package com.gszh.wis.tsp.model;

import java.security.MessageDigest;

/**
 * Created by chenzhixiong on 2016/8/18.
 */
public class StaticClass {
    public static final String TRIGGER_FIRED_NORMAL="执行";
    public static final String TRIGGER_FIRED_WAITING="等待";
    public static final String TRIGGER_FIRED_WAITING_OUT="等待超时，等待的任务key：";
    public static final String TRIGGER_FIRED_COMPLETE="完成";
    public static final String TRIGGER_FIRED_EXCEPTION="异常";

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
