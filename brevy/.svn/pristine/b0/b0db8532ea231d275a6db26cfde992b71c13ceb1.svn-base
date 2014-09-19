package com.brevy.fw.common.pattern.chain.spring.template;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.brevy.fw.common.pattern.chain.ChainExecutor;
import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.pattern.chain.exception.ChainFailureException;
import com.brevy.fw.common.pattern.chain.impl.ContextBase;
import com.brevy.fw.common.pattern.template.Template;
import com.brevy.fw.common.pattern.template.callback.Callback;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 责任链模板方法
 * @author caobin
 * @date 2013-7-24
 * @version 1.0
 */
public class ChainTemplate implements Template<Object>, InitializingBean{

	@Override
	public Object doExecute(Callback callback, Object... params)
			throws CoreException {
		ChainCallback ccb = (ChainCallback)callback;
		ccb.doInChain(chainExecutor, params);
		return null;
	}

	
	/**
	 * @Description 执行action流程
	 * @param chainBeanName 责任链bean的id/name(设置到Context的chainId中)
	 * @param initState 初始（业务）状态(设置到Context的state中)
	 * @param dataMap 数据集(合并到Context的dataMap中)
	 * @author caobin
	 * @throws CoreException 
	 */
	public void executeActionFlow(String chainBeanName, String initState, Map<String, Object> dataMap) throws CoreException{
		log.debug("starting to execute action flow with chain id : {}", chainBeanName);
		this.doExecute(new ChainCallback() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void doInChain(ChainExecutor chainExecutor, Object... params)
					throws ChainExecutionException, ChainFailureException, CoreException {
				Context ctx = new ContextBase();
				ctx.setChainId((String)params[0]);
				ctx.setState((String)params[1]);
				ctx.setDataMap((Map)params[2]);
				chainExecutor.executeChain(ctx);
			}
		}, chainBeanName, initState, dataMap);
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ChainExecutor chainExecutor;

	/**
	 * @param chainExecutor the chainExecutor to set
	 */
	public void setChainExecutor(ChainExecutor chainExecutor) {
		this.chainExecutor = chainExecutor;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(chainExecutor, "chainExecutor is required.");
	}

	
}
