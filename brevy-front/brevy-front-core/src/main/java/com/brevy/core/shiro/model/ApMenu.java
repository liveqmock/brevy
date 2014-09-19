package com.brevy.core.shiro.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;


/**
 * @Description 菜单实体类
 * @author caobin
 * @date 2013-12-9
 * @version 1.0
 */
@Entity
@Table(name="AP_MENU")
public class ApMenu implements Serializable {

	private static final long serialVersionUID = -6031302412480119446L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_MENU_SEQ")
	@TableGenerator(
			name="AP_MENU_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_MENU_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;
	
	@Column(name="PARENT_ID")
	private long parentId;

	private String code;
	
	private String name;
	
	private String url;
	
	@Column(name="MODULE_LOCATION")
	private String moduleLocation;

	private long hierarchy;

	private String icon;

	private String leaf;

	private long sort;

	private String status;

	@Column(updatable=false)
	private String creator;
	
	@Column(name="CREATE_TIME", updatable=false)
	private Timestamp createTime;
	
	@Column(insertable=false)
	private String updator;
	
	@Column(name="UPDATE_TIME", insertable=false)
	private Timestamp updateTime;
	
	@Transient
	private List<ApMenu> apMenus;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

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
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the moduleLocation
	 */
	public String getModuleLocation() {
		return moduleLocation;
	}

	/**
	 * @param moduleLocation the moduleLocation to set
	 */
	public void setModuleLocation(String moduleLocation) {
		this.moduleLocation = moduleLocation;
	}

	/**
	 * @return the hierarchy
	 */
	public long getHierarchy() {
		return hierarchy;
	}

	/**
	 * @param hierarchy the hierarchy to set
	 */
	public void setHierarchy(long hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the leaf
	 */
	public String getLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the sort
	 */
	public long getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(long sort) {
		this.sort = sort;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the updator
	 */
	public String getUpdator() {
		return updator;
	}

	/**
	 * @param updator the updator to set
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the apMenus
	 */
	public List<ApMenu> getApMenus() {
		return apMenus;
	}

	/**
	 * @param apMenus the apMenus to set
	 */
	public void setApMenus(List<ApMenu> apMenus) {
		this.apMenus = apMenus;
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
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"apMenus"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{"apMenus"});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}


}