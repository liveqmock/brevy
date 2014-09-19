package com.brevy.archetype.support.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * @Description 日志增强处理过滤器
 * @author caobin
 * @date 2014-8-19
 * @version 1.0
 */
public class LogProcessFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//用于拼接追踪号
		StringBuilder traceNoBuilder = new StringBuilder();
		//TODO：待有登录用户后取代此行代码
		Object user = null;
		
		
		traceNoBuilder
			.append(DateTime.now().toString(PATTERN))
			.append("-")
			.append(Thread.currentThread().getId());
		
		//匿名用户
		if (user == null) {
			log.trace("cannot get user authentication.");
			traceNoBuilder.append("-").append(ANON);
		} 		
		log.trace("trace No.: {}", new Object[]{traceNoBuilder.toString()});
		
		//扩展日志追踪号
		MDC.put(TRACE_NO, traceNoBuilder.toString());
		try {
			filterChain.doFilter(request, response);
		} finally {
			MDC.clear();
		}

	}
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 匿名用户(未登录)
	 */
	private static final String ANON = "anon";
	
	private static final String PATTERN = "yyyyMMddHHmmssSSS";
	
	/**
	 * 日志追踪号
	 */
	private static final String TRACE_NO = "traceNo";
}
