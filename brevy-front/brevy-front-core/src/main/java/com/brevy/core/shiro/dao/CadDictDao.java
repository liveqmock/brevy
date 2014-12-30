package com.brevy.core.shiro.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.CadDict;

/**
 * @description 数据字典Dao
 * @author caobin
 * @date 2014年12月23日
 */
public interface CadDictDao extends CrudRepository<CadDict, Long> {

	/**
	 * @description 分页查询数据字典
	 * @param page
	 * @return
	 * @author caobin
	 */
	Page<CadDict> findAll(Pageable page);
	
	
	/**
	 * @description 通过关键字分页查询数据字典
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadDict a where lower(a.name) like lower(:kw) or lower(a.code) like lower(:kw) or lower(a.desc) like lower(:kw)")
	Page<CadDict> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
}
