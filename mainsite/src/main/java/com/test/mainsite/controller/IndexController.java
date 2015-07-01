package com.test.mainsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.mainsite.UserFacade;
import com.test.mainsite.test.IpSeeker;

@Controller
public class IndexController {

	@Autowired
	private UserFacade userFacade;
	
	private static final IpSeeker ipSeeker=new IpSeeker();

	@RequestMapping(value={"/","/index"},method=RequestMethod.GET)
	String home() {
		int i=0;
		while(true){
			ipSeeker.put("ihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+i, i+"valueaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			i++;
		}
		
//		return "index";
	}
	
	@RequestMapping(value={"/manageIndex"},method=RequestMethod.GET)
	String manageHome() {
		return "manageIndex";
	}

}
