package com.brevy.fw.common.pattern.chain;

import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 责任链执行器
 * @author caobin
 * @date 2012-12-19
 * @version 1.0
 */
public interface ChainExecutor {
	
	/**
	 * @Description 执行链
	 * @param context
	 * @throws CoreException
	 * @author caobin
	 */
	void executeChain(Context context) throws CoreException;
}
