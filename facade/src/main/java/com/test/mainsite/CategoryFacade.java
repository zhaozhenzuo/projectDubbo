package com.test.mainsite;

import java.util.List;

import com.test.productservice.so.CategorySo;
import com.test.productservice.vo.CategoryVo;

public interface CategoryFacade {

	//商品类别
	public CategoryVo addCategory(CategoryVo vo);
	
	public List<CategoryVo> searchCategory(CategorySo so);

}
