package com.brevy.front.biz.cads.web;

import static org.apache.commons.collections.MapUtils.getString;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.support.exception.BizException;
import com.brevy.core.support.web.BaseController;
import com.brevy.front.biz.cads.service.MySettingsService;

/**
 * @description 我的设置控制器
 * @author caobin
 * @date 2014年12月29日
 */
@Controller
@RequestMapping("/biz/cads/mySettings")
public class MySettingsController extends BaseController {


	@Autowired
	private MySettingsService mySettingsService;
	
	/**
	 * @description 密码重置
	 * @param p
	 * @return
	 * @author caobin
	 * @throws BizException 
	 */
	@RequestMapping("/resetPwd/updatePassword")
	@ResponseBody
	public ModelAndView updatePassword(@RequestBody Map<String, String> p){
		try {
			mySettingsService.resetPassword(getString(p, "oldPassword"), getString(p, "password"));
			return this.successView();
		} catch (BizException e) {
			log.error(e.getMessage(), e);
			return this.failureView(e);
		}
		
	}
}
