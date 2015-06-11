package com.test.framework.domain;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private T result;
	
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
