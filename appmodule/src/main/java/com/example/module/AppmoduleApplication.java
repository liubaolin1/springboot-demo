package com.example.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ServletComponentScan
@MapperScan("com.example.moudule.mapper")
public class AppmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppmoduleApplication.class, args);
	}
}
