package com.test.productservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.framework.util.ObjectGerateUtil;
import com.test.productservice.dao.SkuAttributeMapper;
import com.test.productservice.domain.SkuAttribute;
import com.test.productservice.service.inf.SkuAttributeService;
import com.test.productservice.so.SkuAttributeSo;
import com.test.productservice.vo.SkuAttributeVo;

@Service("skuAttributeService")
public class SkuAttributeServiceImpl implements SkuAttributeService {

	@Autowired
	private SkuAttributeMapper skuAttributeMapper;

	private static final Logger logger = LoggerFactory.getLogger(SkuAttributeServiceImpl.class);

	@Override
	public SkuAttributeVo insert(SkuAttributeVo vo) {
		SkuAttributeVo resVo = ObjectGerateUtil.insertAndReturnTargetClassInstance(vo, SkuAttributeVo.class,
				skuAttributeMapper);
		if (resVo == null) {
			logger.error("err insert SkuAttribute");
			return null;
		}
		return resVo;
	}

	@Override
	public SkuAttributeVo update(SkuAttributeVo vo) {
		return ObjectGerateUtil.copyAndGenerateObj(skuAttributeMapper.update(vo), SkuAttributeVo.class);
	}

	@Override
	public List<SkuAttribute> selectPoBySo(SkuAttributeSo so) {
		return skuAttributeMapper.selectPoBySo(so);
	}

	@Override
	public List<SkuAttributeVo> selectVoBySo(SkuAttributeSo so) {
		return skuAttributeMapper.selectVoBySo(so);
	}

	@Override
	public Long selectCount(SkuAttributeSo so) {
		return skuAttributeMapper.selectCount(so);
	}

	@Override
	public boolean delete(Long id) {
		return skuAttributeMapper.delete(id);
	}

	@Override
	public SkuAttribute selectPoById(Long id) {
		return skuAttributeMapper.selectPoByPrimaryKey(id);
	}

}
