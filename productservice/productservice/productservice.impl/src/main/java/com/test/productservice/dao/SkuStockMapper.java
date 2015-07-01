package com.test.productservice.dao;

import java.util.List;

import com.test.framework.basemapper.BaseMapper;
import com.test.productservice.domain.SkuStock;
import com.test.productservice.vo.SkuStockVo;

public interface SkuStockMapper extends BaseMapper<SkuStock, SkuStockVo>{
	
	public boolean deleteByIdList(List<Long> skuIdList);
	
	/**
	 * 库存增减方法
	 * @param skuStock
	 * @return
	 */
	public int updateStockBySkuIdAndCountOfBuy(SkuStock skuStock);

}
