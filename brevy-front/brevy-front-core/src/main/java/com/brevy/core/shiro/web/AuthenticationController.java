package com.brevy.core.shiro.web;

import static org.apache.commons.collections.MapUtils.getLongValue;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.shiro.ShiroConstants;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApUserSingle;
import com.brevy.core.shiro.model.LoginInfo;
import com.brevy.core.shiro.model.ShiroUser;
import com.brevy.core.shiro.support.token.CustomLoginToken;
import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.core.support.exception.CoreException;
import com.brevy.core.support.web.BaseController;
import com.brevy.core.util.ApMenuUtils;

/**
 * @Description (登录)身份验证控制器
 * @author caobin
 * @date 2013-7-24
 * @version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {

	
	/**
	 * @Description 登录
	 * @param loginInfo 前端提交的登录信息
	 * @return
	 * @author caobin
	 */
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(@RequestBody LoginInfo loginInfo){
		//获取当前登录用户
		Subject currentUser = SecurityUtils.getSubject();
		//判断登录是否已验证
		if(!currentUser.isAuthenticated()){//未认证，执行登录		
			return login(currentUser, loginInfo);
		}else{//重复登录
			log.info("detected repeated login");
			//获取当前登录用户身份
			ShiroUser user = (ShiroUser)currentUser.getPrincipal();
			if(!StringUtils.equalsIgnoreCase(user.getUsername(), loginInfo.getUsername())){
				log.info("detected different user, log on system using a new token after logging off current user");
				currentUser.logout();
				return login(currentUser, loginInfo);
			}		
		}
		return this.successView();		
	}
	
	/**
	 * @Description 登录
	 * @param currentUser 当前用户
	 * @param loginInfo 前端提交的登录信息
	 * @return
	 * @author caobin
	 */
	private ModelAndView login(Subject currentUser, LoginInfo loginInfo){
		CustomLoginToken token = new CustomLoginToken(loginInfo);
		try {
			currentUser.login(token);	
		} catch(IncorrectCredentialsException ice){
			return this.failureView(new CoreException(ShiroConstants.INCORRECT_CREDENTIAL));
		}catch (Throwable t) {
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
	
	/**
	 * @Description 获取当前登录用户的中文名
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getCurrentUser")
	@ResponseBody
	public ApUserSingle getCurrentUser(){
		ShiroUser user = ShiroUtils.getCurrentUser();
		ApUserSingle apUserSingle = new ApUserSingle();
		BeanUtils.copyProperties(user, apUserSingle);
		return apUserSingle;
	}
	
	/**
	 * @Description 查询(静态)菜单节点
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getStaticMenu")
	@ResponseBody
	public List<ApMenu> getStaticMenu(@RequestBody Map<String, String> p){
		return ApMenuUtils.generateSubApMenu(ShiroUtils.getCurrentUser().getMenus(), getLongValue(p, "rootValue", -1), null);	
	}

}
