package com.brevy.core.shiro.service;

import java.util.List;

import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApUser;

/**
 * @Description Shiro Service
 * @author caobin
 * @date 2013-11-11
 * @version 1.0
 */
public interface ShiroService {

	/**
	 * @Description 保存/更新菜单
	 * @param apMenu 菜单实体
	 * @return ApMenu 被持久化的对象
	 * @author caobin
	 */
	ApMenu saveApMenu(ApMenu apMenu);


	
	/**
	 * @Description 通过应用编号查询访问权限
	 * @param appId 应用编号
	 * @return
	 * @author caobin
	 */
	List<ApAccessPerm> findAccessPermByAppId(long appId);
	
	
	/**
	 * @Description 通过用户名查询用户信息
	 * @param username 用户名
	 * @return
	 * @author caobin
	 */
	ApUser findUserDetail(String username);
	
	/**
	 * @Description 通过用户ID查询用户信息
	 * @param userId 用户ID
	 * @return
	 * @author caobin
	 */
	ApUser findUserById(long userId);
}
