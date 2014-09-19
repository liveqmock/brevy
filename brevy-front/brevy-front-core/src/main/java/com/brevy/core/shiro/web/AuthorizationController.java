package com.brevy.core.shiro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brevy.core.support.web.BaseController;

/**
 * @Description 
 * @author caobin
 * @date 2014-5-9
 * @version 1.0
 */
@Controller
@RequestMapping("/authz")
public class AuthorizationController extends BaseController {
	
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "unauthorized";
	}
}
