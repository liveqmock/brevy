package com.brevy.fw.common.pattern.chain.spring.template;

import com.brevy.fw.common.pattern.chain.ChainExecutor;
import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.pattern.chain.exception.ChainFailureException;
import com.brevy.fw.common.pattern.template.callback.Callback;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 
 * @author caobin
 * @date 2013-7-24
 * @version 1.0
 */
public interface ChainCallback extends Callback {

	/**
	 * @Description 回调执行方法
	 * @param chainExecutor 责任链执行器
	 * @param params 参数
	 * @throws ChainExecutionException
	 * @throws ChainFailureException
	 * @throws CoreException
	 * @author caobin
	 */
	void doInChain(ChainExecutor chainExecutor, Object... params) throws ChainExecutionException, ChainFailureException, CoreException;
}
