package com.ips.commons.support.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @Description 生成唯一ID算法
 * @author caobin
 * @date 2012-11-15
 * @version 1.0
 */
public class Identities {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割
	 * (128Bits)
	 */
	public static String create32LenUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割
	 */
	public static String create36LenUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	/**
	 * 生成指定位数的随机字符串
	 * @param length
	 * @param isNumber
	 * @return
	 */
	public static String randomString(int length, boolean isNumber){
		StringBuilder builder = new StringBuilder(isNumber ? RANDOM_NUMBER_RANGE : RANDOM_STRING_RANGE);
		StringBuilder resultBuilder = new StringBuilder();
		Random r = new Random();
		int range = builder.length();
		for(int i = 0; i < length; i++){
			resultBuilder.append(builder.charAt(r.nextInt(range)));
		}
		return resultBuilder.toString();
	}
	
	private final static String RANDOM_STRING_RANGE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private final static String RANDOM_NUMBER_RANGE = "0123456789";

}
