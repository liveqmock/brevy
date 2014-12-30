package com.brevy.front.biz.cads.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brevy.core.shiro.model.CadDict;

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
}
