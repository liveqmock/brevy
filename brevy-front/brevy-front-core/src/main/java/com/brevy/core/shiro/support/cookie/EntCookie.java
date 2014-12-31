package com.brevy.core.shiro.support.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 扩展SimpleCookie，支持从参数获取并覆盖sessionid
 * @author caobin
 * @date 2014年12月31日
 */
public class EntCookie extends SimpleCookie {
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	 public EntCookie(String name) {
	      super(name);
	 }

	public String readValue(HttpServletRequest request,
			HttpServletResponse ignored) {
		String shiroSid = request.getParameter(getName());
		if(shiroSid != null){
			log.info("Found post shiro sid: {}:{}", new Object[]{getName(), shiroSid});
			return shiroSid;
		}	
		return super.readValue(request, ignored);
	}

}
