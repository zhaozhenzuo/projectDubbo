package com.test.mainsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.framework.annotation.Facade;
import com.test.promotionservice.service.inf.PromotionService;
import com.test.promotionservice.so.PromotionRecordSo;
import com.test.promotionservice.vo.CalResultVo;
import com.test.promotionservice.vo.CartCalVo;
import com.test.promotionservice.vo.PromotionRecordVo;

@Facade
public class PromotionFacadeImpl implements PromotionFacade{
	
	@Autowired
	private PromotionService promotionService;

	@Override
	public List<PromotionRecordVo> getValidPromotionsByParams(PromotionRecordSo so) {
		return promotionService.getValidPromotionsByParams(so);
	}

	@Override
	public CalResultVo cal(CartCalVo cartCalVo, List<Long> promotionIdList) {
		return promotionService.cal(cartCalVo, promotionIdList);
	}

}
