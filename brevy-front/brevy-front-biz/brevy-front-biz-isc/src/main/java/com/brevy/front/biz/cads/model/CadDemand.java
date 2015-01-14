package com.brevy.front.biz.cads.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;

import java.sql.Timestamp;


/**
 * The persistent class for the CAD_DEMAND database table.
 * 
 */
@Entity
@Table(name="CAD_DEMAND")
public class CadDemand implements Serializable {

	private static final long serialVersionUID = -9115493982982500408L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CAD_DEMAND_SEQ")
	@TableGenerator(
			name="CAD_DEMAND_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="CAD_DEMAND_SEQ",
			initialValue=1,
			allocationSize=1		
	)
	private long id;

	@Column(name="ATTACH_TYPE")
	private String attachType;

	@Column(name="CREATE_TIME", updatable=false)
	private Timestamp createTime;

	@Column(updatable=false)
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

	private String remark;

	@Column(name="REQUIRE_FINISH_TIME")
	private Timestamp requireFinishTime;

	@Column(name="START_DATE")
	private Timestamp startDate;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;
	
	@Column(name="DEV_FINISH_DATE")
	private Timestamp devFinishDate;
	
	@Column(name="SIT_WORKLOAD")
	private String sitWorkload;
	
	@Column(name="SIT_FINISH_DATE")
	private Timestamp sitFinishDate;
	
	@Transient
	private long[] assignToDept;

	public CadDemand() {
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

	public void setImplTeamIds(String[] implTeamIds) {
		//this.implTeamIds = implTeamIds;
		this.implTeamIds = ArrayUtils.toString(implTeamIds);
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

	public void setPreCondIds(String[] preCondIds) {
		//this.preCondIds = preCondIds;
		this.preCondIds = ArrayUtils.toString(preCondIds);
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
	public Timestamp getDevFinishDate() {
		return devFinishDate;
	}

	public void setDevFinishDate(Timestamp devFinishDate) {
		this.devFinishDate = devFinishDate;
	}

	public String getSitWorkload() {
		return sitWorkload;
	}

	public void setSitWorkload(String sitWorkload) {
		this.sitWorkload = sitWorkload;
	}

	public Timestamp getSitFinishDate() {
		return sitFinishDate;
	}

	public void setSitFinishDate(Timestamp sitFinishDate) {
		this.sitFinishDate = sitFinishDate;
	}

	public long[] getAssignToDept() {
		return assignToDept;
	}

	public void setAssignToDept(long[] assignToDept) {
		this.assignToDept = assignToDept;
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
		return HashCodeBuilder.reflectionHashCode(this, new String[]{ArrayUtils.toString(assignToDept)});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{ArrayUtils.toString(assignToDept)});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, new String[]{ArrayUtils.toString(assignToDept)});
	}

}