package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApGroup;


/**
 * @description 用户组DAO
 * @author caobin
 * @date 2014年12月19日
 */
public interface ApGroupDao extends CrudRepository<ApGroup, Long> {
	
	
	/**
	 * @Description 通过id删除用户组
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_group where id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteGroupByIds(@Param("ids")Collection<Long> ids);
}
