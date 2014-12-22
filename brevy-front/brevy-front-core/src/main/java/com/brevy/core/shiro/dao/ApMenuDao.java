package com.brevy.core.shiro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApMenu;

/**
 * @Description 菜单DAO
 * @author caobin
 * @date 2013-12-9
 * @version 1.0
 */
public interface ApMenuDao extends CrudRepository<ApMenu, Long>{
	
	/**
	 * @Description 查询当前父节点下的菜单
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param parentId 父菜单节点编号
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findByAppIdAndParentId(long appId, long parentId);
	
	/**
	 * @Description 查询当前父节点下的菜单并升序排序
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param parentId 父菜单节点编号
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findByAppIdAndParentIdOrderBySortAsc(long appId, long parentId);

	
	/**
	 * @Description 查询当前父节点菜单
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param parentId 父菜单节点编号
	 * @param leaf 
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findByAppIdAndParentIdAndLeafOrderBySortAsc(long appId, long parentId, String leaf);
	
	
	/**
	 * @Description 查询当前父节点下的指定排序号区间的菜单
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param parentId 父菜单节点编号
	 * @param id
	 * @param sortMin 起始排序号
	 * @param sortMax 结束排序号
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findByAppIdAndParentIdAndIdNotAndSortBetweenOrderBySortAsc(long appId, long parentId, long id, long sortMin, long sortMax);

	
	/**
	 * @Description 查询当前父节点下的顺序大于指定排序号的菜单
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param id
	 * @param sort
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findByAppIdAndParentIdAndSortGreaterThanOrderBySortAsc(long appId, long id, long sort);
	
	/**
	 * @Description 查询当前父节点下一级的节点总数
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param parentId 父菜单节点编号
	 * @return
	 * @author caobin
	 */
	long countByAppIdAndParentId(long appId, long parentId);
	
	
	/**
	 * @Description 通过roleId查找候选菜单
	 * @param appId 应用编号(区分父节点为-1的情况)
	 * @param parentId 父菜单节点编号
	 * @param roleId 角色ID
	 * @return
	 * @author caobin
	 */
	@Query("select am from ApMenu am where am.appId=:appId and am.parentId =:parentId and am.status='1' and am.id not in (select arrm.id.menuId from ApRefRoleMenu arrm where arrm.id.roleId=:roleId)")
	List<ApMenu> findCandidateMenus(@Param("appId")long appId, @Param("parentId")long parentId, @Param("roleId")long roleId);
}
