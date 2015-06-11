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
import com.test.mainsite.CategoryFacade;
import com.test.productservice.so.CategorySo;
import com.test.productservice.vo.CategoryVo;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryFacade categoryFacade;

	@RequestMapping("/list")
	public String listCodeInfo(CategorySo so, Model model) {
		List<CategoryVo> voList = categoryFacade.searchCategory(so);
		model.addAttribute("res", voList);
		return "category/categoryList";
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResponseResult<List<CategoryVo>> search(CategorySo so) {
		List<CategoryVo> voList = categoryFacade.searchCategory(so);
		return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, voList, null);
	}

	@RequestMapping("/save")
	public String save(CategoryVo vo, HttpServletRequest request) {
		CategoryVo res = categoryFacade.addCategory(vo);
		request.setAttribute("msg", res != null ? "成功" : "失败");
		return "category/categoryAdd";
	}

	@RequestMapping(value = "/savePage", method = RequestMethod.GET)
	public String savePage() {
		return "category/categoryAdd";
	}

}
