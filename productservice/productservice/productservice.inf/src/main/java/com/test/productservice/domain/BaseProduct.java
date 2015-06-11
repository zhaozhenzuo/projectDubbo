package com.test.productservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.domain.BaseBo;
import com.test.productservice.enums.Status;

/**
 * 抽象商品父类
 * 
 * @author hzzhaozhenzuo
 * 
 */
public abstract class BaseProduct extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	// 商品名称
	@Column("name")
	private String name;

	// 最低类别id
	@Column("lowestCategoryId")
	private Long lowestCategoryId;

	// 图片url集合
	@Column("picUrls")
	private String picUrls;

	// 商品最低售卖价格
	@Column("price")
	private String price;

	// 商品最低折扣
	@Column("discount")
	private String discount;

	// 市场价
	@Column("marketPrice")
	private String marketPrice;

	@Column("brandId")
	private Long brandId;

	// 商品状态
	@Column("status")
	private Status status;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLowestCategoryId() {
		return lowestCategoryId;
	}

	public void setLowestCategoryId(Long lowestCategoryId) {
		this.lowestCategoryId = lowestCategoryId;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

}
