package com.brevy.front.biz.cads.dao;

import org.springframework.data.repository.CrudRepository;

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
}
