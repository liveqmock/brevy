package com.brevy.core.shiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @description 数据字典明细实体类
 * @author caobin
 * @date 2014年12月23日
 */
@Entity
@Table(name="CAD_DICT_DETAIL")
public class CadDictDetail implements Serializable {

	private static final long serialVersionUID = 626347780836667256L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CAD_DICT_DETAIL_SEQ")
	@TableGenerator(
			name="CAD_DICT_DETAIL_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="CAD_DICT_DETAIL_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;
	
	@Column(name="DICT_ID")
	private long dictId;
	
	private String name;
	
	private String code;
	
	@Column(name="DESC_")
	private String desc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDictId() {
		return dictId;
	}

	public void setDictId(long dictId) {
		this.dictId = dictId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
