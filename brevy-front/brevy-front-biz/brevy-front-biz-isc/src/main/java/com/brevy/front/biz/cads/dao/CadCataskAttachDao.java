package com.brevy.front.biz.cads.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.front.biz.cads.model.CadCataskAttach;

/**
 * @description 综合管理任务附件DAO
 * @author caobin
 * @date 2015年1月15日
 */
public interface CadCataskAttachDao extends CrudRepository<CadCataskAttach, Long> {
	/**
	 * @description 通过综合管理任务编号查询附件
	 * @param cataskId 综合管理任务编号
	 * @return
	 * @author caobin
	 */
	Iterable<CadCataskAttach> findByCataskId(long cataskId);
	
	
	/**
	 * @description 通过综合管理任务编号删除附件
	 * @param cataskId 综合管理任务编号
	 * @author caobin
	 */
	@Query("delete from CadCataskAttach cca where cca.cataskId=:cataskId")
	@Modifying
	void deleteByCataskId(@Param("cataskId") long cataskId);
}
