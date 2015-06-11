package com.test.cartservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cartservice.service.SkuStockInMemService;
import com.test.cartservice.vo.SkuStockInMemVo;

@Service("skuStockInMemService")
public class SkuStockInMemServiceImpl implements SkuStockInMemService {

	@Autowired
	private SkuStockInMemDao<Integer> skuStockInMemDao;

	@Override
	public boolean insert(SkuStockInMemVo vo) {
		return skuStockInMemDao.insert(vo);
	}

	@Override
	public boolean deleteBySkuId(Long skuId) {
		return skuStockInMemDao.deleteBySkuId(skuId);
	}

	@Override
	public Integer increaseStock(Long skuId, Integer num) {
		return skuStockInMemDao.increaseStock(skuId, num);
	}

	@Override
	public Integer decreaseStock(Long skuId, Integer num) {
		return skuStockInMemDao.decreaseStock(skuId, num);
	}

	@Override
	public Integer getStockBySkuId(Long skuId) {
//		skuStockInMemDao.getStockBySkuId(skuId);
		throw new UnsupportedOperationException("not support getStockBySkuId");
	}

	@Override
	public List<SkuStockInMemVo> getStocksBySkuIdList(List<Long> skuIdList) {
		return skuStockInMemDao.getStocksBySkuIdList(skuIdList);
	}

	@Override
	public boolean deleteBySkuIdList(List<Long> skuIdList) {
		return skuStockInMemDao.deleteBySkuIdList(skuIdList);
	}

}
