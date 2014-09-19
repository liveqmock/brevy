package com.brevy.core.support.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpiredFilter implements Filter {

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	private long expiredtime = -1;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String t = filterConfig.getInitParameter("expiredtime");
		if(StringUtils.isNotBlank(t)){
			expiredtime = Long.parseLong(t);
			log.debug("Setting expired time to : {}", new Object[]{expiredtime});
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 取得当前访问的URI
		String uri = ((HttpServletRequest) request).getRequestURI();
		int n = uri.lastIndexOf(".");
		if (n != (-1)) {
			HttpServletResponse resp = (HttpServletResponse) response;
			// 设置缓存
			resp.setHeader("Cache-Control", "max-age=" + expiredtime);
			resp.setHeader("Pragma","max-age=" + expiredtime); //HTTP 1.0
			// 设置过期时间
			resp.setDateHeader("Expires", System.currentTimeMillis() + expiredtime * 1000);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
