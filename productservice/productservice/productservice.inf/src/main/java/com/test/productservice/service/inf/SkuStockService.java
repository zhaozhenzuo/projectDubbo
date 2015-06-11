package com.test.productservice.service.inf;

import java.util.List;

import com.test.framework.base.BaseService;
import com.test.productservice.domain.SkuStock;
import com.test.productservice.so.SkuStockSo;
import com.test.productservice.vo.SkuStockVo;

public interface SkuStockService extends BaseService<SkuStock, SkuStockVo, SkuStockSo> {

	/**
	 * 删除数据库中对应sku的库存记录
	 * 
	 * @param skuIdList
	 * @return
	 */
	public boolean deleteBySkuIdList(List<Long> skuIdList);

}
