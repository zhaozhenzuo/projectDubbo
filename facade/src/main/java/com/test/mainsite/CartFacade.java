package com.test.mainsite;

import com.test.DTO.CartDTO;

public interface CartFacade {

	/**
	 * 添加对应sku到购物车中
	 * 
	 * @param userId
	 * @param skuId
	 * @param count
	 *            　必须大于0
	 * @return
	 */
	public boolean addCartItem(Long userId, Long skuId, Integer count);

	/**
	 * 删除某个用户购物车中对应sku项
	 * 
	 * @param userId
	 * @param skuId
	 * @param count
	 *            　必须大于0
	 * @return
	 */
	public boolean deleteCartItem(Long userId, Long skuId);

	/**
	 * 将对应购物车中某样sku的数量增加count
	 * 
	 * @param userId
	 * @param skuId
	 * @param count
	 *            必须大于0
	 * @return
	 */
	public Integer increaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count);

	/**
	 * 将对应购物车中某样sku的数量减少count
	 * 
	 * @param userId
	 * @param skuId
	 * @param count
	 *            必须小于0
	 * @return
	 */
	public Integer descreaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count);

	/**
	 * 获取对应用户的购物车记录，返回所有skuId及对应数量
	 * 
	 * @param userId
	 * @return
	 */
	public CartDTO getCartDTOByUserId(Long userId);

	/**
	 * 获取用户购物车数量
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getCartCountByUserId(Long userId);

}
