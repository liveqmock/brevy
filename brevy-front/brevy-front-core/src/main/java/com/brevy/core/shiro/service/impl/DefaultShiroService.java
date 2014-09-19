package com.brevy.core.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.core.shiro.dao.ApAccessPermDao;
import com.brevy.core.shiro.dao.ApMenuDao;
import com.brevy.core.shiro.dao.ApUserDao;
import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApUser;
import com.brevy.core.shiro.service.ShiroService;

/**
 * @Description 
 * @author caobin
 * @date 2013-12-12
 * @version 1.0
 */
@Service
public class DefaultShiroService implements ShiroService {

	@Transactional
	@Override
	public ApMenu saveApMenu(ApMenu apMenu) {
		return apMenuDao.save(apMenu);
	}
	


	@Override
	public List<ApAccessPerm> findAccessPermByAppId(long appId) {
		return apAccessPermDao.findByAppIdOrderBySortAsc(appId);
	}

	
	@Override
	public ApUser findUserDetail(String username) {
		return apUserDao.findOneByUsername(username);
	}
	
	@Override
	public ApUser findUserById(long userId) {
		return apUserDao.findOne(userId);
	}

	
	@Autowired
	private ApMenuDao apMenuDao;

	@Autowired
	private ApAccessPermDao apAccessPermDao;

	@Autowired
	private ApUserDao apUserDao;

	
}
