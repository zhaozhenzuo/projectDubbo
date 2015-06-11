//package com.test.mainsite.controller;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.test.framework.domain.ResponseResult;
//import com.test.framework.util.CodeInfoClass;
//import com.test.framework.util.ResponseUtil;
//
//@Controller
//public class CacheController {
//
//	@Autowired
//	private CacheManager cacheManager;
//	
//	@RequestMapping("/cache/list")
//	@ResponseBody
//	public ResponseResult<Collection<String>> cacheList(){ 
//		Collection<String> cacheNames=cacheManager.getCacheNames();
//		if(cacheNames==null || cacheNames.isEmpty()){
//			return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS);
//		}
//		return ResponseUtil.generateSuccess(cacheNames);
//		
//	}
//	
//}
