package com.test.cartservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.cartservice.constant.CartConstant;
import com.test.cartservice.vo.SkuStockInMemVo;
import com.test.framework.base.DataStoreInf;
import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;

@Repository
public class SkuStockInMemDaoImpl implements SkuStockInMemDao<Integer> {

	@Autowired
	private DataStoreInf<Integer> dataStore;

	private static final Logger logger = LoggerFactory.getLogger(SkuStockInMemDaoImpl.class);

	@Override
	public boolean insert(SkuStockInMemVo vo) {
		if (vo == null || vo.getSkuId() == null || vo.getStockCount() < 0) {
			logger.error("vo or stockCount is null,skuId:" + vo.getSkuId());
			return false;
		}

		ResponseResult<Integer> result = dataStore.putToHashNotExist(CartConstant.SKU_STOCK_NAMESPACE,
				String.valueOf(vo.getSkuId()), vo.getStockCount());

		return result.getCode() == CodeInfoClass.SUCCESS;

	}

	@Override
	public boolean deleteBySkuId(Long skuId) {
		if (skuId == null) {
			logger.error("skuId is null");
			return false;
		}

		ResponseResult<Integer> result = dataStore.deleteValueOfHashByKeyAndFieldWhenExist(
				CartConstant.SKU_STOCK_NAMESPACE, String.valueOf(skuId));

		return result.getCode() == CodeInfoClass.SUCCESS;
	}

	@Override
	public Integer increaseStock(Long skuId, Integer num) {
		if (skuId == null || num == null || num <= 0) {
			logger.error("skuId,num is null or num is less than 0,skuId:" + skuId + ",num:" + num);
			return CodeInfoClass.failInt;
		}
		ResponseResult<Integer> res = dataStore.increaseValueByNum(CartConstant.SKU_STOCK_NAMESPACE,
				String.valueOf(skuId), num);

		if (res.getCode() == CodeInfoClass.COMMON_FAIL) {
			logger.error("increaseStock err,skuId:" + skuId + ",num:" + num);
			return CodeInfoClass.failInt;
		}
		return res.getResult();
	}

	@Override
	public Integer decreaseStock(Long skuId, Integer num) {
		if (skuId == null || num == null || num <= 0) {
			logger.error("skuId,num is null or num is less than 0,skuId:" + skuId + ",num:" + num);
			return CodeInfoClass.failInt;
		}
		ResponseResult<Integer> res = dataStore.decreaseValueByNum(CartConstant.SKU_STOCK_NAMESPACE,
				String.valueOf(skuId), num);

		if (res.getCode() == CodeInfoClass.COMMON_FAIL) {
			logger.error("decreaseStock err,skuId:" + skuId + ",num:" + num);
			return CodeInfoClass.failInt;
		}
		return res.getResult();
	}

	@Override
	public ResponseResult<Integer> getStockBySkuId(Long skuId) {
		if (skuId == null) {
			logger.error("skuId is null");
			return ResponseUtil.generateError("skuId is null");
		}
		ResponseResult<Integer> result = dataStore.getValueByKeyAndField(CartConstant.SKU_STOCK_NAMESPACE,
				String.valueOf(skuId));
		return result;
	}

	@Override
	public List<SkuStockInMemVo> getStocksBySkuIdList(List<Long> skuIdList) {
		if (skuIdList == null || skuIdList.size() <= 0) {
			logger.error("skuIdList is null or no elements");
			return null;
		}

		List<SkuStockInMemVo> skuStockInMemVoList = new ArrayList<SkuStockInMemVo>();
		for (Long skuId : skuIdList) {
			ResponseResult<Integer> result = dataStore.getValueByKeyAndField(CartConstant.SKU_STOCK_NAMESPACE,
					String.valueOf(skuId));

			if (result.getCode() == CodeInfoClass.COMMON_FAIL || result.getCode() == CodeInfoClass.NOT_EXIST) {
				logger.error("system err or no stockCount found in mem,skuId:" + skuId + ",code:" + result.getCode());
				continue;
			}
			SkuStockInMemVo vo = new SkuStockInMemVo();
			vo.setSkuId(skuId);
			vo.setStockCount(result.getResult());
			skuStockInMemVoList.add(vo);
		}
		return skuStockInMemVoList;
	}

	@Override
	public boolean deleteBySkuIdList(List<Long> skuIdList) {
		if (skuIdList == null || skuIdList.size() <= 0) {
			logger.warn("skuIdList is null");
			return true;
		}

		boolean allSuccessFlag = true;
		for (Long skuId : skuIdList) {
			if (!this.deleteBySkuId(skuId)) {
				logger.error("delete stock from mem fail,skuId:" + skuId);
				allSuccessFlag = false;
			}
		}
		return allSuccessFlag;
	}

}
