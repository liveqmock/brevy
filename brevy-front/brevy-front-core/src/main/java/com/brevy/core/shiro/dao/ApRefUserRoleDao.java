package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApRefUserRole;
import com.brevy.core.shiro.model.ApRefUserRolePK;
import com.brevy.core.shiro.model.ApRoleSingle;

/**
 * @description 用户角色关系
 * @author caobin
 * @date 2014年12月22日
 */
public interface ApRefUserRoleDao extends CrudRepository<ApRefUserRole, ApRefUserRolePK>{
	
	/**
	 * @Description 通过用户编号分页查询其关联的角色
	 * @param userId 用户编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select ar from ApRoleSingle ar where ar.id in (select arur.id.roleId from ApRefUserRole arur where arur.id.userId=:userId) and (lower(ar.name) like lower(:kw) or lower(ar.code) like lower(:kw))")
	Page<ApRoleSingle> findUserRefRole(@Param("userId")long userid, @Param("kw")String keyword, Pageable pageable);

	
	/**
	 * @Description 分页查询用户可选角色
	 * @param userId 用户编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select ar from ApRoleSingle ar where ar.id not in (select arur.id.roleId from ApRefUserRole arur where arur.id.userId=:userId) and (lower(ar.name) like lower(:kw) or lower(ar.code) like lower(:kw))")
	Page<ApRoleSingle> findCadidateUserRefRole(@Param("userId")long userId, @Param("kw")String keyword, Pageable pageable);
	
	/**
	 * @Description 通过用户组id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_user_role where role_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByRoleIds(@Param("ids")Collection<Long> ids);
}
