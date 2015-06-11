package com.test.DTO;

import com.test.cartservice.vo.CartItemVo;
import com.test.productservice.vo.ProductVo;

/**
 * 面向用户的购物车明细<br/>
 * 
 * 一种sku类别确定一条cart明细
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class CartItemDTO extends CartItemVo {

	private static final long serialVersionUID = 1L;

	// 当前sku所属商品
	private ProductVo productVo;

	// 原单价
	private double originalPriceOfOne;

	// 现售单价
	private double salePriceOfOne;

	// 对于当前sku类别小计
	private double totalPriceForCurrentSku;

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}

	public double getOriginalPriceOfOne() {
		return originalPriceOfOne;
	}

	public void setOriginalPriceOfOne(double originalPriceOfOne) {
		this.originalPriceOfOne = originalPriceOfOne;
	}

	public double getSalePriceOfOne() {
		return salePriceOfOne;
	}

	public void setSalePriceOfOne(double salePriceOfOne) {
		this.salePriceOfOne = salePriceOfOne;
	}

	public double getTotalPriceForCurrentSku() {
		return totalPriceForCurrentSku;
	}

	public void setTotalPriceForCurrentSku(double totalPriceForCurrentSku) {
		this.totalPriceForCurrentSku = totalPriceForCurrentSku;
	}

}
