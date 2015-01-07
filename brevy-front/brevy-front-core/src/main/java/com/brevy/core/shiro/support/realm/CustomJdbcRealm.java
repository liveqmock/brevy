package com.brevy.core.shiro.support.realm;

import static com.brevy.core.shiro.ShiroConstants.ACCOUNT_DISABLED;
import static com.brevy.core.shiro.ShiroConstants.APP_DISABLED;
import static com.brevy.core.shiro.ShiroConstants.INCORRECT_CAPTCHA;
import static com.brevy.core.shiro.ShiroConstants.INVALID_APP;
import static com.brevy.core.shiro.ShiroConstants.INVALID_APP_STATUS;
import static com.brevy.core.shiro.ShiroConstants.INVALID_USER_STATUS;
import static com.brevy.core.shiro.ShiroConstants.USER_NOT_EXIST;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.model.ApOperPerm;
import com.brevy.core.shiro.model.ApRole;
import com.brevy.core.shiro.model.ApUser;
import com.brevy.core.shiro.model.ShiroUser;
import com.brevy.core.shiro.service.CaptchaService;
import com.brevy.core.shiro.service.ShiroService;
import com.brevy.core.shiro.support.token.CustomLoginToken;
import com.brevy.core.shiro.util.ShiroUtils;

/**
 * @Description JDBC REALM
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
public class CustomJdbcRealm extends AuthorizingRealm {

	/** 
	 * @Description 认证
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		//获取登录令牌
		final CustomLoginToken loginToken = (CustomLoginToken)token;
		//开启验证码校验
		if(enableCaptcha){
			boolean correctCode = captchaService.validateCaptchaForID(loginToken.getCaptcha());
			if(!correctCode){
				throw new AuthenticationException(INCORRECT_CAPTCHA);
			}
		}else{
			log.info("disabled captacha validation");
		}
		//获取当前用户信息
		ApUser currentUser = shiroService.findUserDetail(loginToken.getUsername());
		
		if(currentUser != null){
			ApApplication targetApp = (ApApplication)CollectionUtils.find(currentUser.getApp(), new Predicate() {		
				@Override
				public boolean evaluate(Object object) {
					ApApplication app = (ApApplication)object;
					return app != null && app.getId() == loginToken.getAppid();
				}
			});
			
			if(targetApp == null){
				throw new AuthenticationException(String.format(INVALID_APP, loginToken.getAppid()));
			}else if(INVALID_APP_STATUS.equals(targetApp.getStatus())){
				//应用状态无效
				throw new AuthenticationException(String.format(APP_DISABLED, targetApp.getName()));
			}else if(INVALID_USER_STATUS.equals(currentUser.getUsername())){
				//用户状态无效
				throw new DisabledAccountException(String.format(ACCOUNT_DISABLED, currentUser.getUsername()));
			}		
			log.debug("realm name: {}", new Object[]{getName()});
			
			//构造Shiro User
			ShiroUser user = new ShiroUser();
			user.setAppId(targetApp.getId());
			user.setUserId(currentUser.getId());
			user.setUsername(currentUser.getUsername());
			user.setChName(currentUser.getChName());
			user.setGender(currentUser.getGender());
			user.setDeptId(currentUser.getDeptId());
			user.setGroups(ShiroUtils.getGroups(currentUser));
			user.setRoles(ShiroUtils.getRoles(currentUser, true));
			user.setMenus(ShiroUtils.getMenus(currentUser));	
			
			

			
			//构造认证信息
			return new SimpleAuthenticationInfo(user, currentUser.getPassword(), getName());
		}
		//用户不存在
		throw new UnknownAccountException(String.format(USER_NOT_EXIST, loginToken.getUsername()));
	}
	
	
	/** 
	 * @Description 鉴权
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//获取当前realm的shiroUser
		ShiroUser user = (ShiroUser)principals.fromRealm(getName()).iterator().next();
		if(user != null){
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();	

			//获取资源角色拥有的权限(已合并组)
			Set<ApRole> roles = user.getRoles();
			
			if(CollectionUtils.isNotEmpty(roles)){
				for(ApRole ar : roles){
					//将资源访问角色加入授权信息对象		
					if(ar.getAccessPerms() != null){
						for(ApAccessPerm aap : ar.getAccessPerms()){
							authorizationInfo.addRole(aap.getCode());
							log.debug("Added authorized access: [{}(code={})] to authorization info.", new Object[]{aap.getName(), aap.getCode()});	
						}
					}
								
				}
			}


			if(CollectionUtils.isNotEmpty(roles)){
				for(ApRole ar : roles){
					if(ar.getOperPerms() != null){
						for(ApOperPerm aop : ar.getOperPerms()){
							//将角色拥有的操作权限加入授权信息对象
							authorizationInfo.addStringPermission(aop.getAuthorizedOper());
							log.debug("Added authorized oper: [{}] to authorization info.", new Object[]{aop.getAuthorizedOper()});	
						}
					}		
				}
			}		
			
			return authorizationInfo;
		}
		return null;
	}
	
	
	/** 
     * 更新用户授权信息缓存. 
     */  
    public void clearCachedAuthorizationInfo(Object principal) {  
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());  
        clearCachedAuthorizationInfo(principals);  
    }  
  
    /** 
     * 清除所有用户授权信息缓存. 
     */  
   public void clearAllCachedAuthorizationInfo() {  
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();  
        if (cache != null) {  
            for (Object key : cache.keys()) {  
                cache.remove(key);  
            }  
        }  
    }
	
	
	//++cache
	
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 校验码服务
	 */
	@Autowired
	private CaptchaService captchaService;
	
	/**
	 * 开启验证码
	 */
	private boolean enableCaptcha = true;
	
	/**
	 * shiro service
	 */
	@Autowired
	private ShiroService shiroService;


	/**
	 * @param enableCaptcha the enableCaptcha to set
	 */
	public void setEnableCaptcha(boolean enableCaptcha) {
		this.enableCaptcha = enableCaptcha;
	}



}
