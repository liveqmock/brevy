package com.brevy.core.shiro.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;


/**
 * @Description 组实体类（单一）
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
@Entity
@Table(name="AP_GROUP")
public class ApGroupSingle implements Serializable {

	private static final long serialVersionUID = 3017624336942204809L;

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

	@Column(name="CREATE_TIME", updatable=false)
	private Timestamp createTime;

	@Column(updatable=false)
	private String creator;

	@Column(name="DESC_")
	private String desc;

	private String name;

	private String status;

	private String type;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	
    public ApGroupSingle() {
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