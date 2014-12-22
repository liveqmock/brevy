package com.brevy.core.shiro.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApRole;

/**
 * @Description 角色DAO
 * @author caobin
 * @date 2013-12-10
 * @version 1.0
 */
public interface ApRoleDao extends CrudRepository<ApRole, Long> {
	/**
	 * @Description 通过应用编号查询有效角色
	 * @param appId 应用编号
	 * @return
	 * @author caobin
	 */
	List<ApRole> findByAppId(long appId);
	
	
	/**
	 * @Description 通过应用编号分页查询角色
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApRole> findByAppId(long appId, Pageable pageable);
	
	/**
	 * @Description 关键字分页检索角色
	 * @param keyword 关键字
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from ApRole a where a.appId=:appId and (a.name like :kw or a.code like :kw)")
	Page<ApRole> searchByKeyword(@Param("kw")String keyword, @Param("appId")long appId, Pageable pageable);

	/**
	 * @Description 通过角色代码查询角色
	 * @param code 角色代码
	 * @return
	 * @author caobin
	 */
	ApRole findByCode(String code);
	
	
	/**
	 * @Description 通过id删除角色
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_role where id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRoleByIds(@Param("ids")Collection<Long> ids);
}
