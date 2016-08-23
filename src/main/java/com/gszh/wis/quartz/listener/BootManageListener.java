package com.gszh.wis.quartz.listener;

import com.gszh.wis.quartz.service.TaskJobManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 加载 spring IoC 容器后，启用的监听器
 * Created by chenzhixiong on 2016/8/12.
 */
@Component
public class BootManageListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TaskJobManageService taskJobManageService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {//root application context 没有parent，他就是老大.
//            this.taskJobManageService.regeistListener();
//            this.taskJobManageService.startAll();
        }
    }
}
