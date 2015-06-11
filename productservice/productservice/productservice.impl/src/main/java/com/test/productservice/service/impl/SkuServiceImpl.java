package com.test.productservice.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.framework.exception.BizException;
import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.dao.SkuAttributeMapper;
import com.test.productservice.dao.SkuMapper;
import com.test.productservice.dao.SkuStockMapper;
import com.test.productservice.domain.Sku;
import com.test.productservice.domain.SkuStock;
import com.test.productservice.service.inf.SkuService;
import com.test.productservice.so.SkuSo;
import com.test.productservice.vo.SkuVo;

@Service("skuService")
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private SkuAttributeMapper skuAttributeMapper;

	@Autowired
	private SkuStockMapper skuStockMapper;

	private static final Logger logger = Logger.getLogger(SkuServiceImpl.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public SkuVo insert(SkuVo vo) {
		if (vo == null) {
			logger.warn("sku vo is null");
			return null;
		}

		boolean skuSaveFlag = skuMapper.insert(vo);
		if (!skuSaveFlag) {
			logger.error("save sku error");
			return null;
		}

		// insert codeInfo to SkuAttribute table
		boolean attributeFlag = this.createAttribute(vo);
		if (!attributeFlag) {
			String errMsg = "insert addtribute err ,barcode:" + vo.getBarcode();
			logger.error(errMsg);
			throw new BizException(errMsg);
		}

		if (!this.insertStockOfSku(vo)) {
			String errMsg = "insert stock fail,barcode:" + vo.getBarcode();
			logger.error(errMsg);
			throw new BizException(errMsg);
		}

		Sku resSku = skuMapper.selectPoByPrimaryKey(vo.getId());
		if (resSku == null) {
			String errMsg = "no sku found when insert sku,id:" + vo.getId() + ",barcode:" + vo.getBarcode();
			logger.error(errMsg);
			throw new BizException(errMsg);
		}

		return ObjectGerateUtil.copyAndGenerateObj(resSku, SkuVo.class);
	}

	private boolean insertStockOfSku(SkuVo vo) {
		Integer stockCount = vo.getStockCount();
		if (stockCount == null || stockCount < 0) {
			logger.warn("not valid stock");
			return false;
		}
		SkuStock stockPo = new SkuStock();
		stockPo.setSkuId(vo.getId());
		stockPo.setCount(stockCount);
		return skuStockMapper.insert(stockPo);
	}

	private boolean createAttribute(SkuVo vo) {
		if (vo == null || vo.getColorAttributeVo() == null || vo.getSizeAttributeVo() == null) {
			logger.error("color or size is null");
			return false;
		}

		vo.getColorAttributeVo().setBelongId(vo.getId());
		vo.getSizeAttributeVo().setBelongId(vo.getId());

		boolean ColorFlag = skuAttributeMapper.insert(vo.getColorAttributeVo());
		if (!ColorFlag) {
			String errMsg = "insert color skuAttribute fail,barCode:" + vo.getBarcode();
			logger.error(errMsg);
			throw new BizException(errMsg);
		}

		boolean sizeFlag = skuAttributeMapper.insert(vo.getSizeAttributeVo());
		if (!sizeFlag) {
			String errMsg = "insert size skuAttribute fail,barCode:" + vo.getBarcode();
			logger.error(errMsg);
			throw new BizException(errMsg);
		}

		return true;
	}

	public SkuVo update(SkuVo vo) {
		return ObjectGerateUtil.updateAndReturnTargetClassInstance(vo, SkuVo.class, skuMapper);
	}

	public List<Sku> selectPoBySo(SkuSo so) {
		return skuMapper.selectPoBySo(so);
	}

	public List<SkuVo> selectVoBySo(SkuSo so) {
		return skuMapper.selectVoBySo(so);
	}

	public Long selectCount(SkuSo so) {
		return skuMapper.selectCount(so);
	}

	@Override
	public boolean delete(Long id) {
		return skuMapper.delete(id);
	}

	@Override
	public Sku selectPoById(Long id) {
		return skuMapper.selectPoByPrimaryKey(id);
	}

}
