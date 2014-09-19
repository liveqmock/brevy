package com.brevy.fw.common.pattern.chain;

import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 责任链拦截器
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public interface ChainIntercepter {
	
	/**
     * 继续链的执行
     */
    public static final boolean ACTION_CONTINUED = false;

    /**
     * 处理完成，责任链终止
     */
    public static final boolean ACTION_INTERRUPTED = true;
    
    /**
     * 异常已处理，不作抛出处理
     */
    public static final boolean EXCEPTION_PROCESSED = true;

    /**
     * 仍旧异常抛出
     */
    public static final boolean EXCEPTION_THROWN = false;
    
	/**
	 * @Description 预处理
	 * @param context 上下文
	 * @return true-终止此单元中后续操作的执行/false-继续此单元中后续操作的执行
	 * @throws ChainExecutionException
	 * @author caobin
	 */
	boolean preprocess(Context context) throws ChainExecutionException;
	
	/**
	 * @Description 后处理
	 * @param context 上下文
	 * @return true-终止此单元中后续操作的执行/false-继续此单元中后续操作的执行
	 * @throws ChainExecutionException
	 * @author caobin
	 */
	boolean postprocess(Context context) throws ChainExecutionException;
	
	
	/**
	 * @Description 单元执行中抛错
	 * @param context 上下文
	 * @param ce 异常
	 * @return true-已处理异常，不再抛出/false-继续将此异常抛出
	 * @author caobin
	 */
	boolean handleException(Context context, CoreException ce);
}
