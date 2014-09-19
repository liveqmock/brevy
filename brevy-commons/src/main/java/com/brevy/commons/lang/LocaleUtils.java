package com.brevy.commons.lang;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description 区域工具
 * @author caobin
 * @date 2014-5-21
 * @version 1.0
 */
public class LocaleUtils {

	/**
	 * 当前区域BUNDLE设置
	 */
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("_commons/message/message", Locale.getDefault());
	
	
	/**
	 * @Description 获取资源消息
	 * @param key 资源KEY
	 * @param args 占位符参数
	 * @return
	 * @author caobin
	 */
	public static String getMessage(String key, Object ... args){
		return MessageFormat.format(BUNDLE.getString(key), args);
	}

}
