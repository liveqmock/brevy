package com.brevy.front.biz.cads.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadGdHis;

/**
 * @description 工单归档DAO
 * @author caobin
 * @date 2015年1月4日
 */
public interface CadGdHisDao extends CrudRepository<CadGdHis, Long> {
	/**
	 * @description 通过关键字分页查询已归档工单
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadGdHis a where lower(a.name) like lower(:kw) or lower(a.briefName) like lower(:kw) or lower(a.implTeam) like lower(:kw) or lower(a.pmName) like lower(:kw) order by a.recvDate")
	Page<CadGdHis> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
}
