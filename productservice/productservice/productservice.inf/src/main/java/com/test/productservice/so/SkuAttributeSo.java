package com.test.productservice.so;

import java.util.List;

import com.test.framework.so.BaseSo;

public class SkuAttributeSo extends BaseSo{

	private static final long serialVersionUID = 1L;
	
	private Long belongId;
	
	private List<Long> belongIdList;

	public Long getBelongId() {
		return belongId;
	}

	public void setBelongId(Long belongId) {
		this.belongId = belongId;
	}

	public List<Long> getBelongIdList() {
		return belongIdList;
	}

	public void setBelongIdList(List<Long> belongIdList) {
		this.belongIdList = belongIdList;
	}

}
