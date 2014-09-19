package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AP_REF_USER_APP database table.
 * 
 */
@Entity
@Table(name="AP_REF_USER_APP")
public class ApRefUserApp implements Serializable {

	private static final long serialVersionUID = -3857532095259252632L;
	
	@EmbeddedId
	private ApRefUserAppPK id;

    public ApRefUserApp() {
    }

	public ApRefUserAppPK getId() {
		return this.id;
	}

	public void setId(ApRefUserAppPK id) {
		this.id = id;
	}
	
}