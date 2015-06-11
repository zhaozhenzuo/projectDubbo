package com.test.promotionservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.promotionservice.service.inf.PromotionService;
import com.test.promotionservice.so.PromotionRecordSo;
import com.test.promotionservice.vo.CalResultVo;
import com.test.promotionservice.vo.CartCalVo;
import com.test.promotionservice.vo.PromotionRecordVo;

@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

	/**
	 * toDo (non-Javadoc)
	 * 
	 * @see com.test.promotionservice.service.inf.PromotionService#getValidPromotionsByParams(com.test.promotionservice.so.PromotionRecordSo)
	 */
	public List<PromotionRecordVo> getValidPromotionsByParams(PromotionRecordSo so) {
		List<PromotionRecordVo> recordVoList = new ArrayList<PromotionRecordVo>();
		return recordVoList;
	}

	/**
	 * toDo (non-Javadoc)
	 * 
	 * @see com.test.promotionservice.service.inf.PromotionService#cal(com.test.promotionservice.vo.CartCalVo,
	 *      java.util.List)
	 */
	public CalResultVo cal(CartCalVo cartCalVo, List<Long> promotionIdList) {
		CalResultVo calResultVo = new CalResultVo();
		calResultVo.setCartCalVo(cartCalVo);
		return calResultVo;
	}

}
