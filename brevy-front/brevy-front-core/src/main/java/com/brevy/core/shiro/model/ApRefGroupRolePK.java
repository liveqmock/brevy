package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The primary key class for the AP_REF_GROUP_ROLE database table.
 * 
 */
@Embeddable
public class ApRefGroupRolePK implements Serializable {

	private static final long serialVersionUID = 293046677755167352L;

	@Column(name="GROUP_ID")
	private long groupId;

	@Column(name="ROLE_ID")
	private long roleId;

    public ApRefGroupRolePK() {
    }
	public long getGroupId() {
		return this.groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
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