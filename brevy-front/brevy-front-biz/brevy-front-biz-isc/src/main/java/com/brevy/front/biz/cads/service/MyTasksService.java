package com.brevy.front.biz.cads.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.front.biz.cads.model.CadCataskHis;
import com.brevy.front.biz.cads.model.CadDemandHis;
import com.brevy.front.biz.cads.model.CadGdHis;
import com.brevy.front.biz.cads.service.mytask.CadCataskService;
import com.brevy.front.biz.cads.service.mytask.CadDemandService;
import com.brevy.front.biz.cads.service.mytask.CadGdService;

/**
 * @description 我的任务Service
 * @author caobin
 * @date 2014年12月31日
 */
public interface MyTasksService extends CadGdService, CadDemandService, CadCataskService{

	/**
	 * @description 查询已归档工单列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadGdHis> findAllArchivedGDs(String keyword, Pageable pageable);
	
	/**
	 * @description 查询已归档需求评估单列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadDemandHis> findAllArchivedDemands(String keyword, Pageable pageable);
	
	
	/**
	 * @description 查询已归档综合管理任务列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadCataskHis> findAllArchivedCatasks(String keyword, Pageable pageable);
	
	
}
