package com.gszh.wis.tsp.listener;

import com.gszh.wis.tsp.service.TaskJobManageService;
import com.gszh.wis.tsp.service.TaskJobStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 加载 spring IoC 容器后，启用的监听器
 * Created by chenzhixiong on 2016/8/12.
 */
public class BootManageListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TaskJobManageService taskJobManageService;
    @Autowired
    private TaskJobStateService taskJobStateService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // spring 和 springmvc 整合，监听器会加载两次
        // root application context 没有parent，他就是老大.即第一次加载
        if(event.getApplicationContext().getParent() == null) {
            this.taskJobManageService.regeistListener(taskJobStateService);
            this.taskJobManageService.startAll();
        }
    }
}
