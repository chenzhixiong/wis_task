package com.gszh.wis.tsp.controller.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by chenzhixiong on 2016/8/12.
 */

public class TestMain {

    public static void main(String[] args) {
        //testTimeUse();
//        testStirngArr();
        testReflex();
    }

    private static void testReflex() {
        try {
            Class<?> classType=Class.forName("com.gszh.wis.tsp.task.HelloJob");
            Object object=classType.newInstance();
            System.out.println(object.getClass().getName());
            Method method=classType.getMethod("test",Map.class);
            method.invoke(object,new HashMap<String,String>() );
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

    //测试 String split 方法
    static void testStirngArr(){
        String a="hheheh";
        String b="bjijii;";
        String[] c=a.split(";");
        System.out.println(c[0]);
        String[] d=b.split(";");
        System.out.println(d[0]);
    }

    /**
     * 测试执行时间
     */
    private static void testTimeUse() {
        System.out.println(new Date());
        long flag=1L;
        List<Long> list= new ArrayList<Long>();
        for(int i = 0;i<20000000;i++){
            flag++;
            list.add(flag);
        }
        System.out.println(list.size()+"次计算任务");
        list.clear();
        System.out.println(new Date());
    }
}
