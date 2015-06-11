package com.test.productservice.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource(value={"classpath:config/provider.xml"})
@EnableAutoConfiguration
@ComponentScan("com")
@PropertySource({ "file:${configPath}/${spring.profiles.active}/ncr.properties" })
public class CommonConfig{
	

}
