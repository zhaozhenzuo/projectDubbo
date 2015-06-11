package com.test.productservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 库存表
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_skustock")
public class SkuStock extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	@Column("skuId")
	private Long skuId;

	@Column("count")
	private Integer count;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
