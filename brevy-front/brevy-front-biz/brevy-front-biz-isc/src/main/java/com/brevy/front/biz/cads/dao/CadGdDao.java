package com.brevy.front.biz.cads.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadGd;

/**
 * @description 技术中心工单DAO
 * @author caobin
 * @date 2014年12月31日
 */
public interface CadGdDao extends CrudRepository<CadGd, Long> {
	/**
	 * @description 通过关键字分页查询工单
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadGd a where lower(a.name) like lower(:kw) or lower(a.briefName) like lower(:kw) or lower(a.implTeam) like lower(:kw) or lower(a.pmName) like lower(:kw) order by a.recvDate")
	Page<CadGd> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
}
