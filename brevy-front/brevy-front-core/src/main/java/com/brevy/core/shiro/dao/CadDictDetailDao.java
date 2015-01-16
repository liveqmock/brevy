package com.brevy.core.shiro.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	List<CadDictDetail> findByDictIdOrderByIdAsc(long dictId);
	
	/**
	 * @description 通过关键字分页查询数据字典明细
	 * @param keyword
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from CadDictDetail a where a.dictId=:dictId and (lower(a.name) like lower(:kw) or lower(a.code) like lower(:kw) or lower(a.desc) like lower(:kw)) order by a.id")
	Page<CadDictDetail> searchByKeyword(@Param("kw")String keyword, @Param("dictId")Long dictId, Pageable pageable);
}
