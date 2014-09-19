package com.brevy.core.shiro.support.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.brevy.core.shiro.model.LoginInfo;

/**
 * @Description 登录令牌
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
public class CustomLoginToken extends UsernamePasswordToken{

	private static final long serialVersionUID = -4402462633057533647L;

	public CustomLoginToken(LoginInfo loginInfo) {
		super(loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.isRememberMe(), loginInfo.getHost());
		this.appid = loginInfo.getAppid();
		this.captcha = loginInfo.getCaptcha();
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
