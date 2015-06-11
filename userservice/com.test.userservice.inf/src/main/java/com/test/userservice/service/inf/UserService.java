package com.test.userservice.service.inf;


import java.util.List;

import com.test.userservice.domain.User;
import com.test.userservice.so.UserSearchSo;
import com.test.userservice.vo.UserVo;

public interface UserService {
	
	public UserVo insert(UserVo vo);
	
	public List<User> selectPoBySo(UserSearchSo so);
	
	public List<UserVo> selectVoBySo(UserSearchSo so);
	
	public User login(String username,String password);

}
