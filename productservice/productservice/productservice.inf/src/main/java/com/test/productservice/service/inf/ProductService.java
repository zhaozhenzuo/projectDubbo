package com.test.productservice.service.inf;

import java.util.List;

import com.test.framework.base.BaseService;
import com.test.productservice.domain.Product;
import com.test.productservice.so.ProductSo;
import com.test.productservice.vo.ProductVo;

public interface ProductService extends BaseService<Product, ProductVo, ProductSo> {
	
	public List<ProductVo> selectVoByProductIdList(List<Long> idList);

	/**
	 * 查询商品详情信息，会将该商品对应sku放入productVo中返回<br/>
	 * 
	 * 业务上用于详情页,商品状态必须为ONLINE状态
	 * 
	 * @param id
	 * @return
	 */
	public ProductVo getProductWithSkuInfoByProductId(Long id);

	/**
	 * 使某个商品上线，能够被用户购买
	 * 
	 * @param id
	 * @return
	 */
	public boolean onlineProductByProductId(Long id);
	
	/**
	 * 下线某个商品
	 * 
	 * @param id
	 * @return
	 */
	public boolean offlineProductByProductId(Long id);
	
}
