package com.brevy.front.biz.cads.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CAD_REF_DEPT_DEMAND database table.
 * 
 */
@Entity
@Table(name="CAD_REF_DEPT_DEMAND")
public class CadRefDeptDemand implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CadRefDeptDemandPK id;

	public CadRefDeptDemand() {
	}

	public CadRefDeptDemandPK getId() {
		return this.id;
	}

	public void setId(CadRefDeptDemandPK id) {
		this.id = id;
	}

}