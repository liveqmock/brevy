package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_ROLE_ACCESS_PERM database table.
 * 
 */
@Entity
@Table(name="AP_REF_ROLE_ACCESS_PERM")
public class ApRefRoleAccessPerm implements Serializable {

	private static final long serialVersionUID = -2147370456615923735L;
	
	@EmbeddedId
	private ApRefRoleAccessPermPK id;

    public ApRefRoleAccessPerm() {
    }

	public ApRefRoleAccessPermPK getId() {
		return this.id;
	}

	public void setId(ApRefRoleAccessPermPK id) {
		this.id = id;
	}
	
}