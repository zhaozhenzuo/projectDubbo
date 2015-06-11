package com.test.mainsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.framework.annotation.Facade;
import com.test.productservice.service.inf.CategoryService;
import com.test.productservice.so.CategorySo;
import com.test.productservice.vo.CategoryVo;

@Facade
public class CategoryFacadeImpl implements CategoryFacade {

	@Autowired
	private CategoryService categoryService;

	@Override
	public CategoryVo addCategory(CategoryVo vo) {
		return categoryService.insert(vo);
	}

	@Override
	public List<CategoryVo> searchCategory(CategorySo so) {
		return categoryService.selectVoBySo(so);
	}

}
