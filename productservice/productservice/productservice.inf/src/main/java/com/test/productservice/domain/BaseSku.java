package com.test.productservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.domain.BaseBo;

public abstract class BaseSku extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	@Column("barcode")
	private String barcode;

	// 所属商品id
	@Column("productId")
	private Long productId;

	// 市场价
	@Column("marketPrice")
	private String marketPrice;

	// 销售价
	@Column("salePrice")
	private String salePrice;

	// 图片url
	@Column("picUrls")
	private String picUrls;
	
	//原始库存
	@Column("orginalStock")
	private Integer orginalStock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public Integer getOrginalStock() {
		return orginalStock;
	}

	public void setOrginalStock(Integer orginalStock) {
		this.orginalStock = orginalStock;
	}

}
