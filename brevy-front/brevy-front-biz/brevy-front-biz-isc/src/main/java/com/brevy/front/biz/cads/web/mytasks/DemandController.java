package com.brevy.front.biz.cads.web.mytasks;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getLongValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.front.biz.cads.model.CadDemand;
import com.brevy.front.biz.cads.model.CadDemandAttach;

/**
 * @description 需求评估单Controller
 * @author caobin
 * @date 2015年1月15日
 */
@Controller
@RequestMapping("/biz/cads/myTasks/demand")
public class DemandController extends AbstractMyTasksController {

	/**
	 * @description 需求评估单附件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */	
	@RequestMapping("/fileUpload")
	public ModelAndView demandFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.upload("DEMAND", request, response);
	}
	
	/**
	 * @description 需求评估单附件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	@RequestMapping("/fileDownload")
	public void demandFileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long attachId = ServletRequestUtils.getLongParameter(request, "attachId");
		CadDemandAttach cadDemandAttach = myTasksService.findDemandAttachment(attachId);
        this.download(cadDemandAttach, request, response);
	}
	
	/**
	 * @description 获取技术中心需求评估单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getDemandList")
	@ResponseBody
	public Page<CadDemand> getDemandList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findDemandsRefDept(keyword, pageable);			
	}
	
	
	/**
	 * @description 获取技术中心需求评估单列表(Admin)
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getAllDemandList")
	@ResponseBody
	public Page<CadDemand> getAllDemandList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllDemands(keyword, pageable);			
	}

	/**
	 * @description 保存（更新）需求评估单
	 * @param cadDemand
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdate(@RequestBody CadDemand cadDemand){		
		log.info(">>> CadDemand from request is: {}", new Object[]{cadDemand});
		CadDemand savedCadDemand = myTasksService.saveOrUpdateCadDemand(cadDemand);
		return this.successView().addObject("id", savedCadDemand.getId());
	}
	
	
	/**
	 * @description 获取技术中心需求评估单附件列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getDemandAttachmentList")
	@ResponseBody
	public Iterable<CadDemandAttach> getAllDemandAttachmentList(@RequestBody Map<String, String> p){
		return myTasksService.findAllDemandAttachments(getLongValue(p, "demandId"));			
	}
	
	/**
	 * @description 需求评估单归档
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/archive")
	@ResponseBody
	public ModelAndView demandArchive(@RequestBody Map<String, String> p){
		myTasksService.demandArchive(getLongValue(p, "demandId"));
		return this.successView();
	}
}
