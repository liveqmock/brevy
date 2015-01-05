package com.brevy.front.biz.cads.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadDemandAttach;

/**
 * @description 需求评估附件DAO
 * @author caobin
 * @date 2015年1月5日
 */
public interface CadDemandAttachDao extends CrudRepository<CadDemandAttach, Long> {
	/**
	 * @description 通过需求评估单编号查询附件
	 * @param demandId 需求评估单编号
	 * @return
	 * @author caobin
	 */
	Iterable<CadDemandAttach> findByDemandId(long demandId);
	
	
	/**
	 * @description 通过需求评估单号删除附件
	 * @param demandId 需求评估单编号
	 * @author caobin
	 */
	@Query("delete from CadDemandAttach cda where cda.demandId=:demandId")
	@Modifying
	void deleteByDemandId(@Param("demandId") long demandId);
}
