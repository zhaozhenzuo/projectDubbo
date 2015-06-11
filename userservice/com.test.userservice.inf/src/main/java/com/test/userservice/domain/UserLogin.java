package com.test.userservice.domain;

import java.util.Date;
import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

@Table("ds_userlogin")
public class UserLogin extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;
	
	@Column("userId")
	private Long userId;
	
	@Column("userName")
	private String userName;
	
	@Column("lastLoginTime")
	private Date lastLoginTime;
	
	@Column("loginIp")
	private String loginIp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}
