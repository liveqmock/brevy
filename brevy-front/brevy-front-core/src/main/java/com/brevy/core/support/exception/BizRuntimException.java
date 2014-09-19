package com.brevy.core.support.exception;

/**
 * @Description 业务运行时异常类
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class BizRuntimException extends CoreRuntimeException {

	private static final long serialVersionUID = 5802365433556942279L;

	public BizRuntimException() {
		super();
	}

	public BizRuntimException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}

	public BizRuntimException(MessageCode messageCode) {
		super(messageCode);
	}

	public BizRuntimException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizRuntimException(String message) {
		super(message);
	}

	public BizRuntimException(Throwable cause) {
		super(cause);
	}
}
