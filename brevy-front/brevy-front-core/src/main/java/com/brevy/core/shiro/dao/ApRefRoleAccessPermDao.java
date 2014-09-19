package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApRefRoleAccessPerm;
import com.brevy.core.shiro.model.ApRefRoleAccessPermPK;

/**
 * @Description 角色访问权限关系
 * @author caobin
 * @date 2014-5-9
 * @version 1.0
 */
public interface ApRefRoleAccessPermDao extends CrudRepository<ApRefRoleAccessPerm, ApRefRoleAccessPermPK> {
	/**
	 * @Description 通过访问权限id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_access_perm where access_perm_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByAccessPermIds(@Param("ids")Collection<Long> ids);
	
	/**
	 * @Description 通过角色id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_access_perm where role_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByRoldIds(@Param("ids")Collection<Long> ids);
	

	
	/**
	 * @Description 通过角色编号分页查询其拥有的访问权限
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select aap from ApAccessPerm aap where aap.id in (select arrap.id.accessPermId from ApRefRoleAccessPerm arrap where arrap.id.roleId=:roleId) and (aap.name like :kw or aap.code like :kw or aap.urlPattern like :kw)")
	Page<ApAccessPerm> findRoleRefAccessAuth(@Param("roleId")long roleId, @Param("kw")String keyword, Pageable pageable);
	
	
	/**
	 * @Description 分页查询角色可选访问权限
	 * @param appId 应用编号
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select aap from ApAccessPerm aap where aap.appId =:appId and aap.id not in (select arrap.id.accessPermId from ApRefRoleAccessPerm arrap where arrap.id.roleId=:roleId) and aap.authenticationFilter <> 'anon' and (aap.name like :kw or aap.code like :kw or aap.urlPattern like :kw)")
	Page<ApAccessPerm> findCadidateRoleRefAccessAuth(@Param("appId")long appId, @Param("roleId")long roleId, @Param("kw")String keyword, Pageable pageable);
}
