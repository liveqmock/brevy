package com.brevy.fw.common.core.security.shiro.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brevy.fw.common.core.security.shiro.model.ApAccessPerm;
import com.brevy.fw.common.core.security.shiro.model.ApOperPerm;
import com.brevy.fw.common.core.security.shiro.model.ApRole;
import com.brevy.fw.common.core.security.shiro.model.ApUserApp;
import com.brevy.fw.common.core.security.shiro.token.LoginToken;

/**
 * @Description shiro服务(事务)层
 * @author caobin
 * @date 2013-7-31
 * @version 1.0
 */
public interface ShiroService {
	/**
	 * @Description 通过登录令牌获取用户信息
	 * @param token 登录令牌
	 * @return
	 * @author caobin
	 */
	ApUserApp getUserDetail(LoginToken token);
	
	
	/**
	 * @Description 通过用户id获取角色信息
	 * @param userid 用户id
	 * @return
	 * @author caobin
	 */
	List<ApRole> getRoleDetail(long userid);
	
	
	/**
	 * @Description 通过角色(集)获取用户操作权限
	 * @param apRoles 角色(集)
	 * @return
	 * @author caobin
	 */
	List<ApOperPerm> getOperPermDetail(List<ApRole> apRoles);
	
	/**
	 * @Description 通过应用id获取所有URL访问权限
	 * @param appid 应用id
	 * @return
	 * @author caobin
	 */
	List<ApAccessPerm> getAllAccessPermDetail(@Param("appid")long appid);
}
