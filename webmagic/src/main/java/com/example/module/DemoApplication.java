package com.example.module;

import com.example.module.pageprocessor.AntServiceProcess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Spider;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	public void geturl(AntServiceProcess antServiceProcess){
		Spider.create(antServiceProcess)
				//开始抓
				.addUrl("")
				//开启5个线程抓取
				.thread(100)
				//启动爬虫
				.run();
	}
}
