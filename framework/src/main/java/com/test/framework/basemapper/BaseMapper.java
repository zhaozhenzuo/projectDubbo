package com.test.framework.basemapper;

import java.util.List;

import com.test.framework.so.BaseSo;

/**
 * 所有操作接口父类
 * 
 * @author hzzhaozhenzuo
 * 
 */
public interface BaseMapper<PO,VO> {

	public boolean insert(PO po);
	
	public int update(PO po);

	public List<PO> selectPoBySo(BaseSo so);
	
	public List<VO> selectVoBySo(BaseSo so);
	
	public Long selectCount(BaseSo so);
	
	public PO selectPoByPrimaryKey(Long id);
	
	public boolean delete(Long id);
}
