package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_ROLE_MENU database table.
 * 
 */
@Entity
@Table(name="AP_REF_ROLE_MENU")
public class ApRefRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApRefRoleMenuPK id;

    public ApRefRoleMenu() {
    }

	public ApRefRoleMenuPK getId() {
		return this.id;
	}

	public void setId(ApRefRoleMenuPK id) {
		this.id = id;
	}
	
}