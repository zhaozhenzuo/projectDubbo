package com.test.cartservice.impl;

import com.test.cartservice.vo.CartVo;
import com.test.framework.domain.ResponseResult;

/**
 * 购物车dao
 * 
 * 只完成最基础的curd
 * 
 * @author hzzhaozhenzuo
 * 
 */
public interface CartDao {

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
	public CartVo getCartVoByUserId(Long userId);

	/**
	 * 获取用户购物车中sku类别数量
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getCartCountByUserId(Long userId);

	/**
	 * 返回当前用户购买对应sku的数量
	 * 
	 * @param userId
	 * @param skuId
	 * @return 查询失败时返回code为:COMMON_FAIL,不存在元素时返回NOT_EXIST，成功则返回SUCCESS并返回结果
	 */
	public ResponseResult<Integer> getSkuNumsByUserIdAndSkuId(Long userId, Long skuId);

}
