package com.test.mainsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.codeinfoservice.so.CodeInfoSo;
import com.test.codeinfoservice.vo.CodeInfoVo;
import com.test.framework.domain.ResponseResult;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;
import com.test.mainsite.CodeInfoFacade;

@Controller
@RequestMapping("/codeinfo")
public class CodeInfoController {

	@Autowired
	private CodeInfoFacade codeInfoFacade;

	@RequestMapping("/list")
	public String listCodeInfo(CodeInfoSo so, Model model) {
		List<CodeInfoVo> voList = codeInfoFacade.searchVosBySo(so);
		model.addAttribute("res", voList);
		return "codeinfo/codeinfoList";
	}
	
	@RequestMapping("/delete")
	public String deleteCodeInfo(Long id, HttpServletRequest request) {
		if(id==null){
			return "codeinfo/codeinfoList";
		}
		boolean deleteFlag=codeInfoFacade.delete(id);
		String content=deleteFlag?"delete successful,id:"+id:"delete fail,id:"+id;
		request.setAttribute("msg", content);
		return "codeinfo/codeinfoList";
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResponseResult<List<CodeInfoVo>> search(CodeInfoSo so) {
		List<CodeInfoVo> voList = codeInfoFacade.searchVosBySo(so);
		return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, voList, null);
	}

	@RequestMapping("/save")
	public String save(CodeInfoVo vo, HttpServletRequest request) {
		CodeInfoVo res = codeInfoFacade.insert(vo);
		request.setAttribute("msg", res != null ? "成功" : "失败");
		return "codeinfo/codeinfoAdd";
	}

	@RequestMapping(value = "/savePage", method = RequestMethod.GET)
	public String saveProduct() {
		return "codeinfo/codeinfoAdd";
	}

}
