package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;


/**
 * The persistent class for the CAD_GD_ATTACH database table.
 * 
 */
@Entity
@Table(name="CAD_GD_ATTACH")
public class CadGdAttach implements Serializable {

	private static final long serialVersionUID = -6633592738939589003L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CAD_GD_ATTACH_SEQ")
	@TableGenerator(
			name="CAD_GD_ATTACH_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="CAD_GD_ATTACH_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="GD_ID")
	private long gdId;

	@Column(name="PATH")
	private String path;

	public CadGdAttach() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGdId() {
		return this.gdId;
	}

	public void setGdId(long gdId) {
		this.gdId = gdId;
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