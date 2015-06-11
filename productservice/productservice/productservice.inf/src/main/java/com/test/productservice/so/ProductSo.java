package com.test.productservice.so;

import java.util.List;

import com.test.framework.so.BaseSo;
import com.test.productservice.enums.Status;

public class ProductSo extends BaseSo{

	private static final long serialVersionUID = 1L;
	
	private List<Long> productIdList;
	
	private Status status;
	
	private Long productId;
	
	public List<Long> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
