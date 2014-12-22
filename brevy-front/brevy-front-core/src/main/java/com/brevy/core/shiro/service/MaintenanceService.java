package com.brevy.core.shiro.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.model.ApGroupSingle;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApOperPerm;
import com.brevy.core.shiro.model.ApRoleSingle;

/**
 * @Description 权限管理维护Service
 * @author caobin
 * @date 2013-12-31
 * @version 1.0
 */
public interface MaintenanceService {
	
	/**
	 * @Description 获取当前父节点下的菜单
	 * @param appId 应用编号
	 * @param parentId 父菜单节点编号
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findChildren(long appId, long parentId);
	
	/**
	 * @Description 获取关联当前角色的父节点下的菜单
	 * @param appId 应用编号
	 * @param parentId 父菜单节点编号
	 * @param roleId 角色ID
	 * @return
	 * @author caobin
	 */
	Collection<?> findChildren(long appId, long parentId, long roleId);
	
	/**
	 * @Description 获取当前父节点菜单
	 * @param appId 应用编号
	 * @param parentId 父菜单节点编号
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findParent(long appId, long parentId);
	
	/**
	 * @Description 保存（更新）菜单
	 * @param apMenu
	 * @author caobin
	 */
	void saveOrUpdateApMenu(ApMenu apMenu);
	
	/**
	 * @Description 更新并重排节点
	 * @param apMenu
	 * @return long 需要刷新的节点ID
	 * @author caobin
	 */
	long updateAndRearrangeNodes(ApMenu apMenu);
	
	/**
	 * @Description 获取指定父菜单节点下一级的子菜单节点数量
	 * @param apMenu 
	 * @return
	 * @author caobin
	 */
	long countChildren(ApMenu apMenu);
	
	/**
	 * @Description 级联删除菜单
	 * @param apMenu
	 * @author caobin
	 */
	void delApMenuCascade(ApMenu apMenu);
	
	
	/**
	 * @Description 分页查询访问权限
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApAccessPerm> findApAccessPerms(long appId, Pageable pageable);
	
	
	/**
	 * @Description 分页模糊查询访问权限
	 * @param keyword 关键字
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApAccessPerm> searchApAccessPermsByKeyword(String keyword, long appId, Pageable pageable);
	
	/**
	 * @Description 保存（更新）访问权限
	 * @param apAccessPerm
	 * @author caobin
	 */
	void saveOrUpdateApAccessPerm(ApAccessPerm apAccessPerm);
	
	/**
	 * @Description 检查访问权限代码是否重复
	 * @param code 访问权限代码
	 * @return boolean true-不重复|false-重复
	 * @author caobin
	 */
	boolean checkApAccessPermCode(String code);
	
	
	/**
	 * @Description （批量）删除访问权限
	 * @param ids
	 * @param appId
	 * @author caobin
	 */
	void deleteApAccessPerm(Collection<Long> ids, long appId);
	
	
	/**
	 * @Description 分页查询操作权限
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApOperPerm> findApOperPerms(long appId, Pageable pageable);
	
	
	/**
	 * @Description 分页模糊查询操作权限
	 * @param keyword 关键字
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApOperPerm> searchApOperPermsByKeyword(String keyword, long appId, Pageable pageable);
	
	/**
	 * @Description 保存（更新）操作权限
	 * @param apAccessPerm
	 * @author caobin
	 */
	void saveOrUpdateApOperPerm(ApOperPerm apOperPerm);
	
	/**
	 * @Description 检查操作权限代码是否重复
	 * @param code 操作权限代码
	 * @return boolean true-不重复|false-重复
	 * @author caobin
	 */
	boolean checkApOperPermCode(String code);
	
	
	/**
	 * @Description （批量）删除操作权限
	 * @param ids
	 * @param appId
	 * @author caobin
	 */
	void deleteApOperPerm(Collection<Long> ids, long appId);
	
	/**
	 * @Description 分页查询角色
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApRoleSingle> findApRoles(long appId, Pageable pageable);
	
	
	/**
	 * @Description 分页模糊查询角色
	 * @param keyword 关键字
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApRoleSingle> searchApRolesByKeyword(String keyword, long appId, Pageable pageable);
	
	/**
	 * @Description 保存（更新）角色
	 * @param apRole
	 * @author caobin
	 */
	void saveOrUpdateApRoleSingle(ApRoleSingle apRole);
	
	/**
	 * @Description 检查角色代码是否重复
	 * @param code 角色代码
	 * @return boolean true-不重复|false-重复
	 * @author caobin
	 */
	boolean checkApRoleCode(String code);
	
	
	/**
	 * @Description （批量）删除角色
	 * @param ids
	 * @param appId
	 * @author caobin
	 */
	void deleteApRole(Collection<Long> ids, long appId);
	
	
	/**
	 * @Description 查找指定角色ID对应的候选菜单
	 * @param appId 
	 * @param parentId
	 * @param roleId
	 * @return
	 * @author caobin
	 */
	List<ApMenu> findCandidateMenus(long appId, long parentId, long roleId);
	
	
	/**
	 * @Description 保存角色菜单关系
	 * @param roleId
	 * @param menuId
	 * @author caobin
	 */
	void saveRoleRefMenu(long roleId, long menuId);
	
	/**
	 * @Description 删除角色菜单关系
	 * @param roleId 角色编号
	 * @param menuId 菜单编号
	 * @author caobin
	 */
	void delRoleRefMenu(long roleId, long menuId);
	
	
	/************************ 访问权限  *********************************/
	
	/**
	 * @Description 分页查询角色拥有的访问权限
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApAccessPerm> findRoleRefAccessAuth(long roleId, String keyword, Pageable pageable);
	
	
	/**
	 * @Description 分页查询角色可选的访问权限
	 * @param appId 应用编号
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApAccessPerm> findCandidateAccessAuth(long appId, long roleId, String keyword, Pageable pageable);
	
	
	/**
	 * @Description 保存访问权限和角色的关系
	 * @param roleId 角色编号
	 * @param accessPermId 访问权限编号
	 * @author caobin
	 */
	void saveRoleRefAccessAuth(long roleId, long accessPermId);
	
	/**
	 * @Description 删除访问权限和角色的关系
	 * @param roleId 角色编号
	 * @param accessPermId 访问权限编号
	 * @author caobin
	 */
	void delRoleRefAccessAuth(long roleId, long accessPermId);
	
	
	/**
	 * @Description 批量保存访问权限和角色的关系
	 * @param roleId 角色编号
	 * @param accessPermIds 访问权限编号
	 * @author caobin
	 */
	void saveRoleRefAccessAuths(long roleId, String accessPermIds);
	
	/**
	 * @Description 批量删除访问权限和角色的关系
	 * @param roleId 角色编号
	 * @param accessPermIds 访问权限编号
	 * @author caobin
	 */
	void delRoleRefAccessAuths(long roleId, String accessPermIds);
	
	/************************ 操作权限  *********************************/
	
	
	/**
	 * @Description 分页查询角色拥有的操作权限
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApOperPerm> findRoleRefOperAuth(long roleId, String keyword, Pageable pageable);
	
	
	/**
	 * @Description 分页查询角色可选的操作权限
	 * @param appId 应用编号
	 * @param roleId 角色编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApOperPerm> findCandidateOperAuth(long appId, long roleId, String keyword, Pageable pageable);
	
	
	/**
	 * @Description 保存操作权限和角色的关系
	 * @param roleId 角色编号
	 * @param operPermId 操作权限编号
	 * @author caobin
	 */
	void saveRoleRefOperAuth(long roleId, long operPermId);
	
	/**
	 * @Description 删除操作权限和角色的关系
	 * @param roleId 角色编号
	 * @param operPermId 访问权限编号
	 * @author caobin
	 */
	void delRoleRefOperAuth(long roleId, long operPermId);
	
	
	/**
	 * @Description 批量保存操作权限和角色的关系
	 * @param roleId 角色编号
	 * @param operPermIds 操作权限编号
	 * @author caobin
	 */
	void saveRoleRefOperAuths(long roleId, String operPermIds);
	
	/**
	 * @Description 批量删除操作权限和角色的关系
	 * @param roleId 角色编号
	 * @param operPermIds 操作权限编号
	 * @author caobin
	 */
	void delRoleRefOperAuths(long roleId, String operPermIds);
	
	
	/************************ 用户组  *********************************/
	
	/**
	 * @Description 保存（更新）用户组
	 * @param apGroup
	 * @author caobin
	 */
	void saveOrUpdateApGroupSingle(ApGroupSingle apGroup);
	
	
	/**
	 * @Description 检查用户组代码是否重复
	 * @param code 用户组代码
	 * @return boolean true-不重复|false-重复
	 * @author caobin
	 */
	boolean checkApGroupCode(String code);
	
	
	/**
	 * @Description （批量）删除用户组
	 * @param ids
	 * @param appId
	 * @author caobin
	 */
	void deleteApGroup(Collection<Long> ids, long appId);
	
	/**
	 * @Description 分页查询用户组
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApGroupSingle> findApGroups(long appId, Pageable pageable);
	
	/**
	 * @Description 分页模糊查询用户组
	 * @param keyword 关键字
	 * @param appId
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApGroupSingle> searchApGroupsByKeyword(String keyword, long appId, Pageable pageable);
	
	/**
	 * @Description 分页查询用户组拥有的角色
	 * @param userGroupId 用户组编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApRoleSingle> findUserGroupRefRole(long userGroupId, String keyword, Pageable pageable);
	
	
	/**
	 * @Description 分页查询用户组可选的角色
	 * @param appId 应用编号
	 * @param userGroupId 用户组编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApRoleSingle> findCandidateRole(long appId, long userGroupId, String keyword, Pageable pageable);
	
	
	/**
	 * @Description 保存用户组和角色的关系
	 * @param userGroupId 用户组编号
	 * @param roleId 角色编号
	 * @author caobin
	 */
	void saveUserGroupRefRole(long userGroupId, long roleId);
	
	/**
	 * @Description 删除用户组和角色的关系
	 * @param userGroupId 用户组编号
	 * @param roleId 角色编号
	 * @author caobin
	 */
	void delUserGroupRefRole(long userGroupId, long roleId);
	
	
	/**
	 * @Description 批量保存用户组和角色的关系
	 * @param userGroupId 用户组编号
	 * @param roleIds 角色编号
	 * @author caobin
	 */
	void saveUserGroupRefRoles(long userGroupId, String roleIds);
	
	/**
	 * @Description 批量删除用户组和角色的关系
	 * @param userGroupId 用户组编号
	 * @param roleIds 角色编号
	 * @author caobin
	 */
	void delUserGroupRefRoles(long userGroupId, String roleIds);
	
	/************************ 应用系统 *********************************/
	
	/**
	 * @Description 分页查询应用系统
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApApplication> findApApplications(Pageable pageable);
	
	
	/**
	 * @Description 分页模糊查询应用系统
	 * @param keyword 关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApApplication> searchApApplicationsByKeyword(String keyword, Pageable pageable);
	
	/**
	 * @Description 保存（更新）应用系统
	 * @param apApplication
	 * @author caobin
	 */
	void saveOrUpdateApApplication(ApApplication apApplication);
	
	/**
	 * @Description 检查应用系统代码是否重复
	 * @param code 访问权限代码
	 * @return boolean true-不重复|false-重复
	 * @author caobin
	 */
	boolean checkApApplicationCode(String code);
	
	
	/**
	 * @Description （批量）删除应用系统
	 * @param ids
	 * @param appId
	 * @author caobin
	 */
	void deleteApApplication(Collection<Long> ids);
}

