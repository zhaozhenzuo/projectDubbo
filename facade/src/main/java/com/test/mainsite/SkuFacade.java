package com.test.mainsite;

import java.util.List;

import com.test.productservice.so.SkuSo;
import com.test.productservice.vo.SkuVo;

public interface SkuFacade {
	
	public List<SkuVo> searchVosBySo(SkuSo so);
	
	public SkuVo insert(SkuVo vo);

}
