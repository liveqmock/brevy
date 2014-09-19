package com.brevy.commons.vfs;

import com.brevy.commons.lang.LocaleUtils;

/**
 * @Description 包com.brevy.commons.vfs下的运行时异常
 * @author caobin
 * @date 2014-5-15
 * @version 1.0
 */
public class VfsRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1099336545162439662L;

	public VfsRuntimeException() {
		super();
	}

	public VfsRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code locale code
	 * @param args
	 */
	public VfsRuntimeException(String code, Object ... args) {		
		super(LocaleUtils.getMessage(code, args));
	}

	public VfsRuntimeException(Throwable cause) {
		super(cause);
	}

}
