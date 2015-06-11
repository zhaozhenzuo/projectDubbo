package com.test.cartservice.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.cartservice.vo.CartItemVo;
import com.test.cartservice.vo.CartVo;
import com.test.framework.base.DataStoreInf;
import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	private DataStoreInf<Integer> dataStoreInf;

	private static final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);

	@Override
	public boolean addCartItem(Long userId, Long skuId, Integer count) {
		if (userId == null || skuId == null || count == null) {
			logger.error("userId,skuId,count must be set");
			return false;
		}

		String key = String.valueOf(userId);
		String field = String.valueOf(skuId);

		ResponseResult<Integer> result = dataStoreInf.getValueByKeyAndField(key, field);

		if (result.getCode() == CodeInfoClass.COMMON_FAIL) {
			logger.error("redis fail");
			return false;
		}

		if (CodeInfoClass.NOT_EXIST == result.getCode()) {
			// 不存在
			ResponseResult<Integer> resPutWhenNotExist = dataStoreInf.putToHashNotExist(key, field, count);
			return CodeInfoClass.SUCCESS == resPutWhenNotExist.getCode();
		}

		// 存在则增加指定field对应的value值
		Integer increaseInt = this.increaseSkuNumForCartOfUser(userId, skuId, count);
		return increaseInt > 0;
	}

	@Override
	public boolean deleteCartItem(Long userId, Long skuId) {
		if (userId == null || skuId == null) {
			logger.error("userId,skuId must be set");
			return false;
		}

		ResponseResult<Integer> res = dataStoreInf.deleteValueOfHashByKeyAndFieldWhenExist(String.valueOf(userId),
				String.valueOf(skuId));

		return CodeInfoClass.SUCCESS == res.getCode();
	}

	@Override
	public Integer increaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count) {
		if (userId == null || skuId == null || count == null) {
			logger.error("userId,skuId,count must be set");
			return CodeInfoClass.failInt;
		}
		ResponseResult<Integer> res = dataStoreInf.increaseValueByNum(String.valueOf(userId), String.valueOf(skuId),
				count);

		if (res.getCode() == CodeInfoClass.COMMON_FAIL) {
			return CodeInfoClass.failInt;
		}

		return res.getResult();
	}

	@Override
	public Integer descreaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count) {
		if (userId == null || skuId == null || count == null) {
			logger.error("userId,skuId,count must be set");
			return CodeInfoClass.failInt;
		}
		ResponseResult<Integer> res = dataStoreInf.decreaseValueByNum(String.valueOf(userId), String.valueOf(skuId),
				count);
		if (res.getCode() == CodeInfoClass.COMMON_FAIL) {
			return CodeInfoClass.failInt;
		}
		return res.getResult();
	}

	@Override
	public CartVo getCartVoByUserId(Long userId) {
		CartVo cartVo = new CartVo();
		if (userId == null) {
			logger.error("userId must be set");
			return cartVo;
		}

		Map<String, Integer> valueMap = dataStoreInf.getAllFieldAndValueFromHash(String.valueOf(userId));
		if (valueMap == null || valueMap.isEmpty()) {
			return cartVo;
		}

		for (Entry<String, Integer> entry : valueMap.entrySet()) {
			CartItemVo cartItemVo = new CartItemVo();
			cartItemVo.setSkuId(Long.valueOf(entry.getKey()));
			cartItemVo.setCount(entry.getValue());
			cartItemVo.setUserId(userId);

			cartVo.addCartItem(cartItemVo);
		}
		return cartVo;
	}

	@Override
	public Integer getCartCountByUserId(Long userId) {
		if (userId == null) {
			logger.error("userId must be set");
			return CodeInfoClass.failInt;
		}

		Map<String, Integer> valueMap = dataStoreInf.getAllFieldAndValueFromHash(String.valueOf(userId));
		if (valueMap == null) {
			return 0;
		}
		return valueMap.size();
	}

	@Override
	public ResponseResult<Integer> getSkuNumsByUserIdAndSkuId(Long userId, Long skuId) {
		if (userId == null || skuId == null) {
			logger.error("param is null");
			return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
		}

		ResponseResult<Integer> result = dataStoreInf.getValueByKeyAndField(String.valueOf(userId),
				String.valueOf(skuId));

		return result;
	}

}
