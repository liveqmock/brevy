package com.brevy.core.shiro.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brevy.core.shiro.model.ApRefUserApp;
import com.brevy.core.shiro.model.ApRefUserAppPK;

/**
 * @description 用户应用系统关系
 * @author caobin
 * @date 2014年12月22日
 */
public interface ApRefUserApplicationDao extends CrudRepository<ApRefUserApp, ApRefUserAppPK>{

	/**
	 * @Description 通过应用系统id删除关联关系
	 * @param ids
	 * @author caobin
	 */
	@Query(value="delete from ap_ref_user_app where app_id in (:ids)", nativeQuery=true)
	@Modifying
	void deleteRelsByApplicationIds(@Param("ids")Collection<Long> ids);
}
