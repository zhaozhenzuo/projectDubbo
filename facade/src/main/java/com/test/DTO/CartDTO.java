package com.test.DTO;

import java.util.List;

import com.test.cartservice.vo.CartVo;
import com.test.promotionservice.vo.PromotionRecordVo;

/**
 * 给用户展示的购物车对象
 * 
 * @author hzzhaozhenzuo
 * 
 */
public class CartDTO extends CartVo {

	private static final long serialVersionUID = 1L;

	// 总价格,不含运费
	private double totalPrice;
	
	private List<CartItemDTO> cartItemDTOList;
	
	private List<PromotionRecordVo> promotionRecordsCanSelect;

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CartItemDTO> getCartItemDTOList() {
		return cartItemDTOList;
	}

	public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
		this.cartItemDTOList = cartItemDTOList;
	}

	public List<PromotionRecordVo> getPromotionRecordsCanSelect() {
		return promotionRecordsCanSelect;
	}

	public void setPromotionRecordsCanSelect(List<PromotionRecordVo> promotionRecordsCanSelect) {
		this.promotionRecordsCanSelect = promotionRecordsCanSelect;
	}

}
