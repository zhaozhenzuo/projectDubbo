package com.test.codeinfoservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;
import com.test.framework.domain.BaseBo;

/**
 * 作为常量表<br/>
 * 
 * 可存储：<br/>
 * 颜色，尺寸等常量
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Table("ds_codeinfo")
public class CodeInfo extends BaseBo {

	private static final long serialVersionUID = 1L;

	@ID("id")
	private Long id;
	
	//所属业务分组，如:颜色分组
	@Column("bizGroup")
	private String bizGroup;
	
	//类别code
	@Column("typeCode")
	private String typeCode;
	
	// 类别名称
	@Column("typeName")
	private String typeName;
	
	//常量值
	@Column("value")
	private String value;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getBizGroup() {
		return bizGroup;
	}

	public void setBizGroup(String bizGroup) {
		this.bizGroup = bizGroup;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
