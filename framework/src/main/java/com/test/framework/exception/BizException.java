package com.test.framework.exception;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BizException(String errMsg) {
		super(errMsg);
	}

	public BizException(String errMsg, Throwable errObj) {
		super(errMsg, errObj);
	}

	public BizException(Throwable errObj) {
		super(errObj);
	}

}
