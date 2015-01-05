package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.sql.Timestamp;


/**
 * The persistent class for the CAD_DEMAND_HIS database table.
 * 
 */
@Entity
@Table(name="CAD_DEMAND_HIS")
public class CadDemandHis implements Serializable {

	private static final long serialVersionUID = -4507164571478589643L;

	@Id
	private long id;

	@Column(name="ATTACH_TYPE")
	private String attachType;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	private String creator;

	@Column(name="ESTIMATE_DEV")
	private String estimateDev;

	@Column(name="ESTIMATE_TEST")
	private String estimateTest;

	@Column(name="IMPL_TEAM")
	private String implTeam;

	@Column(name="IMPL_TEAM_IDS")
	private String implTeamIds;

	@Column(name="PRE_COND")
	private String preCond;

	@Column(name="PRE_COND_IDS")
	private String preCondIds;

	private String priority;

	@Column(name="PRJ_NAME")
	private String prjName;

	@Column(name="RECV_DATE")
	private Timestamp recvDate;

	@Column(name="REQUIRE_FINISH_TIME")
	private Timestamp requireFinishTime;

	@Column(name="START_DATE")
	private Timestamp startDate;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;
	
	private String remark;

	public CadDemandHis() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAttachType() {
		return this.attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
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

	public String getEstimateDev() {
		return this.estimateDev;
	}

	public void setEstimateDev(String estimateDev) {
		this.estimateDev = estimateDev;
	}

	public String getEstimateTest() {
		return this.estimateTest;
	}

	public void setEstimateTest(String estimateTest) {
		this.estimateTest = estimateTest;
	}

	public String getImplTeam() {
		return this.implTeam;
	}

	public void setImplTeam(String implTeam) {
		this.implTeam = implTeam;
	}

	public String getImplTeamIds() {
		return this.implTeamIds;
	}

	public void setImplTeamIds(String implTeamIds) {
		this.implTeamIds = implTeamIds;
	}

	public String getPreCond() {
		return this.preCond;
	}

	public void setPreCond(String preCond) {
		this.preCond = preCond;
	}

	public String getPreCondIds() {
		return this.preCondIds;
	}

	public void setPreCondIds(String preCondIds) {
		this.preCondIds = preCondIds;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPrjName() {
		return this.prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public Timestamp getRecvDate() {
		return this.recvDate;
	}

	public void setRecvDate(Timestamp recvDate) {
		this.recvDate = recvDate;
	}

	public Timestamp getRequireFinishTime() {
		return this.requireFinishTime;
	}

	public void setRequireFinishTime(Timestamp requireFinishTime) {
		this.requireFinishTime = requireFinishTime;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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