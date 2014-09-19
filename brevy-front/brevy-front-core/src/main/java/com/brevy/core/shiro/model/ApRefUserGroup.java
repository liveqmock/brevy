package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_USER_GROUP database table.
 * 
 */
@Entity
@Table(name="AP_REF_USER_GROUP")
public class ApRefUserGroup implements Serializable {

	private static final long serialVersionUID = -136569157924279336L;
	
	@EmbeddedId
	private ApRefUserGroupPK id;

    public ApRefUserGroup() {
    }

	public ApRefUserGroupPK getId() {
		return this.id;
	}

	public void setId(ApRefUserGroupPK id) {
		this.id = id;
	}
	
}