package com.test.cartservice.domain;

import java.io.Serializable;

/**
 * 一个用户在任一时刻只存在一条购物车记录
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	// 自然主键
	private Long id;

	// 用户id
	private Long userId;

	// 购物车最后更新时间
	private Long lastUpdateTime;

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

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
