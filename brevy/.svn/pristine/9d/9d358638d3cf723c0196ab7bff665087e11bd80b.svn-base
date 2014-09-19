package com.brevy.fw.common.support.exception;

/**
 * @Description 核心运行时异常类
 * @author caobin
 * @date 2013-7-18
 * @version 1.0
 */
public class CoreRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 8969283409843225946L;
	
	/**
	 * 错误代码
	 */
	private MessageCode messageCode;
	
	public CoreRuntimeException() {
		super();
	}

	public CoreRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoreRuntimeException(String message) {
		super(message);
	}

	public CoreRuntimeException(Throwable cause) {
		super(cause);
	}
	
	public CoreRuntimeException(MessageCode messageCode){
		this();
		this.messageCode = messageCode;
	}
	
	public CoreRuntimeException(MessageCode messageCode,  Throwable cause){
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
