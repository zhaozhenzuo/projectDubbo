package com.test.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource(value = { "classpath:config/dubbo-register.xml" })
@PropertySource({ "file:${configPath}/${spring.profiles.active}/url.properties"})
public class CommonConfigLoad {

}
