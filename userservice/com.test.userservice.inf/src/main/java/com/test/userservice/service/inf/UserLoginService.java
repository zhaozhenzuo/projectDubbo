package com.test.userservice.service.inf;

import java.util.List;

import com.test.userservice.domain.UserLogin;
import com.test.userservice.so.UserLoginSo;
import com.test.userservice.vo.UserLoginVo;

public interface UserLoginService {

	public UserLoginVo insert(UserLogin po);
	
	public List<UserLoginVo> selectVoBySo(UserLoginSo so);

}
