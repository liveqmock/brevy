package com.brevy.front.biz.cads.web.mytasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.util.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.support.exception.BizException;
import com.brevy.core.support.web.BaseController;
import com.brevy.front.biz.cads.model.AbstractCadAttach;
import com.brevy.front.biz.cads.service.MyTasksService;

/**
 * @description 我的任务抽象控制器
 * @author caobin
 * @date 2015年1月15日
 */
public abstract class AbstractMyTasksController extends BaseController {

	@Autowired
	protected MyTasksService myTasksService;
	
	@Value("${cads.upload.dir}")
	protected String uploadDir;
	
	protected static Map<String, String> fileType = new HashMap<String, String>();
	
	static {
		fileType.put("doc", "application/msword");
		fileType.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		fileType.put("pdf", "application/pdf");
		fileType.put("zip", "application/zip");
		fileType.put("rar", "application/x-rar-compressed");
		fileType.put("xls", "application/vnd.ms-excel");
		fileType.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
	}
	
	/**
	 * @description 上传公用方法
	 * @param prefix 保存文件夹的前缀
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	protected ModelAndView upload(String prefix, HttpServletRequest request, HttpServletResponse response) throws Exception{	
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
		MultipartFile file = mhsr.getFileMap().values().iterator().next();
		if (!file.isEmpty()) {
			long id = Long.parseLong(mhsr.getParameter("id"));
			log.debug(">>>>ID: {}", new Object[]{id});
			
			File target = new File(uploadDir.concat(prefix).concat(String.valueOf(id)).concat("/").concat(file.getOriginalFilename()));
			target.getParentFile().mkdirs();
			byte[] bytes = file.getBytes();
			OutputStream os = new FileOutputStream(target);
			IOUtils.write(bytes, os);
			IOUtils.closeQuietly(os);
			
			String methodName = "add".concat(StringUtils.firstLetterCaps(prefix.toLowerCase())).concat("Attach");			
			ReflectionUtils.invokeMethod(
					ReflectionUtils.findMethod(MyTasksService.class, methodName, long.class, String.class, String.class),
					myTasksService, 
					id, FileUtils.getExtension(file.getOriginalFilename()), target.getAbsolutePath()
			);		
		} else {
			log.debug(">>>>>>>>>>>EMPTY FILE");
			return this.failureView(new BizException("Empty file"));
		}
		return this.successView();
	}
	
	
	/**
	 * @description  下载公用方法
	 * @param attach
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author caobin
	 */
	protected void download(AbstractCadAttach attach, HttpServletRequest request, HttpServletResponse response) throws Exception {	
        String downLoadPath = attach.getPath();
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
}
