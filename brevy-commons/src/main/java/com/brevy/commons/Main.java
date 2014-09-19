package com.brevy.commons;

import org.apache.commons.codec.StringEncoder;
import org.codehaus.plexus.util.StringUtils;

public class Main {

	/**
	 * @Description
	 * @param args
	 * @author caobin
	 */

	public static void main(String[] args) {
		String myString = "测试";
		String s = StringUtils.escape(myString);
		System.out.println();
		
	}

}
