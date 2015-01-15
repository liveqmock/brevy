package com.brevy.front.biz.cads.web.mytasks;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brevy.front.biz.cads.model.CadCataskHis;
import com.brevy.front.biz.cads.model.CadDemandHis;
import com.brevy.front.biz.cads.model.CadGdHis;

/**
 * @description 归档Controller
 * @author caobin
 * @date 2015年1月15日
 */
@Controller
@RequestMapping("/biz/cads/myTasks/archive")
public class ArchivedController extends AbstractMyTasksController {

	/**
	 * @description 获取技术中心已归档工单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getGDHisList")
	@ResponseBody
	public Page<CadGdHis> getGDHisList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllArchivedGDs(keyword, pageable);			
	}
	
	/**
	 * @description 获取技术中心已归档需求评估单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getDemandHisList")
	@ResponseBody
	public Page<CadDemandHis> getDemandHisList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllArchivedDemands(keyword, pageable);			
	}
	
	/**
	 * @description 获取技术中心已归档综合管理任务列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getCataskHisList")
	@ResponseBody
	public Page<CadCataskHis> getCataskHisList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllArchivedCatasks(keyword, pageable);			
	}
}
