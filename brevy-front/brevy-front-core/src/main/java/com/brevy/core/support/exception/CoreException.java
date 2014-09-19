package com.brevy.core.support.exception;

/**
 * @Description 核心异常类
 * @author caobin
 * @date 2013-7-18
 * @version 1.0
 */
public class CoreException extends Exception {

	private static final long serialVersionUID = -5376368834396539290L;
	
	/**
	 * 错误代码
	 */
	private MessageCode messageCode;

	public CoreException() {
		super();
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}


	public CoreException(String message) {
		super(message);
	}

	public CoreException(Throwable cause) {
		super(cause);
	}
	
	public CoreException(MessageCode messageCode){
		this();
		this.messageCode = messageCode;
	}
	
	public CoreException(MessageCode messageCode,  Throwable cause){
		this(cause);
		this.messageCode = messageCode;		
	}

	/**
	 * @return the code
	 */
	public MessageCode getMessageCode() {
		return messageCode;
	}
}
