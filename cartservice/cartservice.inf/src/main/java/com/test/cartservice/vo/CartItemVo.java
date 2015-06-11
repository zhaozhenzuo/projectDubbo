package com.test.cartservice.vo;

import com.test.cartservice.domain.CartItem;

public class CartItemVo extends CartItem{

	private static final long serialVersionUID = 1L;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
