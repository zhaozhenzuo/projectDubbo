package com.test.promotionservice.vo;

import java.io.Serializable;
import java.util.List;

public class CartCalVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;

	// 总价格,不含运费
	private double totalPrice;

	private List<CartItemCalVo> cartItemCalVoList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CartItemCalVo> getCartItemCalVoList() {
		return cartItemCalVoList;
	}

	public void setCartItemCalVoList(List<CartItemCalVo> cartItemCalVoList) {
		this.cartItemCalVoList = cartItemCalVoList;
	}

}
