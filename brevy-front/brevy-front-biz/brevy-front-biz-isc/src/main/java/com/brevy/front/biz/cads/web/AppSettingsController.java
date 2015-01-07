package com.brevy.front.biz.cads.web;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getLongValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.shiro.model.CadDict;
import com.brevy.core.shiro.model.CadDictDetail;
import com.brevy.core.support.exception.BizException;
import com.brevy.core.support.exception.MessageCode;
import com.brevy.core.support.web.BaseController;
import com.brevy.front.biz.cads.service.AppSettingsService;
import com.brevy.front.biz.messages.Errors;

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
	
	@Value("${cads.permission.resetTables}")
	private String permittedResetTablesPattern;
	
	
	
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
	 * @param cadDict
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
	
	/**
	 * @description 获取所有数据字典明细列表
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictMgr/getAllDictDetailList")
	@ResponseBody
	public Iterable<CadDictDetail> getAllDictList(){
		return appSettingsService.findAllDictDetails();			
	}
	
	/**
	 * @description 获取数据字典明细列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictDetailMgr/getDictDetailList")
	@ResponseBody
	public Page<CadDictDetail> getDictDetailList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return appSettingsService.findAllDictDetails(getLongValue(p, "dictId"), keyword, pageable);			
	}
	
	
	/**
	 * @Description 保存（更新）数据字典明细
	 * @param cadDictDetail
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictDetailMgr/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateDictDetail(@RequestBody CadDictDetail cadDictDetail){
		log.debug(">>>> cadDictDetail from request is : {}", new Object[]{cadDictDetail});
		appSettingsService.saveOrUpdateCadDictDetail(cadDictDetail);
		return this.successView();
	}
	
	
	/**
	 * @description 删除数据字典明细
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/dictDetailMgr/delete")
	@ResponseBody
	public ModelAndView deleteDictDetails(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			appSettingsService.deleteCadDictDetail(Arrays.asList(longs));
		}
		return this.successView();	
	}
	
	
	/**
	 * @description 表重置
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/maintenance/resetTables")
	@ResponseBody
	public ModelAndView resetTables(@RequestBody Map<String, String> p){
		String[] patterns = permittedResetTablesPattern.split("\\,");
		for(String table : getString(p, "tables").split("\\,")){
			for(String pattern : patterns){
				if(table.matches(pattern)){
					appSettingsService.deleteTable(table);
				}
			}
		}
		return this.successView();
	}
	
}
