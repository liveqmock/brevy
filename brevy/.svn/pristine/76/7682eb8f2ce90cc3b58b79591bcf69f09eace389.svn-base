package com.brevy.fw.common.core.security.shiro.service.impl;

import java.util.List;

import com.brevy.fw.common.core.security.shiro.dao.ShiroQueryDao;
import com.brevy.fw.common.core.security.shiro.model.ApAccessPerm;
import com.brevy.fw.common.core.security.shiro.model.ApOperPerm;
import com.brevy.fw.common.core.security.shiro.model.ApRole;
import com.brevy.fw.common.core.security.shiro.model.ApUserApp;
import com.brevy.fw.common.core.security.shiro.service.ShiroService;
import com.brevy.fw.common.core.security.shiro.token.LoginToken;
import com.brevy.fw.common.support.web.service.BaseService;

/**
 * @Description ShiroService
 * @author caobin
 * @date 2013-7-31
 * @version 1.0
 */
public class ShiroServiceImpl extends BaseService implements ShiroService {

	@Override
	public ApUserApp getUserDetail(LoginToken token) {		
		return shiroQueryDao.findUserAppDetail(token.getUsername(), token.getAppid());
	}
	
	
	@Override
	public List<ApRole> getRoleDetail(long userid) {
		return shiroQueryDao.findRoleDetail(userid);
	}
	

	@Override
	public List<ApOperPerm> getOperPermDetail(List<ApRole> apRoles) {
		return shiroQueryDao.findOperPermDetail(apRoles);
	}
	

	@Override
	public List<ApAccessPerm> getAllAccessPermDetail(long appid) {
		return shiroQueryDao.findAccessPermDetail(appid);
	}

	private ShiroQueryDao shiroQueryDao;

	/**
	 * @param shiroQueryDao the shiroQueryDao to set
	 */
	public void setShiroQueryDao(ShiroQueryDao shiroQueryDao) {
		this.shiroQueryDao = shiroQueryDao;
	}

}
