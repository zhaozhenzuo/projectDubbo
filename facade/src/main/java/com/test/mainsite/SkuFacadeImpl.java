package com.test.mainsite;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.codeinfoservice.domain.CodeInfo;
import com.test.codeinfoservice.service.inf.CodeInfoService;
import com.test.framework.annotation.Facade;
import com.test.productservice.service.inf.SkuService;
import com.test.productservice.so.SkuSo;
import com.test.productservice.vo.SkuAttributeVo;
import com.test.productservice.vo.SkuVo;

@Facade
public class SkuFacadeImpl implements SkuFacade {

	@Autowired
	private SkuService skuService;

	@Autowired
	private CodeInfoService codeInfoService;

	private static final Logger logger = LoggerFactory.getLogger(SkuFacadeImpl.class);

	@Override
	public List<SkuVo> searchVosBySo(SkuSo so) {
		return skuService.selectVoBySo(so);
	}

	/**
	 * 保存sku <br/>
	 * (non-Javadoc)
	 * 
	 * @see com.test.mainsite.SkuFacade#insert(com.test.productservice.vo.SkuVo)
	 */
	@Override
	public SkuVo insert(SkuVo vo) {
		if (StringUtils.isEmpty(vo.getBarcode()) || vo.getColorAttributeVo() == null || vo.getSizeAttributeVo() == null
				|| vo.getColorAttributeVo().getCodeInfoId() == null || vo.getSizeAttributeVo().getCodeInfoId() == null) {
			logger.error("barcode,color and size must be set when insert sku");
			return null;
		}

		SkuAttributeVo colorAttributeVo = vo.getColorAttributeVo();
		SkuAttributeVo sizeAttributeVo = vo.getSizeAttributeVo();

		// file extra info for size and color attribute
		boolean fillColorFlag = this.fillCodeInfoExtrInfoToTarget(colorAttributeVo.getCodeInfoId(), colorAttributeVo);
		if (!fillColorFlag) {
			logger.error("fill color info err for barcode:" + vo.getBarcode());
			return null;
		}

		boolean fillSizeFlag = this.fillCodeInfoExtrInfoToTarget(sizeAttributeVo.getCodeInfoId(), sizeAttributeVo);
		if (!fillSizeFlag) {
			logger.error("fill size info err for barcode:" + vo.getBarcode());
			return null;
		}

		return skuService.insert(vo);
	}

	private boolean fillCodeInfoExtrInfoToTarget(Long codeInfoId, SkuAttributeVo targetAttributeVo) {
		if (codeInfoId == null || targetAttributeVo == null) {
			return false;
		}
		CodeInfo codeInfoInDatabase = codeInfoService.selectPoById(targetAttributeVo.getCodeInfoId());
		if (codeInfoInDatabase == null) {
			logger.error("no codeInfo found,codeInfoId:" + targetAttributeVo.getCodeInfoId());
			return false;
		}
		targetAttributeVo.setTypeCode(codeInfoInDatabase.getTypeCode());
		targetAttributeVo.setCodeInfoValue(codeInfoInDatabase.getValue());
		return true;
	}

}
