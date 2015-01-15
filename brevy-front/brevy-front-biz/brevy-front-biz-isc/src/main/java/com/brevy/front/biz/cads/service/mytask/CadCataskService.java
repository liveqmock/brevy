package com.brevy.front.biz.cads.service.mytask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.front.biz.cads.model.CadCatask;
import com.brevy.front.biz.cads.model.CadCataskAttach;

/**
 * @description 综合管理任务
 * @author caobin
 * @date 2015年1月15日
 */
public interface CadCataskService {

	/**
	 * @description 查询综合管理任务列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadCatask> findAllCatasks(String keyword, Pageable pageable);
	
	/**
	 * @description 保存（更新）综合管理任务
	 * @param cadCatask
	 * @author caobin
	 */
	CadCatask saveOrUpdateCatask(CadCatask cadCatask);
	
	/**
	 * @description 添加附件
	 * @param cataskId 综合管理任务编号
	 * @param attachType 附件类型
	 * @param attachPath 附件路径
	 * @author caobin
	 */
	void addCataskAttach(long cataskId, String attachType, String attachPath);
	
	
	/**
	 * @description 查询指定综合管理任务号的附件
	 * @param cataskId 综合管理任务号
	 * @return
	 * @author caobin
	 */
	Iterable<CadCataskAttach> findAllCataskAttachments(long cataskId);
	
	
	/**
	 * @description 查询指定附件编号的附件
	 * @param attachId 附件编号
	 * @return
	 * @author caobin
	 */
	CadCataskAttach findCataskAttachment(long attachId);
	
	/**
	 * @description 综合管理任务归档
	 * @param cataskId 综合管理任务号
	 * @author caobin
	 */
	void cataskArchive(long cataskId);
}
