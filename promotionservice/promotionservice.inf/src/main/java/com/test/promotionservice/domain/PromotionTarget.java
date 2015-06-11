package com.test.promotionservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 促销优惠对象
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_promotion_target")
public class PromotionTarget extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;

	// 所属促销记录
	@Column("belongPromotionId")
	private Long belongPromotionId;

	// 目标对象，-1代表全部用户
	@Column("userId")
	private Long userId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
