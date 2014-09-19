package com.brevy.spring.wf.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletUtils {
	/**
	 * @Description
	 * @param req
	 * @param beanName
	 * @param beanClazz
	 * @return
	 * @author caobin
	 */
	public static<T> T getBean(HttpServletRequest req, String beanName, Class<T> beanClazz) {
		ApplicationContext appctx = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		return appctx.getBean(beanName, beanClazz);
	}
}
