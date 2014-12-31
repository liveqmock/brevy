package com.brevy.front.biz.cads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brevy.front.biz.cads.dao.CadGdDao;
import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.service.MyTasksService;

@Service
public class DefaultMyTasksService implements MyTasksService {
	
	@Autowired
	private CadGdDao cadGdDao;

	@Override
	public Page<CadGd> findAllGDs(String keyword, Pageable pageable) {
		return cadGdDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

}
