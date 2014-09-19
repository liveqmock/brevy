package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AP_REF_USER_APP database table.
 * 
 */
@Embeddable
public class ApRefUserAppPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APP_ID")
	private long appId;

	@Column(name="USER_ID")
	private long userId;

    public ApRefUserAppPK() {
    }
	public long getAppId() {
		return this.appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public long getUserId() {
		return this.userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApRefUserAppPK)) {
			return false;
		}
		ApRefUserAppPK castOther = (ApRefUserAppPK)other;
		return 
			(this.appId == castOther.appId)
			&& (this.userId == castOther.userId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.appId ^ (this.appId >>> 32)));
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		
		return hash;
    }
}