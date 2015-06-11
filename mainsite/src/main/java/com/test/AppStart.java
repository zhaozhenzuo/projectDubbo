package com.test;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan
@EnableAutoConfiguration(exclude={DataSource.class})
@ImportResource(value={"classpath:config/dubbo-register.xml","classpath:config/mvc-config.xml"})
public class AppStart {
	
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
}
