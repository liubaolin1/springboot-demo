package com.example.module;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.example.utils.common.Print.*;

@SpringBootApplication
/**
 * 加载配置
 */
@Configuration
/**
 * 控制过滤器器和拦截器
 */
@ServletComponentScan
/**
 * 定时任务用
 */
@EnableScheduling
public class AppmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppmoduleApplication.class, args);
		printRun();
	}
}
