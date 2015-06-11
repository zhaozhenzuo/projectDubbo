package com.test.mainsite.other;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;

@Controller
public class ChannelController implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("i");
	}
	
	

}
