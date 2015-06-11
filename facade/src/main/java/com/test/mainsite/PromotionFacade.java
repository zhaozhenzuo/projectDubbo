package com.test.mainsite;

import java.util.List;

import com.test.promotionservice.so.PromotionRecordSo;
import com.test.promotionservice.vo.CalResultVo;
import com.test.promotionservice.vo.CartCalVo;
import com.test.promotionservice.vo.PromotionRecordVo;

public interface PromotionFacade {
	
	/**
	 * 根据查询条件查找出当前符合条件的促销记录集<br/>
	 * 
	 * 当用户查询购物车时，可以调用此接口查询出可用的促销优惠卷
	 * 
	 * @param so
	 * @return
	 */
	public List<PromotionRecordVo> getValidPromotionsByParams(PromotionRecordSo so);

	/**
	 * 计算promotion
	 * 
	 * @param cartCalVo 当前购物车
	 * @param promotionIdList 用户选择使用的promotion
	 * @return
	 */
	public CalResultVo cal(CartCalVo cartCalVo, List<Long> promotionIdList);

}
