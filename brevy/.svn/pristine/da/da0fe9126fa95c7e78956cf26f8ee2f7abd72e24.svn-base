package com.brevy.fw.common.pattern.chain;

import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 责任链异常监听器
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public interface ChainExceptionListener {

	
    /**
     * 异常已处理，不作抛出处理
     */
    public static final boolean EXCEPTION_PROCESSED = true;

    /**
     * 仍旧异常抛出
     */
    public static final boolean EXCEPTION_THROWN = false;
	
	/**
	 * @Description 责任链发生异常时触发
	 * @param context 上下文
	 * @param ce
	 * @return true-异常已处理，不再抛出/false-继续将此抛出异常
	 * @author caobin
	 */
	boolean onException(Context context, CoreException ce);
}
