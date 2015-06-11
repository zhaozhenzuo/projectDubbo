package com.test.promotionservice.vo;

import java.io.Serializable;

/**
 * 计算结果vo
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class CalResultVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private CartCalVo cartCalVo;

	public CartCalVo getCartCalVo() {
		return cartCalVo;
	}

	public void setCartCalVo(CartCalVo cartCalVo) {
		this.cartCalVo = cartCalVo;
	}

}
