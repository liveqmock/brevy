package com.brevy.core.shiro.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApApplication;

/**
 * @Description 应用归属DAO
 * @author caobin
 * @date 2013-12-12
 * @version 1.0
 */
public interface ApApplicationDao extends CrudRepository<ApApplication, Long> {

	List<ApApplication> findByStatus(String status);
	
	/**
	 * @Description 通过应用编号分页查询应用系统
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<ApApplication> findAll(Pageable pageable);
	
	/**
	 * @Description 关键字分页检索应用系统
	 * @param keyword 关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	@Query("select a from ApApplication a where lower(a.name) like lower(:kw) or lower(a.code) like lower(:kw) or lower(a.desc) like lower(:kw)")
	Page<ApApplication> searchByKeyword(@Param("kw")String keyword, Pageable pageable);
	
	/**
	 * @Description 通过访问权限代码查询应用系统
	 * @param code 应用系统代码
	 * @return
	 * @author caobin
	 */
	ApApplication findByCode(String code);
	
	
	/**
	 * @Description 通过id删除应用系统
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_application where id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteApplicationByIds(@Param("ids")Collection<Long> ids);

}
