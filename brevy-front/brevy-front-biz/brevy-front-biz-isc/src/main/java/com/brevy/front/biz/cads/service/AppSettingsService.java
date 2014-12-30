package com.brevy.front.biz.cads.service;

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
}
