package com.brevy.front.biz.cads.dao;

import org.springframework.data.repository.CrudRepository;

import com.brevy.core.shiro.model.ApUserSingle;

/**
 * @description 我的设置DAO
 * @author caobin
 * @date 2014年12月29日
 */
public interface MySettingsDao extends CrudRepository<ApUserSingle, Long> {

	
}
