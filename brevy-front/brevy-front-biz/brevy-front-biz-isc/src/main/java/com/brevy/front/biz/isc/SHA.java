package com.brevy.front.biz.isc;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA {

	public static void main(String[] args) {
		
		System.out.println(DigestUtils.sha1("123456").length);
		System.out.println(DigestUtils.sha256("123456").length);
		System.out.println(0xff);

	}

}
