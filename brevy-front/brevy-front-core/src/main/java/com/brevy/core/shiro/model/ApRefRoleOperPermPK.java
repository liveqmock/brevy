package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The primary key class for the AP_REF_ROLE_OPER_PERM database table.
 * 
 */
@Embeddable
public class ApRefRoleOperPermPK implements Serializable {

	private static final long serialVersionUID = -8308006005410542363L;

	@Column(name="ROLE_ID")
	private long roleId;

	@Column(name="OPER_PERM_ID")
	private long operPermId;

    public ApRefRoleOperPermPK() {
    }
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getOperPermId() {
		return this.operPermId;
	}
	public void setOperPermId(long operPermId) {
		this.operPermId = operPermId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}