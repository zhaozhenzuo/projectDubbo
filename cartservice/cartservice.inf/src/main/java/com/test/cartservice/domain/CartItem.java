package com.test.cartservice.domain;

import java.io.Serializable;

/**
 * 购物车明细对象
 * 
 * userId及skuId唯一确定一条记录
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	// 自然主键
	private Long id;

	// 所属cart
	private Long cartId;

	private Long skuId;

	private Integer count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
