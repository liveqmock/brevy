package com.brevy.front.biz.cads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.core.shiro.dao.CadDictDao;
import com.brevy.core.shiro.dao.CadDictDetailDao;
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
	
	@Autowired
	private CadDictDetailDao cadDictDetailDao;

	@Override
	public Page<CadDict> findAllDicts(String keyword, Pageable pageable) {	
		return cadDictDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

	@Transactional
	@Override
	public void saveOrUpdateCadDict(CadDict cadDict) {
		cadDictDao.save(cadDict);
	}

	@Transactional
	@Override
	public void deleteCadDict(List<Long> ids) {		
		cadDictDao.delete(cadDictDao.findAll(ids));
		//删除关联条目
		for(Long dictId : ids){
			cadDictDetailDao.delete(cadDictDetailDao.findByDictId(dictId));
		}	
	}
	
	

}
