package com.brevy.front.biz.cads.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.brevy.core.shiro.dao.CadDictDao;
import com.brevy.core.shiro.dao.CadDictDetailDao;
import com.brevy.core.shiro.model.CadDict;
import com.brevy.core.shiro.model.CadDictDetail;
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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate txTemplate;

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

	@Override
	public Page<CadDictDetail> findAllDictDetails(Long dictId, String keyword,
			Pageable pageable) {
		return cadDictDetailDao.searchByKeyword("%".concat(keyword).concat("%"), dictId, pageable);
	}

	@Transactional
	@Override
	public void saveOrUpdateCadDictDetail(CadDictDetail cadDictDetail) {
		cadDictDetailDao.save(cadDictDetail);
	}

	@Transactional
	@Override
	public void deleteCadDictDetail(List<Long> ids) {
		cadDictDetailDao.delete(cadDictDetailDao.findAll(ids));
	}

	@Override
	public Iterable<CadDictDetail> findAllDictDetails() {
		return cadDictDetailDao.findAll();
	}


	@Override
	public void deleteTable(final String tableName) {
		txTemplate.execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				jdbcTemplate.execute(String.format("delete from %s", tableName));
				return null;
			}		
		});
	}
}
