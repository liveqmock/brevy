package com.brevy.front.biz.cads.web;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.support.exception.BizException;
import com.brevy.core.support.web.BaseController;
import com.brevy.front.biz.cads.model.CadGd;
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
			
			File target = new File(uploadDir.concat(String.valueOf(id)).concat("/").concat(file.getOriginalFilename()));
			target.getParentFile().mkdirs();
			byte[] bytes = file.getBytes();
			OutputStream os = new FileOutputStream(target);
			IOUtils.write(bytes, os);
			IOUtils.closeQuietly(os);
			
			//uploadDir
			myTasksService.addAttach(id, FileUtils.getExtension(file.getOriginalFilename()), target.getAbsolutePath());		
		} else {
			log.debug(">>>>>>>>>>>EMPTY FILE");
			return this.failureView(new BizException("Empty file"));
		}
		return this.successView();
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
}
