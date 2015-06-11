package com.test.security.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.framework.util.CodeInfoClass;
import com.test.security.domain.DSPrinciple;

public class SecurityContextUtils {

	private static final Logger logger = LoggerFactory.getLogger(SecurityContextUtils.class);

	public static DSPrinciple getUserPrinciple() {
		Subject currentUser = SecurityUtils.getSubject();
		return (DSPrinciple) currentUser;
	}

	public static Long getUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser == null || currentUser.getPrincipal() == null
				|| !(currentUser.getPrincipal() instanceof DSPrinciple)) {
			logger.warn("currentUser is null or is not dsPrinciple");
			return CodeInfoClass.failLong;
		}

		DSPrinciple dsPrinciple = (DSPrinciple) currentUser.getPrincipal();
		return dsPrinciple.getUserId();
	}
}
