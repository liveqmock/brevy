package com.brevy.front.biz.cads.web;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getLongValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.support.exception.BizException;
import com.brevy.core.support.web.BaseController;
import com.brevy.front.biz.cads.model.CadDemand;
import com.brevy.front.biz.cads.model.CadDemandAttach;
import com.brevy.front.biz.cads.model.CadDemandHis;
import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadGdAttach;
import com.brevy.front.biz.cads.model.CadGdHis;
import com.brevy.front.biz.cads.service.MyTasksService;

/**
 * @description 我的任务Controller
 * @author caobin
 * @date 2014年12月31日
 */
@Controller
@RequestMapping("/biz/cads/myTasks")
public class MyTasksController extends BaseController {
	
	@Autowired
	private MyTasksService myTasksService;
	
	@Value("${cads.upload.dir}")
	private String uploadDir;
	
	private static Map<String, String> fileType = new HashMap<String, String>();
	
	static {
		fileType.put("doc", "application/msword");
		fileType.put("docx", "application/msword");
		fileType.put("pdf", "application/pdf");
		fileType.put("zip", "application/zip");
		fileType.put("rar", "application/x-rar-compressed");
		
	}

	/**
	 * @description 工单附件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */	
	@RequestMapping("/gd/fileUpload")
	public ModelAndView gdFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
		MultipartFile file = mhsr.getFileMap().values().iterator().next();
		if (!file.isEmpty()) {
			long id = Long.parseLong(mhsr.getParameter("id"));
			log.debug(">>>>ID: {}", new Object[]{id});
			
			File target = new File(uploadDir.concat("GD").concat(String.valueOf(id)).concat("/").concat(file.getOriginalFilename()));
			target.getParentFile().mkdirs();
			byte[] bytes = file.getBytes();
			OutputStream os = new FileOutputStream(target);
			IOUtils.write(bytes, os);
			IOUtils.closeQuietly(os);
			
			//uploadDir
			myTasksService.addGdAttach(id, FileUtils.getExtension(file.getOriginalFilename()), target.getAbsolutePath());		
		} else {
			log.debug(">>>>>>>>>>>EMPTY FILE");
			return this.failureView(new BizException("Empty file"));
		}
		return this.successView();
	}
	
	/**
	 * @description 工单附件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	@RequestMapping("/gd/fileDownload")
	public void gdFileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long attachId = ServletRequestUtils.getLongParameter(request, "attachId");
		CadGdAttach cadGdAttach = myTasksService.findGdAttachment(attachId);

        String downLoadPath = cadGdAttach.getPath();
        File downloadFile = new File(downLoadPath);
        //设置response头
      	response.setContentType("text/html;charset=UTF-8");
      	response.setContentType(fileType.get(FileUtils.getExtension(downloadFile.getName())));
      	response.setHeader("Content-disposition", "attachment; filename=\"".concat(new String(downloadFile.getName().getBytes("utf-8"), "iso8859-1")).concat("\""));
      	OutputStream os = response.getOutputStream();
      	org.apache.commons.io.FileUtils.copyFile(downloadFile, os);
      	os.flush();
      	response.flushBuffer();
      	IOUtils.closeQuietly(os);
	}
	
	/**
	 * @description 获取技术中心工单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/gd/getGDList")
	@ResponseBody
	public Page<CadGd> getGDList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findGDsRefDept(keyword, pageable);			
	}
	
	
	/**
	 * @description 获取技术中心工单列表(Admin)
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/gd/getAllGDList")
	@ResponseBody
	public Page<CadGd> getAllGDList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllGDs(keyword, pageable);			
	}

	/**
	 * @description 保存（更新）工单
	 * @param cadGd
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/gd/saveOrUpdate")
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
	@RequestMapping("/gd/getGDAttachmentList")
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
	@RequestMapping("/gd/archive")
	@ResponseBody
	public ModelAndView gdArchive(@RequestBody Map<String, String> p){
		myTasksService.gdArchive(getLongValue(p, "gdId"));
		return this.successView();
	}

	
	/**
	 * @description 需求评估单附件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */	
	@RequestMapping("/demand/fileUpload")
	public ModelAndView demandFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
		MultipartFile file = mhsr.getFileMap().values().iterator().next();
		if (!file.isEmpty()) {
			long id = Long.parseLong(mhsr.getParameter("id"));
			log.debug(">>>>ID: {}", new Object[]{id});
			
			File target = new File(uploadDir.concat("DEMAND").concat(String.valueOf(id)).concat("/").concat(file.getOriginalFilename()));
			target.getParentFile().mkdirs();
			byte[] bytes = file.getBytes();
			OutputStream os = new FileOutputStream(target);
			IOUtils.write(bytes, os);
			IOUtils.closeQuietly(os);
			
			//uploadDir
			myTasksService.addCadDemandAttach(id, FileUtils.getExtension(file.getOriginalFilename()), target.getAbsolutePath());		
		} else {
			log.debug(">>>>>>>>>>>EMPTY FILE");
			return this.failureView(new BizException("Empty file"));
		}
		return this.successView();
	}
	
	/**
	 * @description 需求评估单附件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	@RequestMapping("/demand/fileDownload")
	public void demandFileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long attachId = ServletRequestUtils.getLongParameter(request, "attachId");
		CadDemandAttach cadDemandAttach = myTasksService.findDemandAttachment(attachId);

        String downLoadPath = cadDemandAttach.getPath();
        File downloadFile = new File(downLoadPath);
        //设置response头
      	response.setContentType("text/html;charset=UTF-8");
      	response.setContentType(fileType.get(FileUtils.getExtension(downloadFile.getName())));
      	response.setHeader("Content-disposition", "attachment; filename=\"".concat(new String(downloadFile.getName().getBytes("utf-8"), "iso8859-1")).concat("\""));
      	OutputStream os = response.getOutputStream();
      	org.apache.commons.io.FileUtils.copyFile(downloadFile, os);
      	os.flush();
      	response.flushBuffer();
      	IOUtils.closeQuietly(os);
	}
	
	/**
	 * @description 获取技术中心需求评估单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/demand/getDemandList")
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
	@RequestMapping("/demand/getAllDemandList")
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
	@RequestMapping("/demand/saveOrUpdate")
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
	@RequestMapping("/demand/getDemandAttachmentList")
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
	@RequestMapping("/demand/archive")
	@ResponseBody
	public ModelAndView demandArchive(@RequestBody Map<String, String> p){
		myTasksService.demandArchive(getLongValue(p, "demandId"));
		return this.successView();
	}
	
	
	/**
	 * @description 获取技术中心已归档工单列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/archive/getGDHisList")
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
	@RequestMapping("/archive/getDemandHisList")
	@ResponseBody
	public Page<CadDemandHis> getDemandHisList(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		return myTasksService.findAllArchivedDemands(keyword, pageable);			
	}
}
