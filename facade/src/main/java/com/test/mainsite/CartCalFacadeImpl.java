package com.test.mainsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.DTO.CartDTO;
import com.test.DTO.CartItemDTO;
import com.test.framework.annotation.Facade;
import com.test.framework.util.CalUtil;
import com.test.promotionservice.service.inf.PromotionService;
import com.test.promotionservice.vo.CalResultVo;
import com.test.promotionservice.vo.CartCalVo;
import com.test.promotionservice.vo.CartItemCalVo;

@Facade
public class CartCalFacadeImpl implements CartCalFacade {

	@Autowired
	private PromotionService promotionService;

	private static final Logger logger = LoggerFactory.getLogger(CartCalFacadeImpl.class);

	@Override
	public CartDTO calCartWithPromotion(CartDTO cartDTO, List<Long> promotionIdList) {
		CartCalVo cartCalVo = new CartCalVo();
		cartCalVo.setTotalPrice(cartDTO.getTotalPrice());

		List<CartItemCalVo> cartItemCalList = new ArrayList<CartItemCalVo>();

		for (CartItemDTO cartItemDTO : cartDTO.getCartItemDTOList()) {
			CartItemCalVo vo = new CartItemCalVo();
			vo.setCount(cartItemDTO.getCount());
			vo.setOriginalPriceOfOne(cartItemDTO.getOriginalPriceOfOne());
			vo.setProductId(cartItemDTO.getProductVo().getId());
			vo.setSalePriceOfOne(cartItemDTO.getSalePriceOfOne());
			vo.setSkuId(cartItemDTO.getSkuId());
			vo.setTotalPriceForCurrentSku(cartItemDTO.getTotalPriceForCurrentSku());

			cartItemCalList.add(vo);
		}

		CalResultVo calResultVo = promotionService.cal(cartCalVo, promotionIdList);
		this.fillCalPriceToCartDTO(calResultVo, cartDTO);
		return cartDTO;
	}

	private void fillCalPriceToCartDTO(CalResultVo calResultVo, CartDTO targetCartDTO) {
		CartCalVo cartCalVo = calResultVo.getCartCalVo();

		// 总价更新
		targetCartDTO.setTotalPrice(cartCalVo.getTotalPrice());

		Map<Long, CartItemCalVo> cartItemCalMap = this.groupCartItemBySkuId(cartCalVo.getCartItemCalVoList());

		if (cartItemCalMap == null || cartItemCalMap.isEmpty()) {
			return;
		}

		// 明细项更新
		for (CartItemDTO cartItemDTO : targetCartDTO.getCartItemDTOList()) {
			CartItemCalVo cartItemCalVo = cartItemCalMap.get(cartItemDTO.getSkuId());
			if (cartItemCalVo == null) {
				logger.error("no cal result found for sku:" + cartItemDTO.getSkuId());
				continue;
			}
			cartItemDTO.setSalePriceOfOne(cartItemCalVo.getSalePriceOfOne());
			cartItemDTO.setTotalPriceForCurrentSku(cartItemCalVo.getTotalPriceForCurrentSku());
		}
	}

	private Map<Long, CartItemCalVo> groupCartItemBySkuId(List<CartItemCalVo> cartItemCalVoList) {
		Map<Long, CartItemCalVo> cartItemCalMap = new HashMap<Long, CartItemCalVo>();
		if (cartItemCalVoList == null || cartItemCalVoList.size() <= 0) {
			return cartItemCalMap;
		}

		for (CartItemCalVo cartItemCalVo : cartItemCalVoList) {
			cartItemCalMap.put(cartItemCalVo.getSkuId(), cartItemCalVo);
		}
		return cartItemCalMap;
	}

	@Override
	public CartDTO calCartNoPromotion(CartDTO cartDTO) {
		if (cartDTO == null || cartDTO.getCartItemDTOList() == null || cartDTO.getCartItemDTOList().size() <= 0) {
			return cartDTO;
		}
		double total = 0;
		for (CartItemDTO cartItemDTO : cartDTO.getCartItemDTOList()) {
			double totalOfOneSku = CalUtil.multiple(cartItemDTO.getSalePriceOfOne(), cartItemDTO.getCount());
			cartItemDTO.setTotalPriceForCurrentSku(totalOfOneSku);
			total = CalUtil.add(total, totalOfOneSku);
		}

		cartDTO.setTotalPrice(total);
		return cartDTO;
	}

}
