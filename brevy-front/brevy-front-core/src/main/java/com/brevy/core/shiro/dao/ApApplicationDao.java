package com.brevy.core.shiro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brevy.core.shiro.model.ApApplication;

/**
 * @Description 应用数据DAO
 * @author caobin
 * @date 2013-12-12
 * @version 1.0
 */
public interface ApApplicationDao extends CrudRepository<ApApplication, Long> {

	@Query("from ApApplication aap where aap.STATUS='1'")
	List<ApApplication> findAll();
}
