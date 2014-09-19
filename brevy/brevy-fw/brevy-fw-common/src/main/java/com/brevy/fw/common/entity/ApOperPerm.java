package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;



/**
 * The persistent class for the AP_OPER_PERM database table.
 * 
 */
@Entity
@Table(name="AP_OPER_PERM")
public class ApOperPerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_OPER_PERM_SEQ")
	@TableGenerator(
			name="AP_OPER_PERM_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_OPER_PERM_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;

	@Column(name="AUTHORIZED_OPER")
	private String authorizedOper;

	private String code;
	
	private String status;

	@Column(name="\"ORDER\"")
	private long order;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

    public ApOperPerm() {
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

	public String getAuthorizedOper() {
		return this.authorizedOper;
	}

	public void setAuthorizedOper(String authorizedOper) {
		this.authorizedOper = authorizedOper;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public long getOrder() {
		return this.order;
	}

	public void setOrder(long order) {
		this.order = order;
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

}