package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApOperPerm;
import com.brevy.core.shiro.model.ApRefRoleOperPerm;
import com.brevy.core.shiro.model.ApRefRoleOperPermPK;

/**
 * @Description 角色操作权限关系
 * @author caobin
 * @date 2014-5-9
 * @version 1.0
 */
public interface ApRefRoleOperPermDao extends CrudRepository<ApRefRoleOperPerm, ApRefRoleOperPermPK> {

	/**
	 * @Description 通过操作权限id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_oper_perm where oper_perm_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByOperPermIds(@Param("ids")Collection<Long> ids);
	
	/**
	 * @Description 通过角色id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_oper_perm where role_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByRoldIds(@Param("ids")Collection<Long> ids);
	
	/**
	 * @Description 通过角色编号分页查询其拥有的操作权限
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select aop from ApOperPerm aop where aop.id in (select arrop.id.operPermId from ApRefRoleOperPerm arrop where arrop.id.roleId=:roleId) and (lower(aop.name) like lower(:kw) or lower(aop.code) like lower(:kw) or lower(aop.authorizedOper) like lower(:kw))")
	Page<ApOperPerm> findRoleRefOperAuth(@Param("roleId")long roleId, @Param("kw")String keyword, Pageable pageable);
	
	
	/**
	 * @Description 分页查询角色可选操作权限
	 * @param appId 应用编号
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select aop from ApOperPerm aop where aop.appId =:appId and aop.id not in (select arrop.id.operPermId from ApRefRoleOperPerm arrop where arrop.id.roleId=:roleId) and (lower(aop.name) like lower(:kw) or lower(aop.code) like lower(:kw) or lower(aop.authorizedOper) like lower(:kw))")
	Page<ApOperPerm> findCadidateRoleRefOperAuth(@Param("appId")long appId, @Param("roleId")long roleId, @Param("kw")String keyword, Pageable pageable);
}
