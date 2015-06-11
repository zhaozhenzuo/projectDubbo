package com.test.userservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 系统用户与角色关联表
 * @author hzzhaozhenzuo
 *
 */
@Table("ds_system_userrole")
public class SystemUserRole extends BaseBo{

	private static final long serialVersionUID = 1L;
	
	@ID("id")
	private Long id;
	
	@Column("userId")
	private Long userId;
	
	@Column("username")
	private String username;
	
	@Column("roleId")
	private Long roleId;
	
	@Column("rolename")
	private String rolename;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
