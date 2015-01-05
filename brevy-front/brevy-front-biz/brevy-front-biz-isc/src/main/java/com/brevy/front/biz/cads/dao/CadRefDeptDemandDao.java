package com.brevy.front.biz.cads.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadRefDeptDemand;
import com.brevy.front.biz.cads.model.CadRefDeptDemandPK;

/**
 * @description 部门需求评估单关联DAO
 * @author caobin
 * @date 2015年1月5日
 */
public interface CadRefDeptDemandDao extends CrudRepository<CadRefDeptDemand, CadRefDeptDemandPK> {

	@Query("delete from CadRefDeptDemand crdd where crdd.id.demandId=:demandId")
	@Modifying
	void deleteByDemandId(@Param("demandId") long demandId);
}
