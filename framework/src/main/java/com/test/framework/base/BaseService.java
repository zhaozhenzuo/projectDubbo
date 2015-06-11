package com.test.framework.base;

import java.util.List;

/**
 * service层父类
 * 
 * @author hzzhaozhenzuo
 * 
 * @param <PO>
 * @param <VO>
 * @param <SO>
 */
public interface BaseService<PO, VO,SO> {

	public VO insert(VO vo);

	public VO update(VO vo);

	public List<PO> selectPoBySo(SO so);

	public List<VO> selectVoBySo(SO so);

	public Long selectCount(SO so);
	
	public boolean delete(Long id);
	
	public PO selectPoById(Long id);
}
