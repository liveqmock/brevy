package com.brevy.core.shiro.support.filter;

import static com.brevy.core.shiro.ShiroConstants.AUTHORIZED_FAILED;
import static com.brevy.core.shiro.ShiroConstants.AUTHORIZED_FAILED_MESSAGE;
import static com.brevy.core.support.SupportConstants.WEB_RSP_BODY;
import static com.brevy.core.support.SupportConstants.WEB_RSP_BODY_DEFAULT_URL_KEY;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_ERROR_CODE;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_ERROR_MESSAGE;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_TRAN_SUCCESS;
import static com.brevy.core.support.SupportConstants.WEB_VIEW_RESULT_FAILURE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brevy.core.support.web.http.MIME;
import com.brevy.core.util.JsonUtils;

/**
 * @Description 自定义鉴权过滤器
 * @author caobin
 * @date 2014-5-10
 * @version 1.0
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {
	
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authz.AuthorizationFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		if(StringUtils.contains(request.getContentType(), MIME.APPLICATION_JSON.getCode())){
			HttpServletResponse res = (HttpServletResponse)response;
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
			//处理XHR请求
			res.setContentType(request.getContentType());		
			Map<String, Object> rspHead = new HashMap<String, Object>();	
			rspHead.put(WEB_RSP_HEAD_TRAN_SUCCESS, WEB_VIEW_RESULT_FAILURE);
			rspHead.put(WEB_RSP_HEAD_ERROR_CODE, AUTHORIZED_FAILED);	
			rspHead.put(WEB_RSP_HEAD_ERROR_MESSAGE, AUTHORIZED_FAILED_MESSAGE);
		
			Map<String, Object> rspBody = new HashMap<String, Object>();
			rspBody.put(WEB_RSP_BODY_DEFAULT_URL_KEY, defaultUrl);
			Map<String, Object> rsp = new HashMap<String, Object>();
			rsp.put(WEB_RSP_HEAD, rspHead);	
			rsp.put(WEB_RSP_BODY, rspBody);	
			log.trace("response: {}", new Object[]{rsp});
			PrintWriter writer = res.getWriter();
			writer.write(JsonUtils.obj2JsonString(rsp));
			return false;
		}else{
			return super.onAccessDenied(request, response);
		}
	  }
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String defaultUrl;

	/**
	 * @param defaultUrl the defaultUrl to set
	 */
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

}
