package com.brevy.core.shiro.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;


/**
 * @Description 访问权限实体类
 * @author caobin
 * @date 2013-12-20
 * @version 1.0
 */
@Entity
@Table(name="AP_ACCESS_PERM")
public class ApAccessPerm implements Serializable {

	private static final long serialVersionUID = 3511418511288025295L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_ACCESS_PERM_SEQ")
	@TableGenerator(
			name="AP_ACCESS_PERM_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_ACCESS_PERM_SEQ",
			initialValue=1,
			allocationSize=1		
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;

	@Column(name="AUTHENTICATION_FILTER")
	private String authenticationFilter;

	@Column(name="AUTHORIZATION_FILTER")
	private String authorizationFilter;

	private String code;

	@Column(name="CREATE_TIME", updatable=false)
	private Timestamp createTime;

	@Column(updatable=false)
	private String creator;

	private String name;

	private long sort;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	@Column(name="URL_PATTERN")
	private String urlPattern;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "accessPerms")
	private Set<ApRole> roles = new HashSet<ApRole>();

    public ApAccessPerm() {
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

	public String getAuthenticationFilter() {
		return this.authenticationFilter;
	}

	public void setAuthenticationFilter(String authenticationFilter) {
		this.authenticationFilter = authenticationFilter;
	}

	public String getAuthorizationFilter() {
		return this.authorizationFilter;
	}

	public void setAuthorizationFilter(String authorizationFilter) {
		this.authorizationFilter = authorizationFilter;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSort() {
		return this.sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
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

	public String getUrlPattern() {
		return this.urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}