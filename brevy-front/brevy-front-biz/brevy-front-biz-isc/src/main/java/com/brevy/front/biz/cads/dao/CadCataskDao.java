package com.brevy.front.biz.cads.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadCatask;

/**
 * @description 综合管理任务DAO
 * @author caobin
 * @date 2015年1月15日
 */
public interface CadCataskDao extends CrudRepository<CadCatask, Long> {

	/**
	 * @description 通过关键字分页查询综合管理任务
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadCatask a where lower(a.title) like lower(:kw) or lower(a.jobContent) like lower(:kw) order by a.id")
	Page<CadCatask> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
	
}
