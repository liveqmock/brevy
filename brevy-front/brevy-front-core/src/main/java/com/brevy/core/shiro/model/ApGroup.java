package com.brevy.core.shiro.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * @Description 组实体类
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
@Entity
@Table(name="AP_GROUP")
public class ApGroup implements Serializable {

	private static final long serialVersionUID = 5278306617336642912L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_GROUP_SEQ")
	@TableGenerator(
			name="AP_GROUP_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_GROUP_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;

	private String code;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	private String creator;

	@Column(name="DESC_")
	private String desc;

	private String name;

	private String status;

	private String type;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;
	
	/**
	 * 组关联的角色
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(		
			name="AP_REF_GROUP_ROLE", //中间表表名
			joinColumns = @JoinColumn(name="GROUP_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="ROLE_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApRole> roles = new HashSet<ApRole>();
	
    public ApGroup() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	/**
	 * @return the roles
	 */
	public Set<ApRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<ApRole> roles) {
		this.roles = roles;
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