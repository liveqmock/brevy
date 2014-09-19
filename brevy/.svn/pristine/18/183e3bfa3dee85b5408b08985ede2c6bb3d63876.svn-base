package com.brevy.fw.common.util;

import org.apache.shiro.codec.Hex;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description Spring应用上下文工具类(需预先配置为bean，才可调用其静态方法)
 * @author caobin
 * @date 2013-4-17
 * @version 1.0
 */
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		context = ac;
	}

	/**
	 * @Description 获取应用上下文
	 * @return
	 * @author caobin
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * @Description 通过bean的id/name获取bean
	 * @param name bean的id或name
	 * @return
	 * @author caobin
	 */
	public static Object getBean(String name) {
		if(context == null)return null;
		return context.getBean(name);
	}

	/**
	 * @Description 通过bean的类型获取bean
	 * @param beanClass bean的类型
	 * @return
	 * @author caobin
	 */
	public static <BEAN> BEAN getBean(Class<BEAN> beanClass) {
		if(context == null)return null;
		return context.getBean(beanClass);
	}
	
	/**
	 * @Description 通过bean的id/name和类型获取bean
	 * @param name bean的id或name
	 * @param beanClass bean的类型
	 * @return
	 * @author caobin
	 */
	public static <BEAN> BEAN getBean(String name, Class<BEAN> beanClass) {
		if(context == null)return null;
		return context.getBean(name, beanClass);
	}
}
