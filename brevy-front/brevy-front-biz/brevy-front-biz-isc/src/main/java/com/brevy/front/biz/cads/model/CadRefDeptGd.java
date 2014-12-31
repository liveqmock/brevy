package com.brevy.front.biz.cads.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CAD_REF_DEPT_GD database table.
 * 
 */
@Entity
@Table(name="CAD_REF_DEPT_GD")
public class CadRefDeptGd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CadRefDeptGdPK id;

	public CadRefDeptGd() {
	}

	public CadRefDeptGdPK getId() {
		return this.id;
	}

	public void setId(CadRefDeptGdPK id) {
		this.id = id;
	}

}