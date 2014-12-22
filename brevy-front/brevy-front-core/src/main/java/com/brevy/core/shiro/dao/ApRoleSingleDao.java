package com.brevy.core.shiro.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApRoleSingle;

/**
 * @description 角色DAO(单一)
 * @author caobin
 * @date 2014年12月22日
 */
public interface ApRoleSingleDao extends CrudRepository<ApRoleSingle, Long> {
	/**
	 * @Description 通过应用编号分页查询角色
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApRoleSingle> findByAppId(long appId, Pageable pageable);
	
	/**
	 * @Description 关键字分页检索角色
	 * @param keyword 关键字
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from ApRoleSingle a where a.appId=:appId and (a.name like :kw or a.code like :kw)")
	Page<ApRoleSingle> searchByKeyword(@Param("kw")String keyword, @Param("appId")long appId, Pageable pageable);
}
