package com.test.userservice.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource(value={"classpath:config/provider.xml"})
@PropertySource({ "file:${configPath}/${spring.profiles.active}/ncr.properties" })
public class CommonConfig{
	

}
