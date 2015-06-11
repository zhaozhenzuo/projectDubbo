package com.test.mainsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.test.mainsite.UserFacade;

@Controller
public class IndexController {

	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value={"/","/index"},method=RequestMethod.GET)
	String home() {
		return "index";
	}
	
	@RequestMapping(value={"/manageIndex"},method=RequestMethod.GET)
	String manageHome() {
		return "manageIndex";
	}

}
