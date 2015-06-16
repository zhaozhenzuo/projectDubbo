package com.test.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.constant.ProductCodeInfo;
import com.test.productservice.dao.ProductMapper;
import com.test.productservice.dao.SkuAttributeMapper;
import com.test.productservice.dao.SkuMapper;
import com.test.productservice.dao.SkuStockMapper;
import com.test.productservice.domain.Product;
import com.test.productservice.enums.Status;
import com.test.productservice.service.inf.ProductService;
import com.test.productservice.so.ProductSo;
import com.test.productservice.so.SkuAttributeSo;
import com.test.productservice.so.SkuSo;
import com.test.productservice.so.SkuStockSo;
import com.test.productservice.vo.ProductVo;
import com.test.productservice.vo.SkuAttributeVo;
import com.test.productservice.vo.SkuStockVo;
import com.test.productservice.vo.SkuVo;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private SkuAttributeMapper skuAttributeMapper;

	@Autowired
	private SkuStockMapper skuStockMapper;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public ProductVo insert(ProductVo vo) {
		vo.setStatus(Status.NEW);
		ProductVo resVo = ObjectGerateUtil.insertAndReturnTargetClassInstance(vo, ProductVo.class, productMapper);
		if (resVo == null) {
			logger.error("err insert Product");
			return null;
		}
		return resVo;
	}

	public ProductVo update(ProductVo vo) {
		return ObjectGerateUtil.updateAndReturnTargetClassInstance(vo, ProductVo.class, productMapper);
	}
	
	public List<Product> selectPoBySo(ProductSo so) {
		return productMapper.selectPoBySo(so);
	}

	@Cacheable(value="product1")
	public List<ProductVo> selectVoBySo(ProductSo so) {
		return productMapper.selectVoBySo(so);
	}

	public Long selectCount(ProductSo so) {
		return productMapper.selectCount(so);
	}

	@Override
	public boolean delete(Long id) {
		return productMapper.delete(id);
	}

	@Override
	public Product selectPoById(Long id) {
		return productMapper.selectPoByPrimaryKey(id);
	}
	
	@CacheEvict("product1")
	@Override
	public ProductVo getProductWithSkuInfoByProductId(Long id) {
		if (id == null) {
			return null;
		}

		Product productPo = productMapper.selectPoByPrimaryKey(id);
		if (productPo == null) {
			logger.error("no product found or status is not online,id:" + id);
			return null;
		}

		ProductVo productVo = ObjectGerateUtil.copyAndGenerateObj(productPo, ProductVo.class);

		// fill sku info
		SkuSo skuSo = new SkuSo();
		skuSo.setProductId(id);
		List<SkuVo> skuVoList = skuMapper.selectVoBySo(skuSo);
		if (skuVoList == null || skuVoList.size() <= 0) {
			logger.error("no sku found,productId:" + id);
			return productVo;
		}

		productVo.setSkuVoList(skuVoList);

		List<Long> skuIdList = new ArrayList<Long>();
		for (SkuVo skuVo : skuVoList) {
			skuIdList.add(skuVo.getId());
		}

		SkuAttributeSo skuAttributeSo = new SkuAttributeSo();
		skuAttributeSo.setBelongIdList(skuIdList);
		List<SkuAttributeVo> skuAttributeVoList = skuAttributeMapper.selectVoBySo(skuAttributeSo);

		Map<Long, List<SkuAttributeVo>> skuAttributeMap = ObjectGerateUtil.groupMapByByKeyAndListValue("belongId",
				Long.class, skuAttributeVoList);

		// fill sku attributes
		this.fillSkuAttributeToSkuVoList(skuVoList, skuAttributeMap);

		// fill skuStock
		SkuStockSo skuStockSo = new SkuStockSo();
		skuStockSo.setSkuIdList(skuIdList);
		List<SkuStockVo> skuStockVoList = skuStockMapper.selectVoBySo(skuStockSo);
		if (skuStockVoList == null || skuStockVoList.size() <= 0) {
			logger.error("no skuStock found in database");
			return productVo;
		}

		Map<Long, SkuStockVo> skuStockMap = ObjectGerateUtil.groupMapByKeyAndSingleValue("skuId", Long.class,
				skuStockVoList);
		this.fillSkuStockToSkuVo(skuStockMap, skuVoList);

		return productVo;
	}

	private void fillSkuStockToSkuVo(Map<Long, SkuStockVo> skuStockMap, List<SkuVo> skuVoList) {
		for (SkuVo skuVo : skuVoList) {
			SkuStockVo skuStockVo = skuStockMap.get(skuVo.getId());
			if (skuStockVo == null) {
				logger.error("no skuStock found,skuId:" + skuVo.getId());
				continue;
			}
			skuVo.setStockCount(skuStockVo.getCount());
		}
	}

	private void fillSkuAttributeToSkuVoList(List<SkuVo> skuVoList, Map<Long, List<SkuAttributeVo>> skuAttributeMap) {
		for (SkuVo skuVo : skuVoList) {
			List<SkuAttributeVo> tempList = skuAttributeMap.get(skuVo.getId());
			if (tempList != null && tempList.size() > 0) {
				for (SkuAttributeVo skuAttributeVo : tempList) {
					this.fillSkuAttributeToSingleSkuVo(skuAttributeVo, skuVo);
				}
			}
		}
	}

	private void fillSkuAttributeToSingleSkuVo(SkuAttributeVo skuAttributeVo, SkuVo skuVo) {
		if (ProductCodeInfo.SKU_COLOR_TYPE_CODE.equalsIgnoreCase(skuAttributeVo.getTypeCode())) {
			skuVo.setColorAttributeVo(skuAttributeVo);
		} else if (ProductCodeInfo.SKU_SIZE_TYPE_CODE.equalsIgnoreCase(skuAttributeVo.getTypeCode())) {
			skuVo.setSizeAttributeVo(skuAttributeVo);
		} else {
			logger.error("only support size and color typeCode,skuId:" + skuAttributeVo.getBelongId()
					+ ",skuAttributeId:" + skuAttributeVo.getId());
			return;
		}
	}

	@Override
	public boolean onlineProductByProductId(Long id) {
		Product poProduct = productMapper.selectPoByPrimaryKey(id);
		poProduct.setStatus(Status.ONLINE);
		Product res = ObjectGerateUtil.updateAndReturnTargetClassInstance(poProduct, Product.class, productMapper);
		return res != null ? true : false;
	}

	@Override
	public boolean offlineProductByProductId(Long id) {
		Product poProduct = productMapper.selectPoByPrimaryKey(id);
		poProduct.setStatus(Status.OFFLINE);
		Product res = ObjectGerateUtil.updateAndReturnTargetClassInstance(poProduct, Product.class, productMapper);
		return res != null ? true : false;
	}
	
	@Cacheable(value="product1")
	@Override
	public List<ProductVo> selectVoByProductIdList(List<Long> idList) {
		ProductSo so=new ProductSo();
		so.setProductIdList(idList);
		return productMapper.selectVoBySo(so);
	}

}
