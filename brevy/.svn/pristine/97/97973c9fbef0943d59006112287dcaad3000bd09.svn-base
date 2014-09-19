	package com.brevy.fw.common.core.security.shiro.realm;

import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_ACCOUNT_DISABLED;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_APP_DISABLED;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_CAPTCHA_INCORRECT;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_INCORRECT_CREDENTIAL;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_INVALID_APP_STATUS;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_INVALID_USER_STATUS;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_USER_OR_APP_NOT_EXIST;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.brevy.fw.common.core.security.shiro.model.ApOperPerm;
import com.brevy.fw.common.core.security.shiro.model.ApRole;
import com.brevy.fw.common.core.security.shiro.model.ApUserApp;
import com.brevy.fw.common.core.security.shiro.model.ShiroUser;
import com.brevy.fw.common.core.security.shiro.service.ShiroService;
import com.brevy.fw.common.core.security.shiro.token.LoginToken;
import com.brevy.fw.common.util.ShiroUtils;
import com.octo.captcha.service.CaptchaService;

/**
 * @Description Realm base on custom JDBC
 * @author caobin
 * @date 2013-7-29
 * @version 1.0
 */
public class CustomJdbcRealm extends AuthorizingRealm implements InitializingBean {

	/**
	 * 获取并处理验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取登录令牌
		LoginToken loginToken = (LoginToken)token;
		//开启验证码校验
		if(enableCaptcha){
			boolean correctCode = captchaService.validateResponseForID(ShiroUtils.getSessionId(), loginToken.getCaptcha());
			if(!correctCode){
				throw new AuthenticationException(E_CAPTCHA_INCORRECT);
			}
		}else{
			log.info("disabled captacha validation");
		}
		//获取用户信息
		ApUserApp userApp = shiroService.getUserDetail(loginToken);
		
		if(userApp != null){
			if(E_INVALID_APP_STATUS.equals(userApp.getAppStatus())){
				//应用状态无效
				throw new AuthenticationException(String.format(E_APP_DISABLED, userApp.getAppName()));
			}else if(E_INVALID_USER_STATUS.equals(userApp.getUsername())){
				//用户状态无效
				throw new DisabledAccountException(String.format(E_ACCOUNT_DISABLED, userApp.getUsername()));
			}		
			log.debug("realm name: {}", new Object[]{getName()});
			//构造验证信息
			return new SimpleAuthenticationInfo(
					new ShiroUser(userApp.getId(), userApp.getAppId(), userApp.getUsername()), 
					userApp.getPassword(), getName()
			);
		}
		//用户或应用不存在
		throw new UnknownAccountException(String.format(E_USER_OR_APP_NOT_EXIST, loginToken.getUsername()));
	}
	
	/**
	 * 获取并处理授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前realm的shiroUser
		ShiroUser shiroUser = (ShiroUser)principals.fromRealm(getName()).iterator().next();
		if(shiroUser != null){
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			//获取角色信息
			List<ApRole> apRoles = shiroService.getRoleDetail(shiroUser.getUserid());
			if(CollectionUtils.isNotEmpty(apRoles)){			
				for(ApRole ar : apRoles){
					log.debug("App id[{}]: Binding role[code:{}, name:{}] on current user[{}].", 
							new Object[]{ar.getAppId(), ar.getCode(), ar.getName(), shiroUser.getUsername()});
					//将角色加入授权信息对象
					authorizationInfo.addRole(ar.getCode());
				}
			}
			//获取角色拥有的操作权限
			List<ApOperPerm> apOperPerms = shiroService.getOperPermDetail(apRoles);
			if(CollectionUtils.isNotEmpty(apOperPerms)){
				for(ApOperPerm aop : apOperPerms){
					log.debug("App id[{}]: Binding permission[{}] on current user[{}].", 
							new Object[]{aop.getAppId(), aop.getAuthorizedOper(), shiroUser.getUsername()});
					//将操作权限加入授权信息对象
					authorizationInfo.addStringPermission(aop.getAuthorizedOper());
				}
			}
			return authorizationInfo;
		}
		return null;
	}
	
	/**
     * 证书(密码)验证
     */
	@Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        CredentialsMatcher cm = getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                throw new IncorrectCredentialsException(E_INCORRECT_CREDENTIAL);
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify " +
                    "credentials during authentication.  If you do not wish for credentials to be examined, you " +
                    "can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }
    }
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(captchaService, "captcha service is required");
		Assert.notNull(shiroService, "shiro service is required");
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * 校验码服务
	 */
	private CaptchaService captchaService;
	
	/**
	 * 开启验证码
	 */
	private boolean enableCaptcha = true;
	
	/**
	 * shiro service
	 */
	private ShiroService shiroService;

	/**
	 * @param captchaService the captchaService to set
	 */
	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	/**
	 * @param enableCaptcha the enableCaptcha to set
	 */
	public void setEnableCaptcha(boolean enableCaptcha) {
		this.enableCaptcha = enableCaptcha;
	}

	/**
	 * @param shiroService the shiroService to set
	 */
	public void setShiroService(ShiroService shiroService) {
		this.shiroService = shiroService;
	}
}
