package com.brevy.front.biz.cads.service.mytask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadGdAttach;

/**
 * @description 工单接口
 * @author caobin
 * @date 2015年1月15日
 */
public interface CadGdService {
	
	/**
	 * @description 查询工单列表
	 * @param keyword 查询关键字
	 * @param monitor 监控情况
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadGd> findGDsRefDept(String keyword, String monitor, Pageable pageable);

	
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
	void addGdAttach(long cadGdId, String attachType, String attachPath);
	
	
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
	CadGdAttach findGdAttachment(long attachId);
	
	/**
	 * @description 工单归档
	 * @param gdId
	 * @author caobin
	 */
	void gdArchive(long gdId);

}
