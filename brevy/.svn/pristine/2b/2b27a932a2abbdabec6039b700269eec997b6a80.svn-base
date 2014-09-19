package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AP_REF_ROLE_MENU database table.
 * 
 */
@Embeddable
public class ApRefRoleMenuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID")
	private long roleId;

	@Column(name="MENU_ID")
	private long menuId;

    public ApRefRoleMenuPK() {
    }
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getMenuId() {
		return this.menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApRefRoleMenuPK)) {
			return false;
		}
		ApRefRoleMenuPK castOther = (ApRefRoleMenuPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.menuId == castOther.menuId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		hash = hash * prime + ((int) (this.menuId ^ (this.menuId >>> 32)));
		
		return hash;
    }
}