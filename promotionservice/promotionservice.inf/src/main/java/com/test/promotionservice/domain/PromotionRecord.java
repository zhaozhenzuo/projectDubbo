package com.test.promotionservice.domain;

import java.util.Date;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 促销记录
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_promotion_record")
public class PromotionRecord extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	// 促销名称描述
	@Column("name")
	private String name;

	// 促销生效开始时间
	@Column("startTime")
	private Date startTime;

	// 促销生效结束时间
	@Column("endTime")
	private Date endTime;

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
