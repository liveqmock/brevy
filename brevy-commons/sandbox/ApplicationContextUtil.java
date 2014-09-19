/**
 * @Title:ApplicationContextUtil.java 
 * @Package:com.ips.pbcs.util
 * @Description:TODO(用一句话描述该文件做什么)
 * @author:shellshen
 * @date:2012-8-13
 * @version	V1.0
 */
package com.ips.commons.support.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @className:ApplicationContextUtil
 * @author shellshen
 * @date:2012-11-15上午9:02:00
 * @version 1.0
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ApplicationContextUtil.context = arg0;

	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static Object getBean(String id) {
		return context.getBean(id);
	}

	public static <T> T getBean(Class<T> c) {
		return context.getBean(c);
	}
}
