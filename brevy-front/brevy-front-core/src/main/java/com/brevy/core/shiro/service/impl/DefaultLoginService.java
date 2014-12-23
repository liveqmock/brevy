package com.brevy.core.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brevy.core.shiro.dao.ApApplicationDao;
import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.service.LoginService;

/**
 * @Description 
 * @author caobin
 * @date 2013-12-12
 * @version 1.0
 */
@Service
public class DefaultLoginService implements LoginService {

	@Override
	public List<ApApplication> findAllApps() {
		return apApplicationDao.findByStatus("1");
	}
	
	@Autowired
	private ApApplicationDao apApplicationDao;	
}
