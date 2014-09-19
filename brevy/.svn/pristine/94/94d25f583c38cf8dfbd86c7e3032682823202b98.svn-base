package com.brevy.fw.common.support.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.fw.common.core.security.shiro.model.SecureLogin;
import com.brevy.fw.common.core.security.shiro.model.ShiroUser;
import com.brevy.fw.common.core.security.shiro.token.LoginToken;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description (登录)身份验证控制器
 * @author caobin
 * @date 2013-7-24
 * @version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {
	
	/*public ModelAndView addAuth(){
		log.info("adding auth");
	}*/
	
	/**
	 * @Description 登录
	 * @param login 前端提交的登录信息
	 * @return
	 * @author caobin
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(@RequestBody SecureLogin login){
		//获取当前登录用户
		Subject currentUser = SecurityUtils.getSubject();
		//判断登录是否已验证
		if(!currentUser.isAuthenticated()){//未认证，执行登录		
			return login(currentUser, login);
		}else{//重复登录
			log.info("detected repeated login");
			//获取当前登录用户身份
			ShiroUser su = (ShiroUser)currentUser.getPrincipal();
			if(!StringUtils.equalsIgnoreCase(su.getUsername(), login.getUsername())){
				log.info("detected different user, log on system using a new token after logging off current user");
				currentUser.logout();
				return login(currentUser, login);
			}		
		}
		return this.successView();		
	}
	
	/**
	 * @Description 登录
	 * @param currentUser 当前用户
	 * @param login 前端提交的登录信息
	 * @return
	 * @author caobin
	 */
	private ModelAndView login(Subject currentUser, SecureLogin login){
		LoginToken upcToken = new LoginToken(login);
		try {
			currentUser.login(upcToken);	
		} catch (Throwable t) {
			return this.failureView(new CoreException(t));
		}	
		return this.successView();		
	}
	
	/**
	 * @Description 登出
	 * @return
	 * @author caobin
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public ModelAndView logout(){
		SecurityUtils.getSubject().logout();
		return this.successView();
	}
}
