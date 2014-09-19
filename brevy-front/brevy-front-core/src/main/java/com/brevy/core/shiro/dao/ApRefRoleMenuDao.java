package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApRefRoleMenu;
import com.brevy.core.shiro.model.ApRefRoleMenuPK;

/**
 * @Description 角色菜单关系
 * @author caobin
 * @date 2014-5-9
 * @version 1.0
 */
public interface ApRefRoleMenuDao extends CrudRepository<ApRefRoleMenu, ApRefRoleMenuPK> {

	/**
	 * @Description 通过菜单id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_menu where menu_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByMenuIds(@Param("ids")Collection<Long> ids);
	
	/**
	 * @Description 通过菜单id删除关联关系
	 * @param id
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_menu where menu_id =:id", nativeQuery=true)
	@Modifying
	void deleteRelsByMenuId(@Param("id")Long id);
	
	
	/**
	 * @Description 通过角色id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_role_menu where role_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByRoldIds(@Param("ids")Collection<Long> ids);
	
}
