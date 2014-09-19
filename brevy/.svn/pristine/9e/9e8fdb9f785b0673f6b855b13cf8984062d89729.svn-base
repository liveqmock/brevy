package com.brevy.fw.common.core.security.shiro.filter;

import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_AUTHENTICATE_FAILED;
import static com.brevy.fw.common.core.security.shiro.ShiroConstants.E_AUTHENTICATE_FAILED_MESSAGE;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_BODY;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_BODY_LOGIN_URL_KEY;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_HEAD;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_HEAD_ERROR_CODE;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_HEAD_ERROR_MESSAGE;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_HEAD_TRAN_SUCCESS;
import static com.brevy.fw.common.support.SupportConstants.WEB_VIEW_RESULT_FAILURE;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brevy.fw.common.support.web.http.MIME;
import com.brevy.fw.common.util.JsonUtils;


public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		if(StringUtils.contains(request.getContentType(), MIME.APPLICATION_JSON.getCode())){
			//处理XHR请求
			response.setContentType(request.getContentType());		
			Map<String, Object> rspHead = new HashMap<String, Object>();	
			rspHead.put(WEB_RSP_HEAD_TRAN_SUCCESS, WEB_VIEW_RESULT_FAILURE);
			rspHead.put(WEB_RSP_HEAD_ERROR_CODE, E_AUTHENTICATE_FAILED);	
			rspHead.put(WEB_RSP_HEAD_ERROR_MESSAGE, E_AUTHENTICATE_FAILED_MESSAGE);	
			Map<String, Object> rspBody = new HashMap<String, Object>();
			rspBody.put(WEB_RSP_BODY_LOGIN_URL_KEY, this.getLoginUrl());
			Map<String, Object> rsp = new HashMap<String, Object>();
			rsp.put(WEB_RSP_HEAD, rspHead);	
			rsp.put(WEB_RSP_BODY, rspBody);	
			log.trace("response: {}", new Object[]{rsp});
			PrintWriter writer = response.getWriter();
			writer.write(JsonUtils.obj2JsonString(rsp));
			return false;
		}else{
			return super.onAccessDenied(request, response);
		}	
	}	
}
