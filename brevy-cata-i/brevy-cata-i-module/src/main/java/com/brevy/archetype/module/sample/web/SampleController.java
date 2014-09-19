package com.brevy.archetype.module.sample.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.archetype.module.sample.model.SampleCity;
import com.brevy.archetype.module.sample.service.SampleService;
import com.brevy.archetype.support.web.BaseController;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @Description 样例控制器
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
@Controller
@RequestMapping("/sample")
public class SampleController extends BaseController {
	
	@Autowired
	private SampleService sampleService;
	
	/**
	 * @Description 显示所有城市
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/showAllCities")
	public ModelAndView showAllCities(){
		List<SampleCity> sampleCities = sampleService.getAllCities();
		return this.successView("default").addObject("allCities", sampleCities);
	}
	
	/**
	 * @Description 分页显示城市
	 * @param params
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/showCities")
	public ModelAndView showCitiesPaged(@RequestParam Map<String, String> params){
		int start = MapUtils.getIntValue(params, PAGE_START);
		int limit = MapUtils.getIntValue(params, PAGE_SIZE);
		PageList<SampleCity> sampleCities = sampleService.getCitiesPaged(start, limit);
		return this.successView("default").addObject("cities", sampleCities);
	}
	
	/**
	 * @Description 添加新城市
	 * @param sampleCity
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/addCity")
	public ModelAndView addCity(SampleCity sampleCity){
		sampleService.addCity(sampleCity);
		return this.successView("default").addObject("success", true);
	}
	
	/**
	 * @Description 移除城市
	 * @param params
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/removeCity")
	public ModelAndView removeCity(Map<String, Object> params){
		long cityID = MapUtils.getLong(params, "id");
		sampleService.removeCity(cityID);
		return this.successView("default").addObject("success", true);
	}
	
}
