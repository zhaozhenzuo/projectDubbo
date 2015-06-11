package com.test.mainsite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.test.framework.annotation.Facade;

@Facade
public class CacheFacadeTestImpl implements CacheFacadeTest{
	
	private static final Logger logger=LoggerFactory.getLogger(CacheFacadeTestImpl.class);;
	
	
	@Override
	@Cacheable("product1234")
	public String getContent1(Long userId) {
		logger.info("===getContent product from database");
		return "hi1234,"+userId;
	}
	
	@Override
	@Cacheable("product1234")
	public String getContent2(Long userId) {
		logger.info("===getContent2 product from database");
		return "* 2 hi1234,"+userId;
	}

}
