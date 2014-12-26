package com.brevy.core.shiro.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brevy.core.shiro.model.CadDictDetail;

/**
 * @description 数据字典明细Dao
 * @author caobin
 * @date 2014年12月23日
 */
public interface CadDictDetailDao extends CrudRepository<CadDictDetail, Long> {

	/**
	 * @description 通过字典类型查询字典明细
	 * @param dictId
	 * @return
	 * @author caobin
	 */
	List<CadDictDetail> findByDictId(long dictId);
}
