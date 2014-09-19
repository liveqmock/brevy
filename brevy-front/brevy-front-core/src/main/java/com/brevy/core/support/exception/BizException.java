package com.brevy.core.support.exception;

/**
 * @Description 业务异常类
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class BizException extends CoreException {

	private static final long serialVersionUID = -4034812150386817059L;

	public BizException() {
		super();
	}

	public BizException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}

	public BizException(MessageCode messageCode) {
		super(messageCode);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
}
