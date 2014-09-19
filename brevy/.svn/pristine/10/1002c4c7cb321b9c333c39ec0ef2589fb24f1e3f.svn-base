package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_USERGROUP_ROLE database table.
 * 
 */
@Entity
@Table(name="AP_REF_USERGROUP_ROLE")
public class ApRefUsergroupRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApRefUsergroupRolePK id;

    public ApRefUsergroupRole() {
    }

	public ApRefUsergroupRolePK getId() {
		return this.id;
	}

	public void setId(ApRefUsergroupRolePK id) {
		this.id = id;
	}
	
}