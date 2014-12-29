package com.brevy.core.shiro.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the AP_REF_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="AP_REF_USER_ROLE")
public class ApRefUserRole implements Serializable {

	private static final long serialVersionUID = -5103664478401354975L;
	
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