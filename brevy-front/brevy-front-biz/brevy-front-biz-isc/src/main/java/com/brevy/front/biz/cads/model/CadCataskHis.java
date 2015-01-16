package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;

import java.sql.Timestamp;


/**
 * The persistent class for the CAD_CATASK_HIS database table.
 * 
 */
@Entity
@Table(name="CAD_CATASK_HIS")
public class CadCataskHis implements Serializable {

	private static final long serialVersionUID = 2492005461019692344L;

	@Id
	private long id;

	private String category;

	@Column(name="CREATE_TIME", updatable=false)
	private Timestamp createTime;

	@Column(updatable=false)
	private String creator;

	@Column(name="FINISH_DATE")
	private Timestamp finishDate;

	@Column(name="FINISH_STATUS")
	private String finishStatus;

	private String importance;

	@Column(name="JOB_CONTENT")
	private String jobContent;

	@Column(name="OPER_LV")
	private String operLv;

	private long progress;

	private String remark;

	@Column(name="REQ_FINISH_DATE")
	private Timestamp reqFinishDate;

	@Column(name="RESULT")
	private String result;

	private String source;

	private String title;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	@Column(name="USING_RESOURCE")
	private String usingResource;
	
	@Column(name="ATTACH_TYPE")
	private String attachType;
	
	@Column(name="USER_ID")
	private long userId;

	public CadCataskHis() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Timestamp getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Timestamp finishDate) {
		this.finishDate = finishDate;
	}

	public String getFinishStatus() {
		return this.finishStatus;
	}

	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}

	public String getImportance() {
		return this.importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getJobContent() {
		return this.jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getOperLv() {
		return this.operLv;
	}

	public void setOperLv(String operLv) {
		this.operLv = operLv;
	}

	public long getProgress() {
		return this.progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getReqFinishDate() {
		return this.reqFinishDate;
	}

	public void setReqFinishDate(Timestamp reqFinishDate) {
		this.reqFinishDate = reqFinishDate;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUsingResource() {
		return this.usingResource;
	}

	public void setUsingResource(String usingResource) {
		this.usingResource = usingResource;
	}
	
	
	
	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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