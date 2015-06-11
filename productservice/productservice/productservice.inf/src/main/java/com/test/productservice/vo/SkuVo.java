package com.test.productservice.vo;

import com.test.productservice.domain.Sku;

public class SkuVo extends Sku {

	private static final long serialVersionUID = 1L;

	private ProductVo productVo;

	private SkuAttributeVo colorAttributeVo;

	private SkuAttributeVo sizeAttributeVo;

	// 数据库中的库存量
	private Integer stockCount;

	// 内存中的库存量
	private Integer stockCountInMem;
	
	private Double marketPriceDouble;
	
	private Double salePriceDouble;

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}

	public SkuAttributeVo getColorAttributeVo() {
		return colorAttributeVo;
	}

	public void setColorAttributeVo(SkuAttributeVo colorAttributeVo) {
		this.colorAttributeVo = colorAttributeVo;
	}

	public SkuAttributeVo getSizeAttributeVo() {
		return sizeAttributeVo;
	}

	public void setSizeAttributeVo(SkuAttributeVo sizeAttributeVo) {
		this.sizeAttributeVo = sizeAttributeVo;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Integer getStockCountInMem() {
		return stockCountInMem;
	}

	public void setStockCountInMem(Integer stockCountInMem) {
		this.stockCountInMem = stockCountInMem;
	}

	public Double getMarketPriceDouble() {
		return marketPriceDouble;
	}
	
	public Double getSalePriceDouble() {
		return salePriceDouble;
	}

	public void setMarketPriceDouble(Double marketPriceDouble) {
		this.marketPriceDouble = super.getMarketPrice() != null ? Double.valueOf(super.getMarketPrice()) : 0D;
	}

	public void setSalePriceDouble(Double salePriceDouble) {
		this.salePriceDouble = super.getSalePrice() != null ? Double.valueOf(super.getSalePrice()) : 0D;
	}

}
