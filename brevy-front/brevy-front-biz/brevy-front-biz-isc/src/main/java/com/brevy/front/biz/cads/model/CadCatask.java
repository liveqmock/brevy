package com.brevy.front.biz.cads.model;

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
 * The persistent class for the CAD_CATASK database table.
 * 
 */
@Entity
@Table(name="CAD_CATASK")
public class CadCatask implements Serializable {

	private static final long serialVersionUID = 7674994785484603246L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CAD_CATASK_SEQ")
	@TableGenerator(
			name="CAD_CATASK_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="CAD_CATASK_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="ATTACH_PATH")
	private String attachPath;

	private String category;

	@Column(name="CREATE_TIME", updatable=false)
	private Timestamp createTime;

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

	public CadCatask() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAttachPath() {
		return this.attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
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
	
	
	
	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
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