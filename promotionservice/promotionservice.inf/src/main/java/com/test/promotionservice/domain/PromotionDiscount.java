package com.test.promotionservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;
import com.test.promotionservice.enums.PromotionDiscountType;
import com.test.promotionservice.enums.PromotionDiscountTypeCalTarget;

/**
 * 优惠政策<br/>
 * 
 * 满减，打折，或包邮
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_promotion_discount")
public class PromotionDiscount extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	// 所属促销记录
	@Column("belongPromotionId")
	private Long belongPromotionId;

	// 优惠方式(有满减，打折，包邮)
	@Column("promotionDiscountType")
	private PromotionDiscountType promotionDiscountType;

	// 优惠方式
	@Column("promotionDiscountTypeCalTarget")
	private PromotionDiscountTypeCalTarget promotionDiscountTypeCalTarget;

	// 价格(对满减策略有效)
	@Column("money")
	private String money;

	// 折扣(对打折策略有效)
	@Column("discount")
	private String discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBelongPromotionId() {
		return belongPromotionId;
	}

	public void setBelongPromotionId(Long belongPromotionId) {
		this.belongPromotionId = belongPromotionId;
	}

	public PromotionDiscountType getPromotionDiscountType() {
		return promotionDiscountType;
	}

	public void setPromotionDiscountType(PromotionDiscountType promotionDiscountType) {
		this.promotionDiscountType = promotionDiscountType;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public PromotionDiscountTypeCalTarget getPromotionDiscountTypeCalTarget() {
		return promotionDiscountTypeCalTarget;
	}

	public void setPromotionDiscountTypeCalTarget(PromotionDiscountTypeCalTarget promotionDiscountTypeCalTarget) {
		this.promotionDiscountTypeCalTarget = promotionDiscountTypeCalTarget;
	}

}
