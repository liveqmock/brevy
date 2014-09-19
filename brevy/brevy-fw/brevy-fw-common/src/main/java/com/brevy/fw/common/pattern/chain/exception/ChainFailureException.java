package com.brevy.fw.common.pattern.chain.exception;

import com.brevy.fw.common.support.exception.CoreException;
import com.brevy.fw.common.support.exception.MessageCode;

/**
 * @Description 责任链执行失败异常
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class ChainFailureException extends CoreException {

	private static final long serialVersionUID = -5731753853102423421L;

	public ChainFailureException() {
		super();
	}

	public ChainFailureException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}

	public ChainFailureException(MessageCode messageCode) {
		super(messageCode);
	}

	public ChainFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChainFailureException(String message) {
		super(message);
	}

	public ChainFailureException(Throwable cause) {
		super(cause);
	}
	
}
