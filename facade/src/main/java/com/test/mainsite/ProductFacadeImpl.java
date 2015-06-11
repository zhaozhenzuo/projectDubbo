package com.test.mainsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.RpcContext;
import com.test.cartservice.service.SkuStockInMemService;
import com.test.cartservice.vo.SkuStockInMemVo;
import com.test.framework.annotation.Facade;
import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.service.inf.ProductService;
import com.test.productservice.service.inf.SkuService;
import com.test.productservice.service.inf.SkuStockService;
import com.test.productservice.so.ProductSo;
import com.test.productservice.so.SkuSo;
import com.test.productservice.so.SkuStockSo;
import com.test.productservice.vo.ProductVo;
import com.test.productservice.vo.SkuStockVo;
import com.test.productservice.vo.SkuVo;

@Facade
public class ProductFacadeImpl implements ProductFacade {

	@Autowired
	private ProductService productService;

	@Autowired
	private SkuStockInMemService skuStockInMemService;

	@Autowired
	private SkuStockService skuStockService;

	@Autowired
	private SkuService skuService;

	private static final Logger logger = LoggerFactory.getLogger(ProductFacadeImpl.class);

	@Override
	public ProductVo saveProduct(ProductVo vo) {
		return productService.insert(vo);
	}

	@Override
	public List<ProductVo> searchProductVosBySo(final ProductSo so) {
		Future<List<ProductVo>> future=RpcContext.getContext().asyncCall(new Callable<List<ProductVo>>() {
			@Override
			public List<ProductVo> call() throws Exception {
				return productService.selectVoBySo(so);
			}
		});
		
		List<ProductVo> res=null;
		try {
			res=future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ProductVo getProductVoWithSkuInfoByProductId(Long productId) {
		ProductVo productVo = productService.getProductWithSkuInfoByProductId(productId);

		List<SkuVo> skuVoList = productVo.getSkuVoList();
		if (skuVoList == null || skuVoList.size() <= 0) {
			logger.error("no sku found,productId:" + productId);
			return productVo;
		}

		List<Long> skuIdList = ObjectGerateUtil.getFiledValueListFromListObject(skuVoList, "id", Long.class);

		// 内存中的库存
		List<SkuStockInMemVo> skuStockInMemVoList = skuStockInMemService.getStocksBySkuIdList(skuIdList);

		if (skuStockInMemVoList == null || skuStockInMemVoList.size() <= 0) {
			logger.error("skuStock in mem is null,productId:" + productId);
			return productVo;
		}

		Map<Long, SkuStockInMemVo> skuStockInMemMap = ObjectGerateUtil.groupMapByKeyAndSingleValue("skuId", Long.class,
				skuStockInMemVoList);

		for (SkuVo skuVo : skuVoList) {
			SkuStockInMemVo skuStockInMemVo = skuStockInMemMap.get(skuVo.getId());
			if (skuStockInMemVo == null) {
				logger.error("no skuStock in mem,skuId:" + skuVo.getId());
				continue;
			}
			skuVo.setStockCountInMem(skuStockInMemVo.getStockCount());
		}

		return productVo;
	}

	@Override
	public boolean onlineProductByProductId(Long productId) {
		boolean saveStockToMemFlag = this.insertStockCountOfSkusToMem(productId);
		if (!saveStockToMemFlag) {
			logger.error("fail insert skuStock to mem,productId:" + productId);
			return false;
		}

		return productService.onlineProductByProductId(productId);
	}

	private boolean insertStockCountOfSkusToMem(Long productId) {
		SkuSo skuSo = new SkuSo();
		skuSo.setProductId(productId);
		List<SkuVo> skuList = skuService.selectVoBySo(skuSo);
		if (skuList == null || skuList.size() <= 0) {
			logger.error("no sku foun for productId:" + productId);
			return false;
		}

		List<Long> skuIdList = new ArrayList<Long>();
		for (SkuVo vo : skuList) {
			skuIdList.add(vo.getId());
		}

		// 查找库存skuStock
		SkuStockSo skuStockSo = new SkuStockSo();
		skuStockSo.setSkuIdList(skuIdList);
		List<SkuStockVo> skuStockVoList = skuStockService.selectVoBySo(skuStockSo);
		if (skuStockVoList == null || skuStockVoList.size() <= 0) {
			logger.error("no skuStock record found in database,productId:" + productId);
			return false;
		}

		boolean allSuccessFlag = true;
		for (SkuStockVo skuStockVo : skuStockVoList) {
			SkuStockInMemVo skuStockInMemVo = new SkuStockInMemVo();
			skuStockInMemVo.setSkuId(skuStockVo.getSkuId());
			skuStockInMemVo.setStockCount(skuStockVo.getCount());
			if (!skuStockInMemService.insert(skuStockInMemVo)) {
				logger.error("fail insert skuStock to mem,productId:" + productId + ",skuId:" + skuStockVo.getSkuId());
				allSuccessFlag = false;
				break;
			}
		}

		return allSuccessFlag;
	}

	@Override
	public boolean offlineProductByProductId(Long productId) {
		boolean delStockFlag = this.delStocksfSkusFromMem(productId);
		if (!delStockFlag) {
			logger.error("fail del skuStock from mem,productId:" + productId);
			// return false;
		}

		return productService.offlineProductByProductId(productId);
	}

	private boolean delStocksfSkusFromMem(Long productId) {
		SkuSo skuSo = new SkuSo();
		skuSo.setProductId(productId);
		List<SkuVo> skuList = skuService.selectVoBySo(skuSo);
		if (skuList == null || skuList.size() <= 0) {
			logger.error("no sku foun for productId:" + productId);
			return false;
		}

		List<Long> skuIdList = new ArrayList<Long>();
		for (SkuVo vo : skuList) {
			skuIdList.add(vo.getId());
		}

		// delete stock from mem
		boolean delStockFromMemFlag = skuStockInMemService.deleteBySkuIdList(skuIdList);
		if (!delStockFromMemFlag) {
			logger.error("del stock from mem fail");
			return false;
		}

		return true;
	}

	@Override
	public ProductVo getProductVoWithSkuInfoByProductIdForPreview(Long productId) {
		return null;
	}

	@Override
	public List<ProductVo> selectVoByProductIdList(List<Long> idList) {
		return productService.selectVoByProductIdList(idList);
	}
}
