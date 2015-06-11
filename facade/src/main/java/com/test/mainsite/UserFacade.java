package com.test.mainsite;

import java.util.List;

import com.test.userservice.domain.User;
import com.test.userservice.so.UserSearchSo;
import com.test.userservice.vo.UserVo;

/**
 * 
 * @author hzzhaozhenzuo
 *
 */
public interface UserFacade {
	
	public UserVo insert(UserVo vo);
	
	public List<User> selectPoBySo(UserSearchSo so);
	
	public List<UserVo> selectVoBySo(UserSearchSo so);

}
