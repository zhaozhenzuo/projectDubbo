package com.test.codeinfoservice.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.codeinfoservice.dao.CodeInfoMapper;
import com.test.codeinfoservice.domain.CodeInfo;
import com.test.codeinfoservice.service.inf.CodeInfoService;
import com.test.codeinfoservice.so.CodeInfoSo;
import com.test.codeinfoservice.vo.CodeInfoVo;
import com.test.framework.util.ObjectGerateUtil;

@Service("codeInfoService")
public class CodeinfoServiceImpl implements CodeInfoService {

	@Autowired
	private CodeInfoMapper codeInfoServiceMapper;

	private static final Logger logger = LoggerFactory.getLogger(CodeinfoServiceImpl.class);

	public CodeInfoVo insert(CodeInfoVo vo) {
		CodeInfoVo resVo = ObjectGerateUtil.insertAndReturnTargetClassInstance(vo, CodeInfoVo.class,
				codeInfoServiceMapper);
		if (resVo == null) {
			logger.error("err insert codeInfo");
			return null;
		}
		return resVo;
	}

	public CodeInfoVo update(CodeInfoVo vo) {
		return ObjectGerateUtil.updateAndReturnTargetClassInstance(vo, CodeInfoVo.class, codeInfoServiceMapper);
	}

	public List<CodeInfo> selectPoBySo(CodeInfoSo so) {
		return codeInfoServiceMapper.selectPoBySo(so);
	}

	public List<CodeInfoVo> selectVoBySo(CodeInfoSo so) {
		return codeInfoServiceMapper.selectVoBySo(so);
	}

	public Long selectCount(CodeInfoSo so) {
		return codeInfoServiceMapper.selectCount(so);
	}

	public boolean delete(Long id) {
		return codeInfoServiceMapper.delete(id);
	}

	@Override
	public CodeInfo selectPoById(Long id) {
		return codeInfoServiceMapper.selectPoByPrimaryKey(id);
	}

}
