package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AP_REF_ROLE_OPER_PERM database table.
 * 
 */
@Embeddable
public class ApRefRoleOperPermPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApRefRoleOperPermPK)) {
			return false;
		}
		ApRefRoleOperPermPK castOther = (ApRefRoleOperPermPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.operPermId == castOther.operPermId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		hash = hash * prime + ((int) (this.operPermId ^ (this.operPermId >>> 32)));
		
		return hash;
    }
}