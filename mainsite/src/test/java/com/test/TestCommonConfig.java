package com.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,FreeMarkerAutoConfiguration.class})
@ImportResource(value={"classpath:config/redisConfig.xml"})
public class TestCommonConfig{
	

}
