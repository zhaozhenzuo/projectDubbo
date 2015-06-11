package com.test.promotionservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;
import com.test.promotionservice.enums.PromotionConditionType;

/**
 * 促销条件<br/>
 * 支持product维度,或全部商品(某个时间段内)　
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_promotion_condition")
public class PromotionCondition extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	// 所属促销记录
	@Column("belongPromotionId")
	private Long belongPromotionId;

	// 促销类型
	@Column("promotionConditionType")
	private PromotionConditionType promotionConditionType;

	// 价格开始区间
	@Column("moneyStart")
	private String moneyStart;

	// 价格结束区间
	@Column("moneyEnd")
	private String moneyEnd;

	// 针对商品条件集合
	@Column("productIds")
	private String productIds;

	// 是否是and条件
	@Column("isAndCondition")
	private boolean isAndCondition;

	// 是否互斥
	@Column("isExclusion")
	private boolean isExclusion;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getBelongPromotionId() {
		return belongPromotionId;
	}

	public void setBelongPromotionId(Long belongPromotionId) {
		this.belongPromotionId = belongPromotionId;
	}

	public PromotionConditionType getPromotionConditionType() {
		return promotionConditionType;
	}

	public void setPromotionConditionType(PromotionConditionType promotionConditionType) {
		this.promotionConditionType = promotionConditionType;
	}

	public String getMoneyStart() {
		return moneyStart;
	}

	public void setMoneyStart(String moneyStart) {
		this.moneyStart = moneyStart;
	}

	public String getMoneyEnd() {
		return moneyEnd;
	}

	public void setMoneyEnd(String moneyEnd) {
		this.moneyEnd = moneyEnd;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public boolean isAndCondition() {
		return isAndCondition;
	}

	public void setAndCondition(boolean isAndCondition) {
		this.isAndCondition = isAndCondition;
	}

	public boolean isExclusion() {
		return isExclusion;
	}

	public void setExclusion(boolean isExclusion) {
		this.isExclusion = isExclusion;
	}

}
