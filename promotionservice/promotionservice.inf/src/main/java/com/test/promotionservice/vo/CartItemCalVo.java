package com.test.promotionservice.vo;

import java.io.Serializable;

public class CartItemCalVo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 当前sku所属商品
	private Long productId;
	
	private Long skuId;

	// 原单价
	private Double originalPriceOfOne;

	// 现售单价
	private Double salePriceOfOne;

	// 对于当前sku类别小计
	private Double totalPriceForCurrentSku;

	// 购买数量
	private Integer count;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Double getOriginalPriceOfOne() {
		return originalPriceOfOne;
	}

	public void setOriginalPriceOfOne(Double originalPriceOfOne) {
		this.originalPriceOfOne = originalPriceOfOne;
	}

	public Double getSalePriceOfOne() {
		return salePriceOfOne;
	}

	public void setSalePriceOfOne(Double salePriceOfOne) {
		this.salePriceOfOne = salePriceOfOne;
	}

	public Double getTotalPriceForCurrentSku() {
		return totalPriceForCurrentSku;
	}

	public void setTotalPriceForCurrentSku(Double totalPriceForCurrentSku) {
		this.totalPriceForCurrentSku = totalPriceForCurrentSku;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
