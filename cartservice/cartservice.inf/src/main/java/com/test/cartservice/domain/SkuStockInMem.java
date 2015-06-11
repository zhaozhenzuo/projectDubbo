package com.test.cartservice.domain;

import java.io.Serializable;

/**
 * 内存中的sku库存
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class SkuStockInMem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long skuId;

	private Integer stockCount;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

}
