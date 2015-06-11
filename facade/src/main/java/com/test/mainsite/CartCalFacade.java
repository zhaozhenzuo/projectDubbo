package com.test.mainsite;

import java.util.List;

import com.test.DTO.CartDTO;

public interface CartCalFacade {

	/**
	 * 根据优惠卷计算购物车
	 * 
	 * @param cartDTO
	 *            用户的购物车
	 * @param promotionIdList
	 *            使用的优惠卷
	 * @return
	 */
	public CartDTO calCartWithPromotion(CartDTO cartDTO, List<Long> promotionIdList);

	/**
	 * 不带优惠卷计算
	 * 
	 * @param cartDTO
	 * @return
	 */
	public CartDTO calCartNoPromotion(CartDTO cartDTO);

}
