package com.test.productservice.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={"classpath:config/provider.xml"})
public class CommonConfig{
	

}
