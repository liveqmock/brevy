package com.brevy.core.shiro.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApOperPerm;

/**
 * @Description 应用操作权限
 * @author caobin
 * @date 2014-5-8
 * @version 1.0
 */
public interface ApOperPermDao extends PagingAndSortingRepository<ApOperPerm, Long> {

	/**
	 * @Description 通过应用编号查询操作权限
	 * @param appId 应用编号
	 * @return
	 * @author caobin
	 */
	List<ApOperPerm> findByAppIdOrderBySortAsc(long appId);
	
	/**
	 * @Description 通过应用编号分页查询操作权限
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApOperPerm> findByAppId(long appId, Pageable pageable);
	
	
	/**
	 * @Description 关键字分页检索操作权限
	 * @param keyword 关键字
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from ApOperPerm a where a.appId=:appId and (lower(a.name) like lower(:kw) or lower(a.code) like lower(:kw) or lower(a.authorizedOper) like lower(:kw) or a.sort like :kw)")
	Page<ApOperPerm> searchByKeyword(@Param("kw")String keyword, @Param("appId")long appId, Pageable pageable);

	/**
	 * @Description 通过操作权限代码查询操作权限
	 * @param code 操作权限代码
	 * @return
	 * @author caobin
	 */
	ApOperPerm findByCode(String code);
	
	
	/**
	 * @Description 通过id删除操作权限
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_oper_perm where id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteOperPermByIds(@Param("ids")Collection<Long> ids);
}
