package com.test.mainsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;
import com.test.mainsite.SkuFacade;
import com.test.productservice.so.SkuSo;
import com.test.productservice.vo.SkuVo;

@Controller
@RequestMapping("/sku")
public class SkuController {

	@Autowired
	private SkuFacade skuFacade;

	@RequestMapping("/list")
	public String listCodeInfo(SkuSo so, Model model) {
		List<SkuVo> voList = skuFacade.searchVosBySo(so);
		model.addAttribute("res", voList);
		return "sku/skuList";
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResponseResult<List<SkuVo>> search(SkuSo so) {
		List<SkuVo> voList = skuFacade.searchVosBySo(so);
		return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, voList, null);
	}

	@RequestMapping("/save")
	public String save(SkuVo vo, HttpServletRequest request) {
		SkuVo res = skuFacade.insert(vo);
		request.setAttribute("msg", res != null ? "成功" : "失败");
		return "sku/skuAdd";
	}

	@RequestMapping(value = "/savePage", method = RequestMethod.GET)
	public String savePage(HttpServletRequest request, Model model) {
		model.addAttribute("productId", request.getParameter("productId"));
		model.addAttribute("productName", request.getParameter("productName"));
		return "sku/skuAdd";
	}

}
