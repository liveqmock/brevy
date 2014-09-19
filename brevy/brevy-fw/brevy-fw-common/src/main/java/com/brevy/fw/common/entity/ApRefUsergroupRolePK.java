package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AP_REF_USERGROUP_ROLE database table.
 * 
 */
@Embeddable
public class ApRefUsergroupRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USERGROUP_ID")
	private long usergroupId;

	@Column(name="ROLE_ID")
	private long roleId;

    public ApRefUsergroupRolePK() {
    }
	public long getUsergroupId() {
		return this.usergroupId;
	}
	public void setUsergroupId(long usergroupId) {
		this.usergroupId = usergroupId;
	}
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApRefUsergroupRolePK)) {
			return false;
		}
		ApRefUsergroupRolePK castOther = (ApRefUsergroupRolePK)other;
		return 
			(this.usergroupId == castOther.usergroupId)
			&& (this.roleId == castOther.roleId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.usergroupId ^ (this.usergroupId >>> 32)));
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		
		return hash;
    }
}