package com.brevy.front.biz.cads.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.ArrayUtils;


/**
 * The persistent class for the CAD_GD database table.
 * 
 */
@Entity
@Table(name="CAD_GD")
public class CadGd implements Serializable {

	private static final long serialVersionUID = -1565777189398949646L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CAD_GD_SEQ")
	@TableGenerator(
			name="CAD_GD_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="CAD_GD_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	private String ad;

	@Column(name="ATTACH_TYPE")
	private String attachType;

	@Column(name="BRIEF_NAME")
	private String briefName;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	private String creator;

	@Column(name="ESTIMATE_JOB")
	private String estimateJob;

	@Column(name="EXEC_TYPE")
	private String execType;

	@Column(name="FINISH_DATE")
	private Timestamp finishDate;

	@Column(name="IMPL_TEAM")
	private String implTeam;
	
	@Column(name="IMPL_TEAM_IDS")
	private String implTeamIds;

	private String ini;

	private String name;

	private String pip;

	@Column(name="PM_NAME")
	private String pmName;
	
	@Column(name="PM_NAME_IDS")
	private String pmNameIds;

	@Column(name="PRE_COND")
	private String preCond;
	
	@Column(name="PRE_COND_IDS")
	private String preCondIds;

	private String priority;

	private String progress;

	private String rdp;

	@Column(name="RECV_DATE")
	private Timestamp recvDate;

	@Column(name="REQUIRE_FINISH_TIME")
	private Timestamp requireFinishTime;

	private String scp;

	private String sit;

	private String smp;

	@Column(name="START_DATE")
	private Timestamp startDate;

	@Column(name="TYPE")
	private String type;

	private String uat;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	@Column(name="USING_RESOURCE")
	private String usingResource;

	@Column(name="USING_TIME")
	private String usingTime;
	
	@Transient
	private String assignToDept;

	public CadGd() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAd() {
		return this.ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAttachType() {
		return this.attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getBriefName() {
		return this.briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
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

	public String getEstimateJob() {
		return this.estimateJob;
	}

	public void setEstimateJob(String estimateJob) {
		this.estimateJob = estimateJob;
	}

	public String getExecType() {
		return this.execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}

	public Timestamp getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Timestamp finishDate) {
		this.finishDate = finishDate;
	}

	public String getImplTeam() {
		return this.implTeam;
	}

	public void setImplTeam(String implTeam) {
		this.implTeam = implTeam;
	}

	public String getIni() {
		return this.ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPip() {
		return this.pip;
	}

	public void setPip(String pip) {
		this.pip = pip;
	}

	public String getPmName() {
		return this.pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPreCond() {
		return this.preCond;
	}

	public void setPreCond(String preCond) {
		this.preCond = preCond;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProgress() {
		return this.progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getRdp() {
		return this.rdp;
	}

	public void setRdp(String rdp) {
		this.rdp = rdp;
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

	public String getScp() {
		return this.scp;
	}

	public void setScp(String scp) {
		this.scp = scp;
	}

	public String getSit() {
		return this.sit;
	}

	public void setSit(String sit) {
		this.sit = sit;
	}

	public String getSmp() {
		return this.smp;
	}

	public void setSmp(String smp) {
		this.smp = smp;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUat() {
		return this.uat;
	}

	public void setUat(String uat) {
		this.uat = uat;
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

	public String getUsingTime() {
		return this.usingTime;
	}

	public void setUsingTime(String usingTime) {
		this.usingTime = usingTime;
	}
	
	
	
	public String getAssignToDept() {
		return assignToDept;
	}

	public void setAssignToDept(String[] assignToDept) {
		//this.assignToDept = assignToDept;
		this.assignToDept = ArrayUtils.toString(assignToDept);
	}


	public String getImplTeamIds() {
		return implTeamIds;
	}

	public void setImplTeamIds(String[] implTeamIds) {
		//this.implTeamIds = implTeamIds;
		this.implTeamIds = ArrayUtils.toString(implTeamIds);
	}

	public String getPmNameIds() {
		return pmNameIds;
	}

	public void setPmNameIds(String[] pmNameIds) {
		//this.pmNameIds = pmNameIds;
		this.pmNameIds = ArrayUtils.toString(pmNameIds);
	}

	public String getPreCondIds() {
		return preCondIds;
	}

	public void setPreCondIds(String[] preCondIds) {
		//this.preCondIds = preCondIds;
		this.preCondIds = ArrayUtils.toString(preCondIds);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[]{assignToDept});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[]{assignToDept});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, new String[]{assignToDept});
	}

}