package com.test.mainsite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.DTO.CartDTO;
import com.test.DTO.CartItemDTO;
import com.test.cartservice.so.CartSo;
import com.test.cartservice.vo.CartItemVo;
import com.test.mainsite.CartCalFacade;
import com.test.mainsite.CartFacade;
import com.test.mainsite.PromotionFacade;
import com.test.promotionservice.so.PromotionRecordSo;
import com.test.promotionservice.vo.PromotionRecordVo;
import com.test.security.util.SecurityContextUtils;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartFacade cartFacade;

	@Autowired
	private CartCalFacade calFacade;

	@Autowired
	private PromotionFacade promotionFacade;

	@RequestMapping("/detail")
	public String listDetail(final CartSo so, Model model) {
		Long userId = SecurityContextUtils.getUserId();
		CartDTO cartViewDTO = cartFacade.getCartDTOByUserId(userId);
		model.addAttribute("res", cartViewDTO);
		String resPage = "cart/cartDetail";

		// 初始计算
		cartViewDTO = calFacade.calCartNoPromotion(cartViewDTO);

		if (cartViewDTO == null || cartViewDTO.getCartItemDTOList() == null
				|| cartViewDTO.getCartItemDTOList().size() <= 0) {
			return resPage;
		}

		List<Long> productIdList = this.getProductIdList(cartViewDTO);
		if (productIdList == null || productIdList.size() <= 0) {
			return resPage;
		}

		// 获取优惠券
		List<PromotionRecordVo> promotionRecordVoList = this.getPromotionByParams(productIdList);
		cartViewDTO.setPromotionRecordsCanSelect(promotionRecordVoList);

		// 使用优惠券重新计算
		this.recal(cartViewDTO, so);

		return resPage;
	}

	private void recal(CartDTO cartViewDTO, final CartSo so) {
		if (so.getPromotionIdUseList() == null || so.getPromotionIdUseList().size() <= 0) {
			return;
		}
		calFacade.calCartWithPromotion(cartViewDTO, so.getPromotionIdUseList());
	}

	private List<Long> getProductIdList(CartDTO cartViewDTO) {
		List<Long> productIdList = new ArrayList<Long>();
		if (cartViewDTO.getCartItemDTOList() == null || cartViewDTO.getCartItemDTOList().size() <= 0) {
			return productIdList;
		}
		Set<Long> productIdSet = new HashSet<Long>();
		for (CartItemDTO cartItemDTO : cartViewDTO.getCartItemDTOList()) {
			productIdSet.add(cartItemDTO.getProductVo().getId());
		}
		productIdList.addAll(productIdSet);
		return productIdList;
	}

	private List<PromotionRecordVo> getPromotionByParams(List<Long> productIdList) {
		Date curDate = new Date();
		PromotionRecordSo promotionRecordSo = new PromotionRecordSo();
		promotionRecordSo.setStartTime(curDate);
		promotionRecordSo.setUserId(SecurityContextUtils.getUserId());
		promotionRecordSo.setProductIdList(productIdList);
		return promotionFacade.getValidPromotionsByParams(promotionRecordSo);
	}

	@RequestMapping("/addCartItem")
	public void save(CartItemVo cartItemVo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Long userId = SecurityContextUtils.getUserId();
		boolean flag = cartFacade.addCartItem(userId, cartItemVo.getSkuId(), cartItemVo.getCount());
		request.setAttribute("msg", flag ? "成功" : "失败");
		try {
			WebUtils.issueRedirect(request, response, "/cart/detail");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/deleteCartItem")
	public void deleteCartItem(CartItemVo cartItemVo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Long userId = SecurityContextUtils.getUserId();
		boolean flag = cartFacade.deleteCartItem(userId, cartItemVo.getSkuId());
		request.setAttribute("msg", flag ? "成功" : "失败");
		try {
			WebUtils.issueRedirect(request, response, "/cart/detail");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
