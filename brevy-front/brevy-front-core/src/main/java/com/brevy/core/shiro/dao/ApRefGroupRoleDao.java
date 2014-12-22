package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApRefGroupRole;
import com.brevy.core.shiro.model.ApRefGroupRolePK;
import com.brevy.core.shiro.model.ApRole;

/**
 * @description 用户组角色关系
 * @author caobin
 * @date 2014年12月19日
 */
public interface ApRefGroupRoleDao extends CrudRepository<ApRefGroupRole, ApRefGroupRolePK> {
	/**
	 * @Description 通过角色id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_group_role where role_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByRoleIds(@Param("ids")Collection<Long> ids);
	
	/**
	 * @Description 通过用户组id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_group_role where group_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByGroupIds(@Param("ids")Collection<Long> ids);
	

	
	/**
	 * @Description 通过用户组编号分页查询其拥有的角色
	 * @param groupId 用户组编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select ar from ApRole ar where ar.id in (select argr.id.roleId from ApRefGroupRole argr where argr.id.groupId=:groupId) and (ar.name like :kw or ar.code like :kw or ar.desc like :kw)")
	Page<ApRole> findGroupRefRole(@Param("groupId")long groupId, @Param("kw")String keyword, Pageable pageable);
	
	
	/**
	 * @Description 分页查询用户组可选角色
	 * @param appId 应用编号
	 * @param userGroupId 用户组编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select ar from ApRole ar where ar.appId =:appId and ar.id not in (select argr.id.roleId from ApRefGroupRole argr where argr.id.groupId=:groupId) and (ar.name like :kw or ar.code like :kw or ar.desc like :kw)")
	Page<ApRole> findCadidateGroupRefRole(@Param("appId")long appId, @Param("groupId")long groupId, @Param("kw")String keyword, Pageable pageable);

}
