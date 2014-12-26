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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;

import com.brevy.core.shiro.util.ShiroUtils;


/**
 * @Description 用户实体类（单一）
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
@Entity
@Table(name="AP_USER")
public class ApUserSingle implements Serializable {

	private static final long serialVersionUID = -6394795549336824853L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_USER_SEQ")
	@TableGenerator(
			name="AP_USER_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_USER_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="CH_NAME")
	private String chName;

	@Column(name="CH_SPELL_NAME")
	private String chSpellName;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	private String creator;

	@Column(name="FIRST_NAME")
	private String firstName;

	private String gender;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ID_TYPE")
	private String idType;

	@Column(name="LAST_NAME")
	private String lastName;

	private String password;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	@Column(name="USER_TYPE")
	private String userType;

	private String username;
	
	@Column(name="POSITION_ID")
	private long positionId;
	
	@Column(name="DEPT_ID")
	private long deptId;
	
	
	@Transient
	private String dept;
	
	@Transient
	private String position;


    public ApUserSingle() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getChSpellName() {
		return this.chSpellName;
	}

	public void setChSpellName(String chSpellName) {
		this.chSpellName = chSpellName;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	
	

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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