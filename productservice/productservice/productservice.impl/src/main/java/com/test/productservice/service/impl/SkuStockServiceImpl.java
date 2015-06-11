package com.test.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.dao.SkuStockMapper;
import com.test.productservice.domain.SkuStock;
import com.test.productservice.service.inf.SkuStockService;
import com.test.productservice.so.SkuStockSo;
import com.test.productservice.vo.SkuStockVo;

@Service("skuStockService")
public class SkuStockServiceImpl implements SkuStockService {

	@Autowired
	private SkuStockMapper skuStockMapper;

	@Override
	public SkuStockVo insert(SkuStockVo vo) {
		SkuStockVo resVo = ObjectGerateUtil.insertAndReturnTargetClassInstance(vo, SkuStockVo.class, skuStockMapper);
		return resVo;
	}

	@Override
	public SkuStockVo update(SkuStockVo vo) {
		return ObjectGerateUtil.updateAndReturnTargetClassInstance(vo, SkuStockVo.class, skuStockMapper);
	}

	@Override
	public List<SkuStock> selectPoBySo(SkuStockSo so) {
		return skuStockMapper.selectPoBySo(so);
	}

	@Override
	public List<SkuStockVo> selectVoBySo(SkuStockSo so) {
		return skuStockMapper.selectVoBySo(so);
	}

	@Override
	public Long selectCount(SkuStockSo so) {
		return skuStockMapper.selectCount(so);
	}

	@Override
	public boolean delete(Long id) {
		return skuStockMapper.delete(id);
	}

	@Override
	public SkuStock selectPoById(Long id) {
		return skuStockMapper.selectPoByPrimaryKey(id);
	}

	@Override
	public boolean deleteBySkuIdList(List<Long> skuIdList) {
		return skuStockMapper.deleteByIdList(skuIdList);
	}

}
