package com.test.productservice.vo;

import java.util.List;

import com.test.productservice.domain.Product;

public class ProductVo extends Product{

	private static final long serialVersionUID = 1L;
	
	private List<SkuVo> skuVoList;

	public List<SkuVo> getSkuVoList() {
		return skuVoList;
	}

	public void setSkuVoList(List<SkuVo> skuVoList) {
		this.skuVoList = skuVoList;
	}

}
