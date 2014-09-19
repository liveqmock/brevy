package com.brevy.core.shiro.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description 登录信息
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -6185279351982793454L;

	/**
	 * 应用系统编号
	 */
	private long appid;
	
	/**
	 * 登录用户名
	 */
	private String username;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 登录验证码
	 */
	private String captcha;
	
	/**
	 * 连接主机名或IP地址字符串
	 */
	private String host;
	
	/**
	 * 记住用户
	 */
	private boolean rememberMe;

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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the rememberMe
	 */
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
