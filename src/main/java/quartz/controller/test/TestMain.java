package quartz.controller.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenzhixiong on 2016/8/12.
 */
public class TestMain {
    public static void main(String[] args) {
        testTimeUse();
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
