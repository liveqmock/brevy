package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApUser;

/**
 * @Description 用户DAO
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
public interface ApUserDao extends CrudRepository<ApUser, Long> {

	/**
	 * @Description 通过用户名获取用户信息(忽略status字段)
	 * @param username 用户名
	 * @return
	 * @author caobin
	 */
	ApUser findOneByUsername(String username);
	
	/**
	 * @Description 通过id删除角色
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_role where id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRoleByIds(@Param("ids")Collection<Long> ids);

}
