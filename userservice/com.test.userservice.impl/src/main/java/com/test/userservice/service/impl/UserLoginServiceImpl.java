package com.test.userservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.framework.util.ObjectGerateUtil;
import com.test.userservice.dao.UserLoginMapper;
import com.test.userservice.domain.UserLogin;
import com.test.userservice.service.inf.UserLoginService;
import com.test.userservice.so.UserLoginSo;
import com.test.userservice.vo.UserLoginVo;

/**
 * 日志基础服务
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginMapper userLoginMapper;

	private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public UserLoginVo insert(UserLogin po) {
		boolean flag = userLoginMapper.insert(po);
		if (!flag) {
			logger.error("insert userLogin fail");
			return null;
		}
		return ObjectGerateUtil.copyAndGenerateObj(userLoginMapper.selectPoByPrimaryKey(po.getId()), UserLoginVo.class);
	}

	@Override
	public List<UserLoginVo> selectVoBySo(UserLoginSo so) {
		return userLoginMapper.selectVoBySo(so);
	}

}
