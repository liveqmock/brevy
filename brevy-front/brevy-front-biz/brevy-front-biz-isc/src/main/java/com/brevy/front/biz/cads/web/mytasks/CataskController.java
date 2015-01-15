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

import com.brevy.front.biz.cads.model.CadCatask;
import com.brevy.front.biz.cads.model.CadCataskAttach;

/**
 * @description 综合管理任务Controller
 * @author caobin
 * @date 2015年1月15日
 */
@Controller
@RequestMapping("/biz/cads/myTasks/catask")
public class CataskController extends AbstractMyTasksController {
	/**
	 * @description 综合管理任务附件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */	
	@RequestMapping("/fileUpload")
	public ModelAndView cataskFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.upload("CATASK", request, response);
	}
	
	/**
	 * @description 综合管理任务附件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	@RequestMapping("/fileDownload")
	public void cataskFileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long attachId = ServletRequestUtils.getLongParameter(request, "attachId");
		CadCataskAttach cadCataskAttach = myTasksService.findCataskAttachment(attachId);
        this.download(cadCataskAttach, request, response);
	}
	
	
	
	/**
	 * @description 获取技术中心综合管理任务列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getAllCataskList")
	@ResponseBody
	public Page<CadCatask> getAllCataskList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllCatasks(keyword, pageable);			
	}

	/**
	 * @description 保存（更新）综合管理任务
	 * @param cadCatask
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdate(@RequestBody CadCatask cadCatask){		
		log.info(">>> CadCatask from request is: {}", new Object[]{cadCatask});
		CadCatask savedCadCatask = myTasksService.saveOrUpdateCatask(cadCatask);
		return this.successView().addObject("id", savedCadCatask.getId());
	}
	
	
	/**
	 * @description 获取技术中心综合管理任务附件列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getCataskAttachmentList")
	@ResponseBody
	public Iterable<CadCataskAttach> getAllCataskAttachmentList(@RequestBody Map<String, String> p){
		return myTasksService.findAllCataskAttachments(getLongValue(p, "cataskId"));			
	}
	
	/**
	 * @description 综合管理任务归档
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/archive")
	@ResponseBody
	public ModelAndView cataskArchive(@RequestBody Map<String, String> p){
		myTasksService.cataskArchive(getLongValue(p, "cataskId"));
		return this.successView();
	}
}
