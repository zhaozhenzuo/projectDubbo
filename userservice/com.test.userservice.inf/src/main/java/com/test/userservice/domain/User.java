package com.test.userservice.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 普通用户
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_user")
public class User extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	@Column("username")
	private String username;

	@Column("password")
	private String password;

	@Column("address")
	private String address;

	@Column("phone")
	private String phone;

	@Column("enabled")
	private Boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		return this.getUsername().equalsIgnoreCase(((User) obj).getUsername());
	}

	public int hashCode() {
		int result = new HashCodeBuilder(17, 37).append(this.getUsername()).hashCode();
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
