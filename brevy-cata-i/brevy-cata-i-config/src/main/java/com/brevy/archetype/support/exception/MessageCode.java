package com.brevy.archetype.support.exception;

/**
 * @Description 消息代码
 * @author caobin
 * @date 2013-7-18
 * @version 1.0
 */
public class MessageCode {
	
	private String code;
	
	private String[] args;

	public MessageCode(String code, String...args){
		this.code = code;
		this.args = args;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}
}
