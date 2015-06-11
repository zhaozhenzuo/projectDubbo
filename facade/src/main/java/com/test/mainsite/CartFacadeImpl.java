package com.test.mainsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.DTO.CartDTO;
import com.test.DTO.CartItemDTO;
import com.test.cartservice.service.CartService;
import com.test.cartservice.service.SkuStockInMemService;
import com.test.cartservice.vo.CartItemVo;
import com.test.cartservice.vo.CartVo;
import com.test.framework.annotation.Facade;
import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.service.inf.ProductService;
import com.test.productservice.service.inf.SkuService;
import com.test.productservice.so.ProductSo;
import com.test.productservice.so.SkuSo;
import com.test.productservice.vo.ProductVo;
import com.test.productservice.vo.SkuVo;

@Facade
public class CartFacadeImpl implements CartFacade {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SkuService skuService;

	@Autowired
	private SkuStockInMemService skuStockInMemService;

	@Override
	public boolean addCartItem(Long userId, Long skuId, Integer count) {
		return cartService.addCartItem(userId, skuId, count);
	}

	@Override
	public boolean deleteCartItem(Long userId, Long skuId) {
		return cartService.deleteCartItem(userId, skuId);
	}

	@Override
	public Integer increaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count) {
		return cartService.increaseSkuNumForCartOfUser(userId, skuId, count);
	}

	@Override
	public Integer descreaseSkuNumForCartOfUser(Long userId, Long skuId, Integer count) {
		return cartService.descreaseSkuNumForCartOfUser(userId, skuId, count);
	}

	@Override
	public CartDTO getCartDTOByUserId(Long userId) {
		// 获取用户购物车
		CartVo cartVo = cartService.getCartVoByUserId(userId);
		CartDTO cartViewDTO = new CartDTO();
		if (cartVo == null) {
			return cartViewDTO;
		}

		BeanUtils.copyProperties(cartVo, cartViewDTO);

		List<Long> skuIdList = new ArrayList<Long>();
		for (CartItemVo cartItemVo : cartVo.getCartItemVoList()) {
			skuIdList.add(cartItemVo.getSkuId());
		}

		// 获取skuVo列表
		List<SkuVo> skuVoList = this.getSkuVosBySkuIdList(skuIdList);
		if (skuVoList == null || skuVoList.size() <= 0) {
			return cartViewDTO;
		}

		List<CartItemDTO> validCartItemDTOList = new ArrayList<CartItemDTO>();
		cartViewDTO.setCartItemDTOList(validCartItemDTOList);

		// 获取商品信息
		Map<Long, ProductVo> productMap = this.getProductVoMapBySkuList(skuVoList);

		Map<Long, SkuVo> skuMap = groupSkuVoBySkuId(skuVoList);

		for (CartItemVo cartItemVo : cartVo.getCartItemVoList()) {
			CartItemDTO cartItemDTO = ObjectGerateUtil.copyAndGenerateObj(cartItemVo, CartItemDTO.class);
			SkuVo skuVo = skuMap.get(cartItemDTO.getSkuId());

			// 商品信息
			cartItemDTO.setProductVo(productMap.get(skuVo.getProductId()));

			// 价格
			cartItemDTO.setOriginalPriceOfOne(skuVo.getMarketPriceDouble());
			cartItemDTO.setSalePriceOfOne(skuVo.getSalePriceDouble());

			validCartItemDTOList.add(cartItemDTO);
		}

		return cartViewDTO;
	}

	private Map<Long, SkuVo> groupSkuVoBySkuId(List<SkuVo> skuVoList) {
		return ObjectGerateUtil.groupMapByKeyAndSingleValue("id", Long.class, skuVoList);
	}

	private Map<Long, ProductVo> getProductVoMapBySkuList(List<SkuVo> skuVoList) {
		Map<Long, ProductVo> productVoMap = new HashMap<Long, ProductVo>();
		if (skuVoList == null || skuVoList.size() <= 0) {
			return productVoMap;
		}

		List<Long> productIdList = new ArrayList<Long>();
		for (SkuVo skuVo : skuVoList) {
			productIdList.add(skuVo.getProductId());
		}

		ProductSo productSo = new ProductSo();
		productSo.setProductIdList(productIdList);
		List<ProductVo> productVoList = productService.selectVoBySo(productSo);

		if (productVoList == null || productVoList.size() <= 0) {
			return productVoMap;
		}

		for (ProductVo productVo : productVoList) {
			productVoMap.put(productVo.getId(), productVo);
		}

		return productVoMap;
	}

	private List<SkuVo> getSkuVosBySkuIdList(List<Long> skuIdList) {
		if (skuIdList == null || skuIdList.size() <= 0) {
			return null;
		}
		SkuSo skuSo = new SkuSo();
		skuSo.setSkuIdList(skuIdList);
		return skuService.selectVoBySo(skuSo);
	}

	@Override
	public Integer getCartCountByUserId(Long userId) {
		return cartService.getCartCountByUserId(userId);
	}

}
