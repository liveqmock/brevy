package com.brevy.front.biz.cads.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadGdAttach;

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
	CadGd saveOrUpdateCadGd(CadGd cadGd);
	
	/**
	 * @description 添加附件
	 * @param cadGdId 工单ID
	 * @param attachType 附件类型
	 * @param attachPath 附件路径
	 * @author caobin
	 */
	void addAttach(long cadGdId, String attachType, String attachPath);
	
	
	/**
	 * @description 查询指定工单号的附件
	 * @param gdId 工单号
	 * @return
	 * @author caobin
	 */
	Iterable<CadGdAttach> findAllGDAttachments(long gdId);
	
	
	/**
	 * @description 查询指定附件编号的附件
	 * @param attachId 附件编号
	 * @return
	 * @author caobin
	 */
	CadGdAttach findAttachment(long attachId);
	
}
