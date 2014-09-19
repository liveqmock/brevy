package com.brevy.fw.common.core.security.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.brevy.fw.common.core.security.shiro.model.SecureLogin;


/**
 * @Description 登录令牌
 * @author caobin
 * @date 2013-7-24
 * @version 1.0
 */
public class LoginToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 229040043767312184L;

	public LoginToken(SecureLogin login) {
		super(login.getUsername(), login.getPassword(), login.isRememberMe(), login.getHost());
		this.appid = login.getAppid();
		this.captcha = login.getCaptcha();
	}	
	
	private long appid;
	
	private String captcha;

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

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
