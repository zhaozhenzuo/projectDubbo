package com.test.productservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.dao.CategoryMapper;
import com.test.productservice.domain.Category;
import com.test.productservice.service.inf.CategoryService;
import com.test.productservice.so.CategorySo;
import com.test.productservice.vo.CategoryVo;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public CategoryVo insert(CategoryVo vo) {
		CategoryVo resVo = ObjectGerateUtil.insertAndReturnTargetClassInstance(vo, CategoryVo.class, categoryMapper);
		if (resVo == null) {
			logger.error("err insert Category");
			return null;
		}
		return resVo;
	}

	@Override
	public CategoryVo update(CategoryVo vo) {
		return ObjectGerateUtil.updateAndReturnTargetClassInstance(vo, CategoryVo.class, categoryMapper);
	}

	@Override
	public List<Category> selectPoBySo(CategorySo so) {
		return categoryMapper.selectPoBySo(so);
	}

	@Override
	public List<CategoryVo> selectVoBySo(CategorySo so) {
		return categoryMapper.selectVoBySo(so);
	}

	@Override
	public Long selectCount(CategorySo so) {
		return categoryMapper.selectCount(so);
	}

	@Override
	public boolean delete(Long id) {
		return categoryMapper.delete(id);
	}

	@Override
	public Category selectPoById(Long id) {
		return categoryMapper.selectPoByPrimaryKey(id);
	}

}
