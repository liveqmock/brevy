package com.brevy.fw.common.core.security.shiro.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description Shiro用户对象
 * @author caobin
 * @date 2013-7-25
 * @version 1.0
 */
public class ShiroUser implements Serializable{

	private static final long serialVersionUID = 4735869470284551163L;
	
	/**
	 * 登录用户id
	 */
	private long userid;
	
	/**
	 * 已登录用户
	 */
	private String username;
	
	/**
	 * 应用id
	 */
	private long appid;
	
	public ShiroUser(long userid, long appid, String username) {
		this.userid = userid;
		this.appid = appid;
		this.username = username;
	}

	
	
	/**
	 * @return the userid
	 */
	public long getUserid() {
		return userid;
	}



	/**
	 * @param userid the userid to set
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	

	/**
	 * @return the appid
	 */
	public long getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(long appid) {
		this.appid = appid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
