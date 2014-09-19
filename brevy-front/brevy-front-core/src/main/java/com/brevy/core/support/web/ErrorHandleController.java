package com.brevy.core.support.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brevy.core.support.web.http.MIME;

/**
 * @Description 异常处理控制器
 * @author caobin
 * @date 2014-5-10
 * @version 1.0
 */
@Controller
@RequestMapping("/errors")
public class ErrorHandleController extends BaseController {

	/**
	 * @Description 404 - 未找到页面
	 * @param request
	 * @param response
	 * @return
	 * @author caobin
	 * @throws IOException 
	 */
	@RequestMapping("/404")
	public String to404(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(StringUtils.contains(request.getContentType(), MIME.APPLICATION_JSON.getCode())){
			HttpServletResponse res = (HttpServletResponse)response;
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			
			//处理XHR请求
			res.setContentType(request.getContentType());		
			PrintWriter writer = res.getWriter();
			writer.write("页面未找到");
			return null;
		}
		return "errors/404";
	}
	
	/**
	 * @Description 403 - 拒绝访问
	 * @param request
	 * @param response
	 * @return
	 * @author caobin
	 * @throws IOException 
	 */
	@RequestMapping("/403")
	public String to403(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(StringUtils.contains(request.getContentType(), MIME.APPLICATION_JSON.getCode())){
			HttpServletResponse res = (HttpServletResponse)response;
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
			//处理XHR请求
			res.setContentType(request.getContentType());		
			PrintWriter writer = res.getWriter();
			writer.write("拒绝访问");
			return null;
		}
		return "errors/403";
	}
	
	/**
	 * @Description 500 - 服务器内部错误
	 * @param request
	 * @param response
	 * @return
	 * @author caobin
	 * @throws IOException 
	 */
	@RequestMapping("/500")
	public String to500(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(StringUtils.contains(request.getContentType(), MIME.APPLICATION_JSON.getCode())){
			HttpServletResponse res = (HttpServletResponse)response;
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			//处理XHR请求
			res.setContentType(request.getContentType());		
			PrintWriter writer = res.getWriter();
			writer.write("服务器内部错误");
			return null;
		}
		return "errors/500";
	}

}
