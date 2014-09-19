package com.brevy.fw.common.core.security.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brevy.fw.common.core.security.shiro.model.ApAccessPerm;
import com.brevy.fw.common.core.security.shiro.model.ApOperPerm;
import com.brevy.fw.common.core.security.shiro.model.ApRole;
import com.brevy.fw.common.core.security.shiro.model.ApUserApp;
import com.brevy.fw.common.support.data.mybatis.MyBatisRepository;

/**
 * @Description shiro的查询DAO
 * @author caobin
 * @date 2013-7-31
 * @version 1.0
 */
public interface ShiroQueryDao extends MyBatisRepository {
	
	/**
	 * @Description 查询用户信息
	 * @param username 用户名
	 * @param appid 应用系统编号
	 * @return
	 * @author caobin
	 */
	ApUserApp findUserAppDetail(@Param("username") String username, @Param("appid")long appid);
	
	
	/**
	 * @Description 查询角色信息(合并用户和用户组) 
	 * @param userid 用户id
	 * @return
	 * @author caobin
	 */
	List<ApRole> findRoleDetail(@Param("userid")long userid);
	
	
	/**
	 * @Description 查询角色操作权限 
	 * @param apRoles 角色集合
	 * @return
	 * @author caobin
	 */
	List<ApOperPerm> findOperPermDetail(@Param("apRoles")List<ApRole> apRoles);
	
	/**
	 * @Description 查询URL访问权限
	 * @param appid 应用id
	 * @return
	 * @author caobin
	 */
	List<ApAccessPerm> findAccessPermDetail(@Param("appid")long appid);
}
