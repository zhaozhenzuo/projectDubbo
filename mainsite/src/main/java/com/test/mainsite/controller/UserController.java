package com.test.mainsite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;
import com.test.mainsite.UserFacade;
import com.test.userservice.so.UserSearchSo;
import com.test.userservice.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserFacade userFacade;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/list")
	public String listCodeInfo(UserSearchSo so,Model model) {
		List<UserVo> voList = userFacade.selectVoBySo(so);
		model.addAttribute("res", voList);
		return "user/userList";
	}


	@RequestMapping("/search")
	@ResponseBody
	public ResponseResult<List<UserVo>> search(@RequestBody UserSearchSo so) {
		List<UserVo> userList = userFacade.selectVoBySo(so);
		return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, userList, null);
	}

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	@RequestMapping("/insert")
	public void insert(UserVo userVo, HttpServletRequest request, HttpServletResponse response) {
		UserVo res = userFacade.insert(userVo);
		try {
			if (res==null) {
				WebUtils.issueRedirect(request, response, "index.ftl");
			}else{
				request.setAttribute("errmsg", "注册失败");
				WebUtils.issueRedirect(request, response, "/user/register.ftl");
			}
		} catch (IOException e) {
			logger.error("redirect to index err", e);
		}
	}
}
