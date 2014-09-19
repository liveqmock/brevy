package com.ips.commons.support.util;

public class SessionUtil {

	/**
	 * @Description: 为每个线程生成唯一的sessionId
	 * @return String sessionId
	 * @throws
	 */
	public static String getSessionId() {
		String sessionId = System.currentTimeMillis() + "-"
				+ (int) ((Math.random() * 10000));
		return sessionId;
	}
}
