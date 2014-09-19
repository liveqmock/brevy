package com.brevy.fw.common.support.web.http;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description JSON报文请求头
 * @author caobin
 * @date 2013-7-17
 * @version 1.0
 */
public class RequestHead implements Serializable{

	private static final long serialVersionUID = 9213494648993337741L;

	private String TRAN_PROCESS;
	
	private boolean SESSION_TIMEOUT;

	/**
	 * @return the tRAN_PROCESS
	 */
	public String getTRAN_PROCESS() {
		return TRAN_PROCESS;
	}

	/**
	 * @param tRAN_PROCESS the tRAN_PROCESS to set
	 */
	public void setTRAN_PROCESS(String tRAN_PROCESS) {
		TRAN_PROCESS = tRAN_PROCESS;
	}

	/**
	 * @return the sESSION_TIMEOUT
	 */
	public boolean isSESSION_TIMEOUT() {
		return SESSION_TIMEOUT;
	}

	/**
	 * @param sESSION_TIMEOUT the sESSION_TIMEOUT to set
	 */
	public void setSESSION_TIMEOUT(boolean sESSION_TIMEOUT) {
		SESSION_TIMEOUT = sESSION_TIMEOUT;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}
