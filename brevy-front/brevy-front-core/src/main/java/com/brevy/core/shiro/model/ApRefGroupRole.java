package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_GROUP_ROLE database table.
 * 
 */
@Entity
@Table(name="AP_REF_GROUP_ROLE")
public class ApRefGroupRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApRefGroupRolePK id;

    public ApRefGroupRole() {
    }

	public ApRefGroupRolePK getId() {
		return this.id;
	}

	public void setId(ApRefGroupRolePK id) {
		this.id = id;
	}
	
}