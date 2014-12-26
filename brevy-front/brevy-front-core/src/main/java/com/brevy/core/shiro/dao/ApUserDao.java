package com.brevy.core.shiro.dao;

import org.springframework.data.repository.CrudRepository;

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

}
