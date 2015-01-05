package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * The persistent class for the CAD_DEMAND_ATTACH database table.
 * 
 */
@Entity
@Table(name="CAD_DEMAND_ATTACH")
public class CadDemandAttach implements Serializable {

	private static final long serialVersionUID = 7932023234463394984L;

	@Id
	private long id;

	@Column(name="DEMAND_ID")
	private long demandId;

	@Column(name="PATH")
	private String path;

	public CadDemandAttach() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDemandId() {
		return this.demandId;
	}

	public void setDemandId(long demandId) {
		this.demandId = demandId;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"roles"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{"roles"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, new String[]{"roles"});
	}

}