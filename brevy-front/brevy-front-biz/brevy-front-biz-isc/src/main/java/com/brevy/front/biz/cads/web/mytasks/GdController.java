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

import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadGdAttach;

/**
 * @description 工单Controller
 * @author caobin
 * @date 2015年1月15日
 */
@Controller
@RequestMapping("/biz/cads/myTasks/gd")
public class GdController extends AbstractMyTasksController{

	/**
	 * @description 工单附件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */	
	@RequestMapping("/fileUpload")
	public ModelAndView gdFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.upload("GD", request, response);
	}
	
	/**
	 * @description 工单附件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	@RequestMapping("/fileDownload")
	public void gdFileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long attachId = ServletRequestUtils.getLongParameter(request, "attachId");
		CadGdAttach cadGdAttach = myTasksService.findGdAttachment(attachId);
		this.download(cadGdAttach, request, response);
	}
	
	/**
	 * @description 获取技术中心工单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getGDList")
	@ResponseBody
	public Page<CadGd> getGDList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		//获取监控情况参数
		String monitor = getString(p, "monitor", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findGDsRefDept(keyword, monitor, pageable);			
	}
	
	


	/**
	 * @description 保存（更新）工单
	 * @param cadGd
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdate(@RequestBody CadGd cadGd){		
		log.info(">>> CadGd from request is: {}", new Object[]{cadGd});
		CadGd savedCadGd = myTasksService.saveOrUpdateCadGd(cadGd);
		return this.successView().addObject("id", savedCadGd.getId());
	}
	
	
	/**
	 * @description 获取技术中心工单附件列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getGDAttachmentList")
	@ResponseBody
	public Iterable<CadGdAttach> getAllGDAttachmentList(@RequestBody Map<String, String> p){
		return myTasksService.findAllGDAttachments(getLongValue(p, "gdId"));			
	}
	
	/**
	 * @description 工单归档
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/archive")
	@ResponseBody
	public ModelAndView gdArchive(@RequestBody Map<String, String> p){
		myTasksService.gdArchive(getLongValue(p, "gdId"));
		return this.successView();
	}
}
