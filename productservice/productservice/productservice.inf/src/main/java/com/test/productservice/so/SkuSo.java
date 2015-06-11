package com.test.productservice.so;

import java.util.List;

import com.test.framework.so.BaseSo;

public class SkuSo extends BaseSo{

	private static final long serialVersionUID = 1L;
	
	private List<Long> skuIdList;
	
	private Long productId;

	public List<Long> getSkuIdList() {
		return skuIdList;
	}

	public void setSkuIdList(List<Long> skuIdList) {
		this.skuIdList = skuIdList;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
