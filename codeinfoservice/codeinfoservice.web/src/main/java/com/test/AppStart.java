package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class AppStart {
	
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
}
