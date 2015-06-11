package com.test.framework.annotation;

import java.io.Serializable;

public class GetLastSeq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
