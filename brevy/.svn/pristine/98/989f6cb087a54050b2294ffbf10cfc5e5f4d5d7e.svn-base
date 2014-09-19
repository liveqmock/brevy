package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;



/**
 * The persistent class for the AP_ACCESS_PERM database table.
 * 
 */
@Entity
@Table(name="AP_ACCESS_PERM")
public class ApAccessPerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_ACCESS_PERM_SEQ")
	@TableGenerator(
			name="AP_ACCESS_PERM_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_ACCESS_PERM_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;

	private String code;

	@Column(name="\"ORDER\"")
	private long order;

	@Column(name="PATTERN_EXPR")
	private String patternExpr;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

	@Column(name="URL_PATTERN")
	private String urlPattern;

    public ApAccessPerm() {
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

	public long getOrder() {
		return this.order;
	}

	public void setOrder(long order) {
		this.order = order;
	}

	public String getPatternExpr() {
		return this.patternExpr;
	}

	public void setPatternExpr(String patternExpr) {
		this.patternExpr = patternExpr;
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

	public String getUrlPattern() {
		return this.urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

}