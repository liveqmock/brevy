package com.brevy.core.shiro.service;

import java.util.List;

import com.brevy.core.shiro.model.ApApplication;

/**
 * @Description 登录Service
 * @author caobin
 * @date 2013-12-12
 * @version 1.0
 */
public interface LoginService {

	/**
	 * @Description 查找所有应用
	 * @return
	 * @author caobin
	 */
	List<ApApplication> findAllApps();
}
