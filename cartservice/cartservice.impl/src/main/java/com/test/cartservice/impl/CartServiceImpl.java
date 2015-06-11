package com.test.cartservice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cartservice.service.CartService;
import com.test.cartservice.vo.CartVo;
import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private SkuStockInMemDao<Integer> skuStockInMemDao;

	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public boolean addCartItem(Long userId, Long skuId, Integer count) {
		if (userId == null || skuId == null || count == null || count <= 0) {
			logger.error("param is null,addCartItem");
			return false;
		}

		ResponseResult<Integer> result = skuStockInMemDao.getStockBySkuId(skuId);
		if (result.getCode() != CodeInfoClass.SUCCESS) {
			logger.warn("系统异常或库存不足,skuId:" + skuId + ",code:" + result.getCode());
			return false;
		}

		// 减库存
		Integer skuStockAfter = skuStockInMemDao.decreaseStock(skuId, count);
		if (skuStockAfter == CodeInfoClass.failInt) {
			logger.error("decrease stock err,skuId:" + skuId + ",userId:" + userId);
			return false;
		} else if (skuStockAfter < 0) {
			// 并发操作导致库存为负数，加回去
			logger.error("skuStockAfter is less than 0,skuId:" + skuId);
			Integer increaseFlag = skuStockInMemDao.increaseStock(skuId, count);
			if (increaseFlag == CodeInfoClass.failInt) {
				logger.error("increase sku stock err,skuId:" + skuId);
			}
			return false;
		}

		// 加入购物车
		boolean addCartFlag = cartDao.addCartItem(userId, skuId, count);
		if (!addCartFlag) {
			logger.error("add cart err");
			// 恢复库存
			Integer resNumIncrease = skuStockInMemDao.increaseStock(skuId, count);
			if (resNumIncrease < 0 || resNumIncrease == CodeInfoClass.failInt) {
				logger.error("try to re increase stock err,skuId:" + skuId);
			}
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteCartItem(Long userId, Long skuId) {
		// 取出用户之前购买该类商品数量
		ResponseResult<Integer> oldCartItemResult = cartDao.getSkuNumsByUserIdAndSkuId(userId, skuId);
		if (oldCartItemResult.getCode() == CodeInfoClass.COMMON_FAIL) {
			// 底层存储查询失败
			logger.error("search stock fail in deleteCartItem,userId:" + userId + ",skuId:" + skuId);
			return false;
		}

		boolean cartDeleteFlag = cartDao.deleteCartItem(userId, skuId);
		if (!cartDeleteFlag) {
			logger.error("delete cart err,userId:" + userId + ",skuId:" + skuId);
			return false;
		}

		// 将库存加回到内存,失败则由库存同步job去同步回库存
		ResponseResult<Integer> oldStockResultInMem = skuStockInMemDao.getStockBySkuId(skuId);
		if (oldStockResultInMem.getCode() == CodeInfoClass.COMMON_FAIL
				|| oldStockResultInMem.getCode() == CodeInfoClass.NOT_EXIST) {

			logger.error("oldStock is err or is not exist,code:" + oldStockResultInMem.getCode() + ",skuId:" + skuId);
			return true;
		}
		Integer increaseStockFlag = skuStockInMemDao.increaseStock(skuId, oldCartItemResult.getResult());
		if (increaseStockFlag == null || increaseStockFlag < 0 || increaseStockFlag == CodeInfoClass.failInt) {
			logger.error("increaseStock err");
			return true;
		}
		
		return true;
	}

	@Override
	public Integer increaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count) {
		ResponseResult<Integer> result = skuStockInMemDao.getStockBySkuId(skuId);
		if (result.getCode() != CodeInfoClass.SUCCESS) {
			logger.error("err,code:" + result.getCode() + ",skuId:" + skuId);
			return CodeInfoClass.failInt;
		}

		if (result.getResult() == null || result.getResult() < count) {
			logger.warn("库存不足,skuId:" + skuId);
			return CodeInfoClass.failInt;
		}

		// 减库存
		Integer skuStockAfter = skuStockInMemDao.decreaseStock(skuId, count);
		if (skuStockAfter == CodeInfoClass.failInt) {
			logger.error("decrease stock err,skuId:" + skuId + ",userId:" + userId);
			return CodeInfoClass.failInt;
		} else if (skuStockAfter < 0) {
			// 并发操作导致库存为负数，加回去
			logger.error("skuStockAfter is less than 0,skuId:" + skuId);
			Integer increaseFlag = skuStockInMemDao.increaseStock(skuId, count);
			if (increaseFlag == CodeInfoClass.failInt) {
				logger.error("increase sku stock err,skuId:" + skuId);
			}
			return CodeInfoClass.failInt;
		}

		return cartDao.increaseSkuNumForCartOfUser(userId, skuId, count);
	}

	@Override
	public Integer descreaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count) {
		// 放回库存到内存中
		Integer skuStockAfter = skuStockInMemDao.increaseStock(skuId, count);
		if (skuStockAfter == CodeInfoClass.failInt) {
			logger.error("decrease stock err,skuId:" + skuId + ",userId:" + userId);
			return CodeInfoClass.failInt;
		}

		Integer cartSkuInteger = cartDao.descreaseSkuNumForCartOfUser(userId, skuId, count);
		if (cartSkuInteger == CodeInfoClass.failInt) {
			logger.error("fail increase cart sku,skuId:" + skuId + ",userId:" + userId);
		}
		return cartSkuInteger;
	}

	@Override
	public CartVo getCartVoByUserId(Long userId) {
		return cartDao.getCartVoByUserId(userId);
	}

	@Override
	public Integer getCartCountByUserId(Long userId) {
		return cartDao.getCartCountByUserId(userId);
	}

}
