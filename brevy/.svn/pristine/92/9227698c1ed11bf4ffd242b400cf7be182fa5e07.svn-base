package com.brevy.fw.common.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @Description 标识工具类
 * @author caobin
 * @date 2013-7-5
 * @version 1.0
 */
public class IdentityUtils {

	/**
	 * @Description 获取随机UUID
	 * @param dash 是否以"-"分割
	 * @return
	 * @author caobin
	 */
	public static String getUUID(boolean dash) {
		return dash ? UUID.randomUUID().toString() : UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * @Description 使用SecureRandom随机生成Long. 
	 * @return
	 * @author caobin
	 */
	public static long randomLong() {
		return Math.abs(secureRandom.nextLong());
	}
	
	/**
	 * 生成指定位数的随机字符串
	 * @param length 长度
	 * @param pattern 字串模式
	 * @return
	 */
	public static String randomString(int length, SecureRandomPattern pattern){
		StringBuilder builder = new StringBuilder(pattern.getCode());
		StringBuilder resultBuilder = new StringBuilder();
		Random r = new Random();
		int range = builder.length();
		for(int i = 0; i < length; i++){
			resultBuilder.append(builder.charAt(r.nextInt(range)));
		}
		return resultBuilder.toString();
	}
	
	/**
	 * @Description 
	 * @author caobin
	 * @date 2013-7-5
	 * @version 1.0
	 */
	public static enum SecureRandomPattern{
		ALPHABET ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
		
		NUMERIC ("0123456789"),
		
		MIX ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		
		SecureRandomPattern(String code){
			this.code = code;
		}
		
		private final String code;

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}
	}
	
	/**
	 * 强加密随机数生成器
	 */
	private final static SecureRandom secureRandom = new SecureRandom();
}
