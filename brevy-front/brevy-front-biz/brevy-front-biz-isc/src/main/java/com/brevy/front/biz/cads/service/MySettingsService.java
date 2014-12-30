package com.brevy.front.biz.cads.service;

import com.brevy.core.support.exception.BizException;

/**
 * @description 我的设置Service
 * @author caobin
 * @date 2014年12月29日
 */
public interface MySettingsService {

	/**
	 * @description 密码重置
	 * @param oldPassword 旧密码（明文）
	 * @param newPassword 新密码 （明文）
	 * @throws BizException
	 * @author caobin
	 */
	void resetPassword(String oldPassword, String newPassword) throws BizException;
}
