package com.test.userservice.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.framework.util.ObjectGerateUtil;
import com.test.userservice.dao.UserMapper;
import com.test.userservice.domain.User;
import com.test.userservice.service.inf.UserLoginService;
import com.test.userservice.service.inf.UserService;
import com.test.userservice.so.UserSearchSo;
import com.test.userservice.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserLoginService userLoginService;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public UserVo insert(UserVo vo) {
		User po = new User();
		BeanUtils.copyProperties(vo, po);

		boolean flag = userMapper.insert(po);
		if (!flag) {
			logger.error("insert user fail");
			return null;
		}

		return ObjectGerateUtil.copyAndGenerateObj(userMapper.selectPoByPrimaryKey(po.getId()), UserVo.class);
	}

	@Override
	public List<User> selectPoBySo(UserSearchSo so) {
		return userMapper.selectPoBySo(so);
	}

	@Override
	public List<UserVo> selectVoBySo(UserSearchSo so) {
		return userMapper.selectVoBySo(so);
	}

	@Override
	public User login(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return null;
		}
		UserSearchSo so = new UserSearchSo();
		so.setUsername(username);
		so.setPassword(password);

		List<User> userList = userMapper.selectPoBySo(so);
		if (userList == null || userList.size() <= 0) {
			return null;
		}
		return userList.get(0);
	}

}
