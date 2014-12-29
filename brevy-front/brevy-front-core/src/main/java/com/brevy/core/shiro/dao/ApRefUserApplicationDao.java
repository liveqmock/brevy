package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.model.ApRefUserApp;
import com.brevy.core.shiro.model.ApRefUserAppPK;

/**
 * @description 用户应用系统关系
 * @author caobin
 * @date 2014年12月22日
 */
public interface ApRefUserApplicationDao extends CrudRepository<ApRefUserApp, ApRefUserAppPK>{
	
	/**
	 * @Description 通过用户编号分页查询其关联的应用系统
	 * @param userId 用户编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select aa from ApApplication aa where aa.id in (select arua.id.appId from ApRefUserApp arua where arua.id.userId=:userId) and (lower(aa.name) like lower(:kw) or lower(aa.code) like lower(:kw))")
	Page<ApApplication> findUserRefApplication(@Param("userId")long userid, @Param("kw")String keyword, Pageable pageable);

	
	/**
	 * @Description 分页查询用户可选应用系统
	 * @param userId 用户编号
	 * @param keyword 检索关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select aa from ApApplication aa where aa.id not in (select arua.id.appId from ApRefUserApp arua where arua.id.userId=:userId) and (lower(aa.name) like lower(:kw) or lower(aa.code) like lower(:kw))")
	Page<ApApplication> findCadidateUserRefApp(@Param("userId")long userId, @Param("kw")String keyword, Pageable pageable);
	
	/**
	 * @Description 通过应用系统id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_user_app where app_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByApplicationIds(@Param("ids")Collection<Long> ids);
}
