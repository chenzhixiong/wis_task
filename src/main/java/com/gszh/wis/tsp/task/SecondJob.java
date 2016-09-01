package com.gszh.wis.tsp.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试任务执行时间在6-9s之间
 * Created by chenzhixiong on 2016/8/12.
 */
@DisallowConcurrentExecution
public class SecondJob implements Job{
    /**
     * 测试执行时间
     */
    private static void test(Map<String,Object> map) {

        System.out.println(new Date());
        long flag=1L;
        List<Long> list= new ArrayList<Long>();
        for(int i = 0;i<30000000;i++){
            flag++;
            list.add(flag);
        }
        System.out.println(list.size()+"次计算任务");
        list.clear();
        System.out.println(new Date());
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, Object> map = context.getMergedJobDataMap().getWrappedMap();
        test(map);
    }
}
