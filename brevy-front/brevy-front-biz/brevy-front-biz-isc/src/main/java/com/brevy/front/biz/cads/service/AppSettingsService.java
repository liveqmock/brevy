package com.brevy.front.biz.cads.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.core.shiro.model.CadDict;
import com.brevy.core.shiro.model.CadDictDetail;

/**
 * @description 应用设置
 * @author caobin
 * @date 2014年12月30日
 */
public interface AppSettingsService {

	/**
	 * @description 查询数据字典列表
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadDict> findAllDicts(String keyword, Pageable pageable);
	
	
	/**
	 * @description  保存（更新）数据字典
	 * @param cadDict
	 * @author caobin
	 */
	void saveOrUpdateCadDict(CadDict cadDict);
	
	
	/**
	 * @description 删除数据字典
	 * @param ids
	 * @author caobin
	 */
	void deleteCadDict(List<Long> ids);
	
	/**
	 * @description 查询数据字典明细列表
	 * @param dictId  字典类型ID
	 * @param keyword 查询关键字
	 * @param pageable
	 * @return
	 * @author caobin
	 */
	Page<CadDictDetail> findAllDictDetails(Long dictId, String keyword, Pageable pageable);
	
	
	/**
	 * @description  保存（更新）数据字典明细
	 * @param cadDictDetail
	 * @author caobin
	 */
	void saveOrUpdateCadDictDetail(CadDictDetail cadDictDetail);
	
	
	/**
	 * @description 删除数据字典明细
	 * @param ids
	 * @author caobin
	 */
	void deleteCadDictDetail(List<Long> ids);
}
