package com.test.mainsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.framework.annotation.Facade;
import com.test.userservice.domain.User;
import com.test.userservice.service.inf.UserService;
import com.test.userservice.so.UserSearchSo;
import com.test.userservice.vo.UserVo;

@Facade
public class UserFacadeImpl implements UserFacade {

	@Autowired
	private UserService userService;

	@Override
	public UserVo insert(UserVo vo) {
		return userService.insert(vo);
	}

	@Override
	public List<User> selectPoBySo(UserSearchSo so) {
		return userService.selectPoBySo(so);
	}

	@Override
	public List<UserVo> selectVoBySo(UserSearchSo so) {
		return userService.selectVoBySo(so);
	}

}
