package com.brevy.fw.common.pattern.chain.spring;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

import com.brevy.fw.common.pattern.chain.Chain;
import com.brevy.fw.common.pattern.chain.ChainExceptionListener;
import com.brevy.fw.common.pattern.chain.ChainExecutor;
import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 基于Spring的责任链执行器
 * @author caobin
 * @date 2012-12-19
 * @version 1.0
 */
public class ChainExecutorEx implements ChainExecutor, BeanFactoryAware, InitializingBean {

	@Override
	public void executeChain(Context context) throws CoreException{		
		try {
			log.trace("checking context...");
			//检查context
			if (context == null) {
				throw new IllegalArgumentException("context of chain is required.");
			}
			//获取责任链ID
			String chainId = context.getChainId();
			log.trace("get chain id: {}", chainId);
			if (StringUtils.isEmpty(chainId)) {
				throw new IllegalArgumentException("chain id of context is required.");
			}
			//从容器中获取bean
			Chain chainBean = beanFactory.getBean(chainIdPrefix.concat(context.getChainId().concat(chainIdSuffix)), Chain.class);
			log.trace("obtain chain bean [{}] from spring container.", chainBean);
			log.trace("executing action(s) of chain...");
			chainBean.execute(context);
		} catch (Throwable t) {
			CoreException ce = new CoreException(t);
			//设置到上下文异常属性中
			context.setException(ce);
			if(chainExceptionListener != null){
				if(!chainExceptionListener.onException(context, ce)){
					throw ce;
				}
			}else{
				throw ce;
			}		
		}
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BeanFactory beanFactory;
	
	/**
	 * 责任链ID前缀
	 */
	private String chainIdPrefix;
	
	/**
	 * 责任链ID后缀
	 */
	private String chainIdSuffix;
	
	
	/**
	 * 责任链异常监听器
	 */
	private ChainExceptionListener chainExceptionListener;

	/**
	 * @param chainIdPrefix the chainIdPrefix to set
	 */
	public void setChainIdPrefix(String chainIdPrefix) {
		this.chainIdPrefix = chainIdPrefix;
	}
	
	/**
	 * @param chainIdSuffix the chainIdSuffix to set
	 */
	public void setChainIdSuffix(String chainIdSuffix) {
		this.chainIdSuffix = chainIdSuffix;
	}

	/**
	 * @param chainExceptionListener the chainExceptionListener to set
	 */
	public void setChainExceptionListener(
			ChainExceptionListener chainExceptionListener) {
		this.chainExceptionListener = chainExceptionListener;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		chainIdPrefix = StringUtils.defaultString(chainIdPrefix);
		chainIdSuffix = StringUtils.defaultString(chainIdSuffix);
	}
}
