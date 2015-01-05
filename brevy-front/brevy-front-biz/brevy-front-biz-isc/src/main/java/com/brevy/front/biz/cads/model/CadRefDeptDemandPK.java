package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * The primary key class for the CAD_REF_DEPT_DEMAND database table.
 * 
 */
@Embeddable
public class CadRefDeptDemandPK implements Serializable {

	private static final long serialVersionUID = 6494560837279563230L;

	@Column(name="DEPT_ID")
	private long deptId;

	@Column(name="DEMAND_ID")
	private long demandId;

	public CadRefDeptDemandPK() {
	}
	public long getDeptId() {
		return this.deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	public long getDemandId() {
		return this.demandId;
	}
	public void setDemandId(long demandId) {
		this.demandId = demandId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"roles"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{"roles"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, new String[]{"roles"});
	}
}