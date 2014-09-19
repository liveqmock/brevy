package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_ROLE_OPER_PERM database table.
 * 
 */
@Entity
@Table(name="AP_REF_ROLE_OPER_PERM")
public class ApRefRoleOperPerm implements Serializable {

	private static final long serialVersionUID = 6794081351074764644L;
	
	@EmbeddedId
	private ApRefRoleOperPermPK id;

    public ApRefRoleOperPerm() {
    }

	public ApRefRoleOperPermPK getId() {
		return this.id;
	}

	public void setId(ApRefRoleOperPermPK id) {
		this.id = id;
	}
	
}