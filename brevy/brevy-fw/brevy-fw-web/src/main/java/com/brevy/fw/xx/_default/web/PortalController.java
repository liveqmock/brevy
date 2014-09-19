package com.brevy.fw.xx._default.web;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brevy.fw.common.core.security.shiro.model.ApAccessPerm;
import com.brevy.fw.common.core.security.shiro.service.ShiroService;
import com.brevy.fw.common.support.web.controller.BaseController;
import com.brevy.fw.common.util.ShiroUtils;
import com.brevy.fw.xx._default.action.PermTest;

/**
 * @Description 入口控制器
 * @author caobin
 * @date 2013-7-2
 * @version 1.0
 */
@Controller
public class PortalController extends BaseController{
	
	@Value("${page.default.title}")
	private String pageTitle;
	
	@RequestMapping(value="/default", method=RequestMethod.GET)
	public String showPortal(ModelMap modelMap){	
		modelMap.put("pageTitle", pageTitle);
		return "core/default/default";
	}
	
	@RequestMapping(value="/reload", method=RequestMethod.GET)
	public String reloadPerms(){
		//pt.test();
		List<ApAccessPerm> list = shiroService.getAllAccessPermDetail(1L);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for(ApAccessPerm aap : list){
			map.put(aap.getUrlPattern(), aap.getPatternExpr());
		}	
		ShiroUtils.reloadFilterChainDefinitions(map);
		return "core/default/default";
	}
	
	@Autowired
	private ShiroService shiroService;
	@Autowired
	private PermTest pt;
}
