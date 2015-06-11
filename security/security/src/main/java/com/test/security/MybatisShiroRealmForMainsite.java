package com.test.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.security.domain.DSPrinciple;
import com.test.userservice.domain.User;
import com.test.userservice.service.inf.UserService;

/**
 * 主站用户的realm
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class MybatisShiroRealmForMainsite extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		throw new RuntimeException("main site unsupported doGetAuthorizationInfo");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) atoken;
		String userName = token.getUsername();
		if (userName != null && !"".equals(userName)) {
			User user = userService.login(token.getUsername(), String.valueOf(token.getPassword()));

			if (user != null) {
				DSPrinciple dsUser = new DSPrinciple();
				dsUser.setUserId(user.getId());
				dsUser.setUserName(user.getUsername());
				return new SimpleAuthenticationInfo(dsUser, user.getPassword(), getName());
			}

		}
		return null;
	}
}
