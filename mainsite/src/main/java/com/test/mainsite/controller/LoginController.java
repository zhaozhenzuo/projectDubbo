package com.test.mainsite.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.framework.util.CodeInfoClass;
import com.test.security.util.SecurityContextUtils;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private static final String FAIL_MSG = "用户名或密码有误";

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		try {
			WebUtils.issueRedirect(request, response, "index.ftl");
		} catch (IOException e) {
			logger.error("logout redirect err", e);
		}
	}

	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userid = null;
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		boolean hasErr = false;
		try {
			currentUser.login(token);
			userid=SecurityContextUtils.getUserId().toString();
			session.setAttribute(CodeInfoClass.USER_ID, userid);
			session.setAttribute(CodeInfoClass.USER_NAME, username);

			WebUtils.issueRedirect(request, response, "index.ftl");
		} catch (UnknownAccountException uae) {
			hasErr = true;
			logger.info("用户认证失败:" + "username wasn't in the system.");
		} catch (IncorrectCredentialsException ice) {
			hasErr = true;
			logger.info("用户认证失败:" + "password didn't match.");
		} catch (LockedAccountException lae) {
			hasErr = true;
			logger.info("用户认证失败:" + "account for that username is locked - can't login.");
		} catch (AuthenticationException ae) {
			hasErr = true;
			logger.info("用户认证失败:" + "unexpected condition.");
		}
		if (hasErr) {
			request.getSession().setAttribute("errmsg", FAIL_MSG);
		}
		WebUtils.issueRedirect(request, response, "login.ftl");

	}
}
