package com.brevy.front.biz.cads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brevy.core.shiro.dao.CadDictDao;
import com.brevy.core.shiro.model.CadDict;
import com.brevy.front.biz.cads.service.AppSettingsService;

/**
 * @description 
 * @author caobin
 * @date 2014年12月30日
 */
@Service
public class DefaultAppSettingsService implements AppSettingsService {
	
	@Autowired
	private CadDictDao cadDictDao;

	@Override
	public Page<CadDict> findAllDicts(String keyword, Pageable pageable) {	
		return cadDictDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

}
