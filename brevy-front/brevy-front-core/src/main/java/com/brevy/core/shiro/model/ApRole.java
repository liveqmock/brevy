package com.brevy.core.shiro.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;



/**
 * @Description 角色实体类
 * @author caobin
 * @date 2013-12-20
 * @version 1.0
 */
@Entity
@Table(name="AP_ROLE")
public class ApRole implements Serializable {

	private static final long serialVersionUID = -528782523698204783L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_ROLE_SEQ")
	@TableGenerator(
			name="AP_ROLE_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_ROLE_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;

	private String code;

	@Column(name="DESC_")
	private String desc;

	private String name;
	
	private String type;

	private String status;

	private String updator;
	
	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	private String creator;
	
	/**
	 * 角色关联的菜单
	 */
	@ManyToMany(fetch=FetchType.LAZY)
	@Where(clause="STATUS='1'")
	@JoinTable(		
			name="AP_REF_ROLE_MENU", //中间表表名
			joinColumns = @JoinColumn(name="ROLE_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="MENU_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApMenu> menus = new LinkedHashSet<ApMenu>();

	/**
	 * 角色关联的访问权限
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@Where(clause="STATUS='1'")	
	@JoinTable(		
			name="AP_REF_ROLE_ACCESS_PERM", //中间表表名
			joinColumns = @JoinColumn(name="ROLE_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="ACCESS_PERM_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApAccessPerm> accessPerms = new LinkedHashSet<ApAccessPerm>();
	
	/**
	 * 角色关联的操作权限
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@Where(clause="STATUS='1'")
	@JoinTable(		
			name="AP_REF_ROLE_OPER_PERM", //中间表表名
			joinColumns = @JoinColumn(name="ROLE_ID"),//和当前表中ID关联的中间表的列名
			inverseJoinColumns = @JoinColumn(name="OPER_PERM_ID")//和关联表中ID关联的中间表的列名
	)
	private Set<ApOperPerm> operPerms = new LinkedHashSet<ApOperPerm>();
	
    public ApRole() {
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
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the menus
	 */
	public Set<ApMenu> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(Set<ApMenu> menus) {
		this.menus = menus;
	}
	

	/**
	 * @return the accessPerms
	 */
	public Set<ApAccessPerm> getAccessPerms() {
		return accessPerms;
	}

	/**
	 * @param accessPerms the accessPerms to set
	 */
	public void setAccessPerms(Set<ApAccessPerm> accessPerms) {
		this.accessPerms = accessPerms;
	}

	/**
	 * @return the operPerms
	 */
	public Set<ApOperPerm> getOperPerms() {
		return operPerms;
	}

	/**
	 * @param operPerms the operPerms to set
	 */
	public void setOperPerms(Set<ApOperPerm> operPerms) {
		this.operPerms = operPerms;
	}
	
	@PrePersist
	public void onPersist(){
		this.setCreator(ShiroUtils.getCurrentUser().getUsername());
		this.setCreateTime(new Timestamp(DateTime.now().getMillis()));
	}
	
	@PreUpdate
	public void onUpdate(){
		this.setUpdator(ShiroUtils.getCurrentUser().getUsername());
		this.setUpdateTime(new Timestamp(DateTime.now().getMillis()));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"menus", "accessPerms", "operPerms"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{"menus", "accessPerms", "operPerms"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, new String[]{"menus", "accessPerms", "operPerms"});
	}

}