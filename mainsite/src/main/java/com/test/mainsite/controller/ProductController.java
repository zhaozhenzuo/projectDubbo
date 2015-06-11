package com.test.mainsite.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;
import com.test.mainsite.ProductFacade;
import com.test.productservice.enums.Status;
import com.test.productservice.so.ProductSo;
import com.test.productservice.vo.ProductVo;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductFacade productFacade;

	/**
	 * 面向用户访问的商品列表
	 * 
	 * @param so
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		ProductSo so = new ProductSo();
		so.setStatus(Status.ONLINE);
		List<ProductVo> voList = productFacade.searchProductVosBySo(so);
		model.addAttribute("res", voList);
		return "product/productList";
	}
	
	@RequestMapping("/getByProductIdList")
	public String searchByIdList(Model model) {
		List<ProductVo> voList = productFacade.selectVoByProductIdList(Arrays.asList(1L,2L));
		model.addAttribute("res", voList);
		return "product/productList";
	}

	/**
	 * 商品管理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/manage")
	public String manage(Model model) {
		ProductSo so = new ProductSo();
		List<ProductVo> voList = productFacade.searchProductVosBySo(so);
		model.addAttribute("res", voList);
		return "product/productManage";
	}

	@RequestMapping("/offline")
	public String offlineProduct(Long productId, Model model) {
		String msg = "";
		String page = "product/productManage";
		if (productId == null) {
			msg = "productId为空";
			model.addAttribute("msg", msg);
			return page;
		}

		boolean flag = productFacade.offlineProductByProductId(productId);
		msg = flag ? "成功下线商品:" + productId : "下线商品:" + productId + "失败";
		model.addAttribute("msg", msg);

		return page;
	}

	@RequestMapping("/online")
	public String onlineProduct(Long productId, Model model) {
		String msg = "";
		String page = "product/productList";
		if (productId == null) {
			msg = "productId为空";
			model.addAttribute("msg", msg);
			return page;
		}

		boolean flag = productFacade.onlineProductByProductId(productId);
		msg = flag ? "成功上线商品:" + productId : "上线商品:" + productId + "失败";
		model.addAttribute("msg", msg);

		return page;
	}

	@RequestMapping("/detail")
	public String detail(Long productId, Model model) {
		ProductSo so = new ProductSo();
		so.setProductIdList(Arrays.asList(productId));
		ProductVo productVo = productFacade.getProductVoWithSkuInfoByProductId(productId);
		model.addAttribute("res", productVo);
		return "product/productDetail";
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResponseResult<List<ProductVo>> searchProduct(@RequestBody ProductSo so) {
		List<ProductVo> productVoList = productFacade.searchProductVosBySo(so);
		return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, productVoList, null);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseResult<Boolean> saveProduct(ProductVo vo) {
		ProductVo res = productFacade.saveProduct(vo);
		return ResponseUtil.generateResponse(res != null ? CodeInfoClass.SUCCESS : CodeInfoClass.COMMON_FAIL, null,
				null);
	}

	@RequestMapping(value = "/savePage", method = RequestMethod.GET)
	public String saveProduct() {
		return "product/productAdd";
	}

}
