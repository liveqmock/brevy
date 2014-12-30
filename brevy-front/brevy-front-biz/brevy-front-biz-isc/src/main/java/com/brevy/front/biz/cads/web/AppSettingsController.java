package com.brevy.front.biz.cads.web;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.shiro.model.CadDict;
import com.brevy.core.support.web.BaseController;
import com.brevy.front.biz.cads.service.AppSettingsService;

/**
 * @description 应用设置Controller
 * @author caobin
 * @date 2014年12月30日
 */
@Controller
@RequestMapping("/biz/cads/appSettings")
public class AppSettingsController extends BaseController {
	
	@Autowired
	private AppSettingsService appSettingsService;
	
	/**
	 * @description 获取数据字典列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictMgr/getDictList")
	@ResponseBody
	public Page<CadDict> getDictList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return appSettingsService.findAllDicts(keyword, pageable);			
	}
	
	
	/**
	 * @Description 保存（更新）数据字典
	 * @param apGroup
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictMgr/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateDict(@RequestBody CadDict cadDict){
		log.debug(">>>> cadDict from request is : {}", new Object[]{cadDict});
		appSettingsService.saveOrUpdateCadDict(cadDict);
		return this.successView();
	}
	
	
	/**
	 * @description 删除数据字典
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictMgr/delete")
	@ResponseBody
	public ModelAndView deleteDicts(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			appSettingsService.deleteCadDict(Arrays.asList(longs));
		}
		return this.successView();	
	}
}
