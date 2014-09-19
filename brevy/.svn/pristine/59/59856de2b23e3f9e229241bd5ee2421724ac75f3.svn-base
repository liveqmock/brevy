package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the AP_APPLICATION database table.
 * 
 */
@Entity
@Table(name="AP_APPLICATION")
public class ApApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_APPLICATION_SEQ")
	@TableGenerator(
			name="AP_APPLICATION_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_APPLICATION_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	private String code;

	@Column(name="\"DESC\"")
	private String desc;

	private String name;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

    public ApApplication() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

}