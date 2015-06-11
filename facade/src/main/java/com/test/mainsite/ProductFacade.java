package com.test.mainsite;

import java.util.List;

import com.test.productservice.so.ProductSo;
import com.test.productservice.vo.ProductVo;

public interface ProductFacade {
	
	public ProductVo saveProduct(ProductVo vo);
	
	public List<ProductVo> searchProductVosBySo(ProductSo so);
	
	public List<ProductVo> selectVoByProductIdList(List<Long> idList);
	
	public ProductVo getProductVoWithSkuInfoByProductId(Long productId);
	
	public boolean onlineProductByProductId(Long productId);
	
	public boolean offlineProductByProductId(Long productId);
	
	//预览商品详情
	public ProductVo getProductVoWithSkuInfoByProductIdForPreview(Long productId);
}
