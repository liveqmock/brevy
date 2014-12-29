package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApGroupSingle;
import com.brevy.core.shiro.model.ApRefUserGroup;
import com.brevy.core.shiro.model.ApRefUserGroupPK;

/**
 * @description 用户用户组关系
 * @author caobin
 * @date 2014年12月22日
 */
public interface ApRefUserGroupDao extends CrudRepository<ApRefUserGroup, ApRefUserGroupPK>{
	
	/**
	 * @Description 通过用户编号分页查询其关联的用户组
	 * @param userId 用户编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select ag from ApGroupSingle ag where ag.id in (select arug.id.groupId from ApRefUserGroup arug where arug.id.userId=:userId) and (lower(ag.name) like lower(:kw) or lower(ag.code) like lower(:kw))")
	Page<ApGroupSingle> findUserRefGroup(@Param("userId")long userid, @Param("kw")String keyword, Pageable pageable);

	
	/**
	 * @Description 分页查询用户可选用户组
	 * @param userId 用户编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select ag from ApGroupSingle ag where ag.id not in (select arug.id.groupId from ApRefUserGroup arug where arug.id.userId=:userId) and (lower(ag.name) like lower(:kw) or lower(ag.code) like lower(:kw))")
	Page<ApGroupSingle> findCadidateUserRefGroup(@Param("userId")long userId, @Param("kw")String keyword, Pageable pageable);
	
	/**
	 * @Description 通过用户组id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_user_group where group_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByGroupIds(@Param("ids")Collection<Long> ids);
}
