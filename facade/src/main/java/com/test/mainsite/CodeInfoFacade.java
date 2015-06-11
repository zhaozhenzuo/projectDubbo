package com.test.mainsite;

import java.util.List;

import com.test.codeinfoservice.so.CodeInfoSo;
import com.test.codeinfoservice.vo.CodeInfoVo;

public interface CodeInfoFacade {
	
	public List<CodeInfoVo> searchVosBySo(CodeInfoSo so);
	
	public CodeInfoVo insert(CodeInfoVo vo);
	
	public boolean delete(Long id);

}
