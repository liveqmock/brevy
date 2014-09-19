package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



/**
 * The persistent class for the AP_USER database table.
 * 
 */
@Entity
@Table(name="AP_USER")
public class ApUser implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="USERGROUP_ID")
	private long usergroupId;

	private String username;

    public ApUser() {
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

	public long getUsergroupId() {
		return this.usergroupId;
	}

	public void setUsergroupId(long usergroupId) {
		this.usergroupId = usergroupId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}