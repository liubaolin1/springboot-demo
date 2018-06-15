package com.example.module.timetask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 定时任务
 * 定时任务一般都写在任务调度框架中
 * 这里只是写一个简单的例子
 * @author: liubao
 * @Date: Created in 2018/6/15 9:05
 */
@Component
public class Timetask {
    /**
     * 每天的凌晨1点执行  0 0 1 1/1 * ?
     * cron表达式必须是6位  最后要有空格
     * 30秒 跑一次  0/30 * * * * ?
     */
    private long sid = 0;
    //@Scheduled(cron="0/5 * * * * ? ")
    public void Demo1(){
        sid++;
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"  定时任务执行"+sid+"次");
    }
}
