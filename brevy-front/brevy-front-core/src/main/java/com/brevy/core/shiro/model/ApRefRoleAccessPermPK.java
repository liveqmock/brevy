package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The primary key class for the AP_REF_ROLE_ACCESS_PERM database table.
 * 
 */
@Embeddable
public class ApRefRoleAccessPermPK implements Serializable {

	private static final long serialVersionUID = -3618097528186122799L;

	@Column(name="ROLE_ID")
	private long roleId;

	@Column(name="ACCESS_PERM_ID")
	private long accessPermId;

    public ApRefRoleAccessPermPK() {
    }
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getAccessPermId() {
		return this.accessPermId;
	}
	public void setAccessPermId(long accessPermId) {
		this.accessPermId = accessPermId;
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