package com.brevy.core.shiro.service;


import javax.servlet.http.HttpServletResponse;

import com.brevy.core.support._enum.CaptchaStyle;

/**
 * @Description 验证码Service
 * @author caobin
 * @date 2013-12-19
 * @version 1.0
 */
public interface CaptchaService {

	/**
	 * @Description 生成验证码
	 * @param captchaStyle 验证码样式
	 * @param response
	 * @author caobin
	 */
	void generateCaptcha(CaptchaStyle captchaStyle, HttpServletResponse response);

	
	/**
	 * @Description 通过实际提交的验证码与当前Session中的验证码进行匹配
	 * @param actual 实际提交的验证码
	 * @return
	 * @author caobin
	 */
	boolean validateCaptchaForID(String actual);
}
