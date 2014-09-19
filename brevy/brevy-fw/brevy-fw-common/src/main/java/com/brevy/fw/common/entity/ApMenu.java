package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;



/**
 * The persistent class for the AP_MENU database table.
 * 
 */
@Entity
@Table(name="AP_MENU")
public class ApMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="AP_MENU_SEQ")
	@TableGenerator(
			name="AP_MENU_SEQ",
			table="AP_SEQ",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_VALUE",
			pkColumnValue="AP_MENU_SEQ",
			initialValue=1,
			allocationSize=1
			
	)
	private long id;

	@Column(name="APP_ID")
	private long appId;

	private String code;

	@Column(name="DISPLAY_ORDER")
	private long displayOrder;

	private String leaf;

	@Column(name="LEAF_ICON")
	private String leafIcon;

	@Column(name="\"LEVEL\"")
	private long level;

	private String name;

	@Column(name="PARENT_ID")
	private long parentId;

	@Column(name="PATH_SEQ")
	private String pathSeq;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	private String updator;

    public ApMenu() {
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

	public long getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(long displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getLeafIcon() {
		return this.leafIcon;
	}

	public void setLeafIcon(String leafIcon) {
		this.leafIcon = leafIcon;
	}

	public long getLevel() {
		return this.level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return this.parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getPathSeq() {
		return this.pathSeq;
	}

	public void setPathSeq(String pathSeq) {
		this.pathSeq = pathSeq;
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

}