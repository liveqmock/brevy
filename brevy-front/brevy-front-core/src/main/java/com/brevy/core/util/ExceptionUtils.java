package com.brevy.core.util;


/**
 * @Description 异常工具类
 * @author caobin
 * @date 2013-7-5
 * @version 1.0
 */
public class ExceptionUtils {

	/**
	 * @Description 将检查异常转换为运行时异常
	 * @param ex 异常
	 * @return
	 * @author caobin
	 */
	public static RuntimeException unchecked(Exception ex) {
		return (ex instanceof RuntimeException) ? (RuntimeException) ex :  new RuntimeException(ex);
	}

	
	/**
	 * @Description 将异常栈追踪信息转化为String
	 * @param t 异常
	 * @return
	 * @author caobin
	 */
	public static String getStackTraceAsString(Throwable t) {
		return org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(t);
	}
	
	
	/**
	 * @Description 获取底层异常
	 * @param t 当前异常
	 * @return
	 * @author caobin
	 */
	public static Throwable getRootCause(Throwable t){
		return org.apache.commons.lang.exception.ExceptionUtils.getRootCause(t);
	}


	/**
	 * @Description 判断异常是否由某些底层的异常引起
	 * @param ex 异常
	 * @param causeExceptionClasses 可能的底层异常类
	 * @return
	 * @author caobin
	 */
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}
}
