package com.brevy.core.shiro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * @Description AP_MENU - ROLE 关联表
 * @author caobin
 * @date 2013-12-9
 * @version 1.0
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