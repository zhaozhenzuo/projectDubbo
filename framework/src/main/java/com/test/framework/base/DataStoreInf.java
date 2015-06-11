package com.test.framework.base;

import java.util.Map;

import com.test.framework.domain.ResponseResult;

/**
 * 存储接口<br/>
 * 
 * 实现可以采用数据库，redis及其它存储形式<br/>
 * 
 * 操作结果统一以ResponseResult对象返回
 * 
 * @author hzzhaozhenzuo
 * 
 */
public interface DataStoreInf<T> {

	/**
	 * 将数据存放到hash结构中<br/>
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 成功返回code为CodeInfoClass.SUCCESSS，已经存在或失败返回code为CodeInfoClass.COMMON_FAIL
	 */
	public ResponseResult<T> putToHashNotExist(String key, String field, T value);

	/**
	 * 根据key清除整个hash
	 * 
	 * @param key
	 * @return 不存在或删除失败对应key时返回CodeInfoClass.COMMON_FAIL
	 */
	public ResponseResult<T> deleteHashByKeyWhenExist(String key);

	/**
	 * 根据key及field清除对应的值
	 * 
	 * @param key
	 * @param field
	 * @return 不存在或删除失败对应key时返回CodeInfoClass.COMMON_FAIL
	 */
	public ResponseResult<T> deleteValueOfHashByKeyAndFieldWhenExist(String key, String field);
	
	/**
	 * 获取当个key下所有的field,value对
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, T> getAllFieldAndValueFromHash(String key);

	/**
	 * 根据key及field获取对应值
	 * 
	 * @param key
	 * @param field
	 * @return 返回ResponseResult对象，底层存储异常时返回code为COMMON_FAILCODE。元素不存在则返回CodeInfoClass.NOT_EXIST_CODE，结果对象为null<br/>
	 *         
	 */
	public ResponseResult<T> getValueByKeyAndField(String key, String field);

	/**
	 * 将key及field对应的value加上num
	 * 
	 * @param key
	 * @param field
	 * @param num
	 *            必须是数值型，且必须大于0
	 * @return 
	 *         返回自增后的数量，失败返回COMMON_FAILCODE，不存在对应key或field值时底层创建结构并返回成功后的自增量
	 */
	public ResponseResult<T> increaseValueByNum(String key, String field, T num);

	/**
	 * 将key及field对应的value减去num
	 * 
	 * @param key
	 * @param field
	 * @param num
	 *            必须是数值型且必须大小于0。减的操作由方法内部实现
	 * @return 
	 *         返回自减后的数量,失败返回COMMON_FAILCODE,不存在对应key或field值时底层创建结构并返回成功后的自减量
	 */
	public ResponseResult<T> decreaseValueByNum(String key, String field, T num);

}
