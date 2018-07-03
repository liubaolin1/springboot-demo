package com.example.module;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.example.utils.common.Print.*;

@SpringBootApplication
/**
 * 加载配置
 */
@Configuration
/**
 * 定时任务用
 */
@EnableScheduling
/**
 * druid用
 */
@ServletComponentScan("com.example.module.config")
/**
 * swagger用
 */
@ComponentScan(basePackages={"com.example.utils","com.example.module"})
public class AppmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppmoduleApplication.class, args);
		printRun();
	}
}
