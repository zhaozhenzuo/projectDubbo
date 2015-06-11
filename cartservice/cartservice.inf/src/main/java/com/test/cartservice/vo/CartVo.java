package com.test.cartservice.vo;

import java.util.ArrayList;
import java.util.List;
import com.test.cartservice.domain.Cart;

public class CartVo extends Cart {

	private static final long serialVersionUID = 1L;

	// 购物车明细
	private List<CartItemVo> cartItemVoList = new ArrayList<CartItemVo>();

	// 无效购物车明细
	private List<CartItemVo> invaideItemVoList = new ArrayList<CartItemVo>();

	public List<CartItemVo> getCartItemVoList() {
		return cartItemVoList;
	}

	public void setCartItemVoList(List<CartItemVo> cartItemVoList) {
		this.cartItemVoList = cartItemVoList;
	}

	public CartVo addCartItem(CartItemVo cartItemVo) {
		cartItemVoList.add(cartItemVo);
		return this;
	}

	public List<CartItemVo> getInvaideItemVoList() {
		return invaideItemVoList;
	}

	public void setInvaideItemVoList(List<CartItemVo> invaideItemVoList) {
		this.invaideItemVoList = invaideItemVoList;
	}

}
