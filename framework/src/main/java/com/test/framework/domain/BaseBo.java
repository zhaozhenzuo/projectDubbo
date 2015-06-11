package com.test.framework.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.test.framework.annotation.Column;

public abstract class BaseBo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column("creator_Id")
	private Long creatorId;

	@Column("creator_name")
	private String creatorName;

	@Column("updator_Id")
	private Long updatorId;

	@Column("updator_name")
	private String updatorName;

	@Column("created_Time")
	private Date createdTime;

	@Column("updated_Time")
	private Date updatedTime;

	@Column("re_mark")
	private String remark;

	@Column("lock_version")
	private Long lockVersion;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	public abstract Long getId();

	public abstract void setId(Long id);

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!this.getClass().isInstance(obj)) {
			return false;
		}

		Long id1 = this.getId();
		BaseBo target = (BaseBo) obj;
		Long id2 = target.getId();

		if (id1 == null) {
			return false;
		} 
		
		return id1.equals(id2);
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).hashCode();
	}

}
