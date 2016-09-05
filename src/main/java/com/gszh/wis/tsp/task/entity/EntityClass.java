package com.gszh.wis.tsp.task.entity;

import java.util.Map;

/**
 * Created by chenzhixiong on 2016/8/30.
 */
public class EntityClass {
    public void execute(Map<String,Object> map){
        System.out.println(map.get("fireTime"));
        System.out.println("+++");
        try {
            int i=0;
            while (i<600){
                Thread.sleep(1000);
                i++;
                System.out.println("sleep "+i);
            }
            Thread.sleep(1);
            System.out.println("---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause(Map<String,Object> map){
        System.out.println(map.size()+" pause");
    }
    public void resume(Map<String,Object> map){
        System.out.println(map.size()+" resume");
    }
    public void stop(Map<String,Object> map){
        System.out.println(map.size()+" stop");
    }
}
