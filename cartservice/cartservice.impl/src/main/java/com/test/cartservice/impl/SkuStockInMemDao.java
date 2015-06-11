package com.test.cartservice.impl;

import java.util.List;

import com.test.cartservice.vo.SkuStockInMemVo;
import com.test.framework.domain.ResponseResult;

public interface SkuStockInMemDao<T> {

	/**
	 * 往内存插入一条sku
	 * 
	 * @param boolean
	 * @return
	 */
	public boolean insert(SkuStockInMemVo vo);

	/**
	 * 删除一条内存中对应sku的库存记录
	 * 
	 * @param skuId
	 * @return
	 */
	public boolean deleteBySkuId(Long skuId);

	/**
	 * 删除内存中对应sku的库存记录
	 * 
	 * @param skuIdList
	 * @return
	 */
	public boolean deleteBySkuIdList(List<Long> skuIdList);

	/**
	 * 将对应sku内存中的库存增加num个
	 * 
	 * @param skuId
	 * @param num
	 * @return 返回负数Integer.minValue 代表失败
	 */
	public T increaseStock(Long skuId, T num);

	/**
	 * 将对应sku内存中的库存减少num个
	 * 
	 * @param skuId
	 * @param num
	 * @return 返回负数Integer.minValue 代表失败
	 */
	public T  decreaseStock(Long skuId, T num);

	/**
	 * 获取对应sku的库存数
	 * 
	 * @param skuId
	 * @return code为SUCCESS表示正常查询，code为COMMON_FAIL表示系统异常,code为NOT_EXIST表示不存在
	 */
	public ResponseResult<T> getStockBySkuId(Long skuId);

	/**
	 * 获取skuIdList对应的库存列表对象
	 * 
	 * @param so
	 * @return
	 */
	public List<SkuStockInMemVo> getStocksBySkuIdList(List<Long> skuIdList);

}
