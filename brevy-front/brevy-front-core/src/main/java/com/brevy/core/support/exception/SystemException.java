package com.brevy.core.support.exception;

/**
 * @Description 系统异常类
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class SystemException extends CoreException {

	private static final long serialVersionUID = 4497521368959567474L;

	public SystemException() {
		super();
	}

	public SystemException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}

	public SystemException(MessageCode messageCode) {
		super(messageCode);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
}
