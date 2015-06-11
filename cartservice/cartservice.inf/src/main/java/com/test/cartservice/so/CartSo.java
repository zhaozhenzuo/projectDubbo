package com.test.cartservice.so;

import java.util.List;

import com.test.cartservice.vo.CartItemVo;
import com.test.framework.so.BaseSo;

public class CartSo extends BaseSo {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private List<CartItemVo> cartItemSelected;
	
	private List<Long> promotionIdUseList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<CartItemVo> getCartItemSelected() {
		return cartItemSelected;
	}

	public void setCartItemSelected(List<CartItemVo> cartItemSelected) {
		this.cartItemSelected = cartItemSelected;
	}

	public List<Long> getPromotionIdUseList() {
		return promotionIdUseList;
	}

	public void setPromotionIdUseList(List<Long> promotionIdUseList) {
		this.promotionIdUseList = promotionIdUseList;
	}

}
