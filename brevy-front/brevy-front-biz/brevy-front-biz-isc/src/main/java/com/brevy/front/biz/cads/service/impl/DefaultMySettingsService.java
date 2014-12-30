package com.brevy.front.biz.cads.service.impl;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.core.shiro.model.ApUserSingle;
import com.brevy.core.shiro.support.realm.CustomJdbcRealm;
import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.core.support.Msa;
import com.brevy.core.support.exception.BizException;
import com.brevy.core.support.exception.BizRuntimException;
import com.brevy.core.support.exception.MessageCode;
import com.brevy.front.biz.cads.dao.MySettingsDao;
import com.brevy.front.biz.cads.service.MySettingsService;
import com.brevy.front.biz.messages.Errors;

@Service
public class DefaultMySettingsService implements MySettingsService {
	
	@Autowired
	private MySettingsDao mySettingsDao;
	
	@Autowired
	private CustomJdbcRealm customJdbcRealm;
	
	@Autowired
	private Msa msa;

	@Transactional
	@Override
	public void resetPassword(String oldPassword, String newPassword) throws BizException{
		//验证旧密码
		ApUserSingle apUser = mySettingsDao.findOne(ShiroUtils.getCurrentUser().getUserId());
		
		HashedCredentialsMatcher hcm = (HashedCredentialsMatcher)customJdbcRealm.getCredentialsMatcher();
		Md5Hash md5Hash = new Md5Hash(oldPassword, null, hcm.getHashIterations());
		String hashedOldPassword = md5Hash.toHex();
		if(!apUser.getPassword().equals(hashedOldPassword)){
			throw new BizException(new MessageCode(Errors.ECADS00001));
		}
		
		//更新新密码
		apUser.setPassword(new Md5Hash(newPassword, null, hcm.getHashIterations()).toHex());
		mySettingsDao.save(apUser);
	}

}
