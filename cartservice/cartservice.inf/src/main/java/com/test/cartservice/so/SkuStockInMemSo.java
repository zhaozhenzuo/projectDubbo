package com.test.cartservice.so;

import java.util.List;

import com.test.framework.so.BaseSo;

public class SkuStockInMemSo extends BaseSo{

	private static final long serialVersionUID = 1L;
	
	private List<Long> skuIdList;

	public List<Long> getSkuIdList() {
		return skuIdList;
	}

	public void setSkuIdList(List<Long> skuIdList) {
		this.skuIdList = skuIdList;
	}

}
