package com.test.security.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 自定义电商项目中用户身份实体
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class DSPrinciple implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userName;

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

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!this.getClass().isInstance(obj)) {
			return false;
		}

		Long id1 = this.getUserId();
		DSPrinciple target = (DSPrinciple) obj;
		Long id2 = target.getUserId();
		
		if (id1 == null) {
			return false;
		}

		return id1.equals(id2);
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getUserId()).hashCode();
	}

}
