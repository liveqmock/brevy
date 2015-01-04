package com.brevy.core.shiro.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @Description User object for shiro
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
public class ShiroUser implements Serializable{

	private static final long serialVersionUID = 2353460137050135016L;

	/**
	 * 应用ID
	 */
	private long appId;
	
	/**
	 * 用户ID
	 */
	private long userId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	
	/**
	 * 部门编号
	 */
	private long deptId;
	
	

	/**
	 * 组
	 */
	private Set<ApGroup> groups;
	
	/**
	 * 角色(合并组后的)
	 */
	private Set<ApRole> roles;
	
	
	/**
	 * 功能菜单(合并组后的)
	 */
	private List<ApMenu> menus;

	/**
	 * @return the appId
	 */
	public long getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
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

	/**
	 * @return the menus
	 */
	public List<ApMenu> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<ApMenu> menus) {
		this.menus = menus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, EXCLUDE_FIELDS);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, EXCLUDE_FIELDS);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, EXCLUDE_FIELDS);
	}
	
	private static final String[] EXCLUDE_FIELDS = new String[]{"appId","userId","groups","roles","menus"};
}
