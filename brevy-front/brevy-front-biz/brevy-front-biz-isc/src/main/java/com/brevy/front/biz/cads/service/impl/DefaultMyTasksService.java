package com.brevy.front.biz.cads.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.front.biz.cads.dao.CadGdDao;
import com.brevy.front.biz.cads.dao.CadRefDeptGdDao;
import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadRefDeptGd;
import com.brevy.front.biz.cads.model.CadRefDeptGdPK;
import com.brevy.front.biz.cads.service.MyTasksService;

@Service
public class DefaultMyTasksService implements MyTasksService {
	
	@Autowired
	private CadGdDao cadGdDao;
	
	@Autowired
	private CadRefDeptGdDao cadRefDeptGdDao;

	@Override
	public Page<CadGd> findGDsRefDept(String keyword, Pageable pageable) {
		return cadGdDao.searchByKeyword(ShiroUtils.getCurrentUser().getDeptId(),"%".concat(keyword).concat("%"), pageable);
	}
	
	@Override
	public Page<CadGd> findAllGDs(String keyword, Pageable pageable) {
		return cadGdDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}


	@Transactional
	@Override
	public void saveOrUpdateCadGd(CadGd cadGd) {	
		CadGd savedGd = cadGdDao.save(cadGd);
		//部门分配
		long[] assignedDepts = savedGd.getAssignToDept();
		for(long assignedDept : assignedDepts){
			CadRefDeptGdPK pk = new CadRefDeptGdPK();
			pk.setGdId(savedGd.getId());
			pk.setDeptId(assignedDept);
			CadRefDeptGd cadRefDeptGd = new CadRefDeptGd();
			cadRefDeptGd.setId(pk);
			cadRefDeptGdDao.save(cadRefDeptGd);
		}	
	}

	
}
