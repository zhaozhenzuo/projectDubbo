package com.test.framework.util;

import com.test.framework.domain.ResponseResult;

public class ResponseUtil{

	public static <T> ResponseResult<T> generateResponse(Integer code, T result, String message) {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(code);
		responseResult.setResult(result);
		responseResult.setMessage(message);
		return responseResult;
	}
	
	/**
	 * 构造结果对象，errorMessage为空
	 * @param code
	 * @param result
	 * @return
	 */
	public static <T> ResponseResult<T> generateResponse(Integer code, T result) {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(code);
		responseResult.setResult(result);
		return responseResult;
	}
	
	public static <T> ResponseResult<T> generateResponse(Integer code) {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(code);
		return responseResult;
	}
	
	public static <T> ResponseResult<T> generateError() {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(CodeInfoClass.COMMON_FAIL);
		return responseResult;
	}
	
	public static <T> ResponseResult<T> generateError(String errorMsg) {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(CodeInfoClass.COMMON_FAIL);
		responseResult.setMessage(errorMsg);
		return responseResult;
	}
	
	public static <T> ResponseResult<T> generateSuccess(T result) {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(CodeInfoClass.SUCCESS);
		responseResult.setResult(result);
		return responseResult;
	}

}
