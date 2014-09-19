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
import org.hibernate.annotations.Where;


/**
 * @Description 用户实体类
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
@Entity
@Table(name="AP_USER")
public class ApUser implements Serializable {

	private static final long serialVersionUID = -4356802641521355223L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_USER_SEQ")
	@TableGenerator(
			name="AP_USER_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_USER_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="CH_NAME")
	private String chName;

	@Column(name="CH_SPELL_NAME")
	private String chSpellName;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	private String creator;

	@Column(name="FIRST_NAME")
	private String firstName;

	private String gender;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ID_TYPE")
	private String idType;

	@Column(name="LAST_NAME")
	private String lastName;

	private String password;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	@Column(name="USER_TYPE")
	private String userType;

	private String username;
	
	/**
	 * 用户关联的应用
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(		
			name="AP_REF_USER_APP", //中间表表名
			joinColumns = @JoinColumn(name="USER_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="APP_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApApplication> app = new HashSet<ApApplication>();
	
	/**
	 * 用户关联的组
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Where(clause="STATUS='1'")
	@JoinTable(		
			name="AP_REF_USER_GROUP", //中间表表名
			joinColumns = @JoinColumn(name="USER_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="GROUP_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApGroup> groups = new HashSet<ApGroup>();
	
	
	/**
	 * 用户关联的角色
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Where(clause="STATUS='1'")
	@JoinTable(		
			name="AP_REF_USER_ROLE", //中间表表名
			joinColumns = @JoinColumn(name="USER_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="ROLE_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApRole> roles = new HashSet<ApRole>();

    public ApUser() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getChSpellName() {
		return this.chSpellName;
	}

	public void setChSpellName(String chSpellName) {
		this.chSpellName = chSpellName;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * @return the app
	 */
	public Set<ApApplication> getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(Set<ApApplication> app) {
		this.app = app;
	}
	

	/**
	 * @return the groups
	 */
	public Set<ApGroup> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Set<ApGroup> groups) {
		this.groups = groups;
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
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"app", "roles", "groups"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{"app", "roles", "groups"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, new String[]{"app", "roles", "groups"});
	}

}