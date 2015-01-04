package com.brevy.front.biz.cads.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.front.biz.cads.model.CadGd;

/**
 * @description 我的任务Service
 * @author caobin
 * @date 2014年12月31日
 */
public interface MyTasksService {
	/**
	 * @description 查询工单列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadGd> findGDsRefDept(String keyword, Pageable pageable);
	
	
	/**
	 * @description 查询工单列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadGd> findAllGDs(String keyword, Pageable pageable);
	
	/**
	 * @description 保存（更新）工单
	 * @param cadGd
	 * @author caobin
	 */
	void saveOrUpdateCadGd(CadGd cadGd);
}
