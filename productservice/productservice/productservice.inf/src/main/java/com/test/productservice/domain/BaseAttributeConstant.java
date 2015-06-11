package com.test.productservice.domain;

import com.test.framework.annotation.Column;
import com.test.framework.domain.BaseBo;

/**
 * 记录商品与对应常量间的关系
 * 
 * sku属性及商品参数属性继承此表
 * 
 * @author hzzhaozhenzuo
 * 
 */
public abstract class BaseAttributeConstant extends BaseBo {

	private static final long serialVersionUID = 1L;

	@Column("id")
	private Long id;

	// 所属记录id，如所属sku id或product id
	@Column("belongId")
	private Long belongId;

	// 类别code，可用于检索
	@Column("typeCode")
	private String typeCode;

	// 对应常量记录id
	@Column("codeInfoId")
	private Long codeInfoId;

	@Column("codeInfoValue")
	private String codeInfoValue;

	// 其它值，如颜色属性时，有黑色作为第一值，接着还有图片url作为第二属性值
	@Column("extraValue")
	private String extraValue;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBelongId() {
		return belongId;
	}

	public void setBelongId(Long belongId) {
		this.belongId = belongId;
	}

	public Long getCodeInfoId() {
		return codeInfoId;
	}

	public void setCodeInfoId(Long codeInfoId) {
		this.codeInfoId = codeInfoId;
	}

	public String getCodeInfoValue() {
		return codeInfoValue;
	}

	public void setCodeInfoValue(String codeInfoValue) {
		this.codeInfoValue = codeInfoValue;
	}

	public String getExtraValue() {
		return extraValue;
	}

	public void setExtraValue(String extraValue) {
		this.extraValue = extraValue;
	}

}
