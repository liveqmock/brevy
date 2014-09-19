package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="AP_REF_USER_ROLE")
public class ApRefUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApRefUserRolePK id;

    public ApRefUserRole() {
    }

	public ApRefUserRolePK getId() {
		return this.id;
	}

	public void setId(ApRefUserRolePK id) {
		this.id = id;
	}
	
}