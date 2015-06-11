package com.test.userservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 系统用户角色
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_system_role")
public class SystemRole extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	// 角色名称
	@Column("name")
	private String name;

	@Column("remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
