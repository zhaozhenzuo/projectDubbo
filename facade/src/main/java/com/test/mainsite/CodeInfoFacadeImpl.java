package com.test.mainsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.codeinfoservice.service.inf.CodeInfoService;
import com.test.codeinfoservice.so.CodeInfoSo;
import com.test.codeinfoservice.vo.CodeInfoVo;
import com.test.framework.annotation.Facade;

@Facade
public class CodeInfoFacadeImpl implements CodeInfoFacade {

	@Autowired
	private CodeInfoService codeInfoService;

	@Override
	public List<CodeInfoVo> searchVosBySo(CodeInfoSo so) {
		return codeInfoService.selectVoBySo(so);
	}

	@Override
	public CodeInfoVo insert(CodeInfoVo vo) {
		return codeInfoService.insert(vo);
	}

	@Override
	public boolean delete(Long id) {
		return codeInfoService.delete(id);
	}

}
