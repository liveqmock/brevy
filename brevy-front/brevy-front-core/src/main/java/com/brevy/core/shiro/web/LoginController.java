package com.brevy.core.shiro.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.service.CaptchaService;
import com.brevy.core.shiro.service.LoginService;
import com.brevy.core.support._enum.CaptchaStyle;
import com.brevy.core.support.web.BaseController;

/**
 * @Description 登录控制器
 * @author caobin
 * @date 2013-12-12
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@RequestMapping("/getAppStore")
	@ResponseBody
	public List<ApApplication> getAppStore(){
		return loginService.findAllApps();
	}
	
	@RequestMapping("/captcha")
	public void initCaptcha(HttpServletRequest request, HttpServletResponse response){
		captchaService.generateCaptcha(CaptchaStyle.STYLE_DEFAULT, response);
	}
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CaptchaService captchaService;
}
