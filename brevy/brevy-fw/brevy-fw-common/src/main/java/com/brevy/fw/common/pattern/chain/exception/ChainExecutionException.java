package com.brevy.fw.common.pattern.chain.exception;

import com.brevy.fw.common.support.exception.CoreException;
import com.brevy.fw.common.support.exception.MessageCode;

/**
 * @Description 责任链执中时异常
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class ChainExecutionException extends CoreException {

	private static final long serialVersionUID = -1222886135305893159L;

	public ChainExecutionException() {
		super();
	}

	public ChainExecutionException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}

	public ChainExecutionException(MessageCode messageCode) {
		super(messageCode);
	}

	public ChainExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChainExecutionException(String message) {
		super(message);
	}

	public ChainExecutionException(Throwable cause) {
		super(cause);
	}
}
