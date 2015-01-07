package com.brevy.front.biz.cads.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadDemandHis;

/**
 * @description 需求评估单归档DAO
 * @author caobin
 * @date 2015年1月5日
 */
public interface CadDemandHisDao extends CrudRepository<CadDemandHis, Long> {
	/**
	 * @description 通过关键字分页查询已归档需求评估单
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadDemandHis a where lower(a.prjName) like lower(:kw) or lower(a.implTeam) like lower(:kw) order by a.recvDate")
	Page<CadDemandHis> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
}
