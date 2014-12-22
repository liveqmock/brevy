package com.brevy.core.shiro.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApGroupSingle;

/**
 * @description 用户组DAO(单一)
 * @author caobin
 * @date 2014年12月19日
 */
public interface ApGroupSingleDao extends CrudRepository<ApGroupSingle, Long> {
	/**
	 * @Description 通过应用编号查询有效用户组
	 * @param appId 应用编号
	 * @return
	 * @author caobin
	 */
	List<ApGroupSingle> findByAppId(long appId);
	
	
	/**
	 * @Description 通过应用编号分页查询用户组
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApGroupSingle> findByAppId(long appId, Pageable pageable);
	
	/**
	 * @Description 关键字分页检索用户组
	 * @param keyword 关键字
	 * @param appId 应用编号
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from ApGroupSingle a where a.appId=:appId and (a.name like :kw or a.code like :kw or a.desc like :kw)")
	Page<ApGroupSingle> searchByKeyword(@Param("kw")String keyword, @Param("appId")long appId, Pageable pageable);

	/**
	 * @Description 通过用户组代码查询用户组
	 * @param code 用户组代码
	 * @return
	 * @author caobin
	 */
	ApGroupSingle findByCode(String code);
}
