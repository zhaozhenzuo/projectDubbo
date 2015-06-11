package com.test.promotionservice.so;

import java.util.Date;
import java.util.List;
import com.test.framework.so.BaseSo;

public class PromotionRecordSo extends BaseSo{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private List<Long> productIdList;
	
	private Date startTime;
	
	private Date endTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
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
