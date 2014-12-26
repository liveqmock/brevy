package com.brevy.core.shiro.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApAccessPerm;

/**
 * @Description 应用访问权限DAO
 * @author caobin
 * @date 2013-12-20
 * @version 1.0
 */
public interface ApAccessPermDao extends PagingAndSortingRepository<ApAccessPerm, Long> {
	/**
	 * @Description 通过应用编号查询有效访问权限
	 * @param appId 应用编号
	 * @return
	 * @author caobin
	 */
	List<ApAccessPerm> findByAppIdOrderBySortAsc(long appId);
	
	
	/**
	 * @Description 通过应用编号分页查询访问权限
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApAccessPerm> findByAppId(long appId, Pageable pageable);
	
	/**
	 * @Description 关键字分页检索访问权限
	 * @param keyword 关键字
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from ApAccessPerm a where a.appId=:appId and (lower(a.name) like lower(:kw) or lower(a.code) like lower(:kw) or lower(a.urlPattern) like lower(:kw) or lower(a.authenticationFilter) like lower(:kw) or lower(a.authorizationFilter) like lower(:kw) or a.sort like :kw)")
	Page<ApAccessPerm> searchByKeyword(@Param("kw")String keyword, @Param("appId")long appId, Pageable pageable);

	/**
	 * @Description 通过访问权限代码查询访问权限
	 * @param code 访问权限代码
	 * @return
	 * @author caobin
	 */
	ApAccessPerm findByCode(String code);
	
	
	/**
	 * @Description 通过id删除访问权限
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_access_perm where id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteAccessPermByIds(@Param("ids")Collection<Long> ids);
	
	
}
