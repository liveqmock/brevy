package com.brevy.front.biz.cads.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadGdAttach;

/**
 * @description 工单附件DAO
 * @author caobin
 * @date 2015年1月4日
 */
public interface CadGdAttachDao extends CrudRepository<CadGdAttach, Long> {

	/**
	 * @description 通过工单编号查询附件
	 * @param gdid 工单编号
	 * @return
	 * @author caobin
	 */
	Iterable<CadGdAttach> findByGdId(long gdid);
	
	
	/**
	 * @description 通过工单号删除附件
	 * @param gdId
	 * @author caobin
	 */
	@Query("delete from CadGdAttach ca where ca.gdId=:gdId")
	@Modifying
	void deleteByGdId(@Param("gdId") long gdId);
}
