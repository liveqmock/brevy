package com.brevy.front.biz.cads.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadDemand;

/**
 * @description 技术中心需求评估单DAO
 * @author caobin
 * @date 2015年1月5日
 */
public interface CadDemandDao extends CrudRepository<CadDemand, Long> {
	/**
	 * @description 通过关键字关联部门分页查询需求评估单
	 * @param deptId 部门编号
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadDemand a, CadRefDeptDemand b where a.id=b.id.demandId and b.id.deptId=:deptId and (lower(a.prjName) like lower(:kw) or lower(a.implTeam) like lower(:kw)) order by a.id")
	Page<CadDemand> searchByKeyword(@Param("deptId")long deptId, @Param("kw")String keyword, Pageable pageable);
	
	
	/**
	 * @description 通过关键字分页查询需求评估单
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadDemand a where lower(a.prjName) like lower(:kw) or lower(a.implTeam) like lower(:kw) order by a.id")
	Page<CadDemand> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
}
