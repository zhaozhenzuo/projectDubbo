package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:config/user-consumer.xml"})
public class FacadeConfig implements InitializingBean{
	
	private static final Logger logger=LoggerFactory.getLogger(FacadeConfig.class);

	public void afterPropertiesSet() throws Exception {
		logger.info("ok");
	}

}
