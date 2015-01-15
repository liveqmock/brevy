package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;



/**
 * The persistent class for the CAD_CATASK_ATTACH database table.
 * 
 */
@Entity
@Table(name="CAD_CATASK_ATTACH")
public class CadCataskAttach implements AbstractCadAttach, Serializable {

	private static final long serialVersionUID = -1745953973270795524L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CAD_CATASK_ATTACH_SEQ")
	@TableGenerator(
			name="CAD_CATASK_ATTACH_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="CAD_CATASK_ATTACH_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="CATASK_ID")
	private long cataskId;

	@Column(name="PATH")
	private String path;

	public CadCataskAttach() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCataskId() {
		return this.cataskId;
	}

	public void setCataskId(long cataskId) {
		this.cataskId = cataskId;
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
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


}