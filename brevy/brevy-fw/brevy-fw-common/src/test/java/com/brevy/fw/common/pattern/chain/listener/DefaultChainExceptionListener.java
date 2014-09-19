package com.brevy.fw.common.pattern.chain.listener;

import com.brevy.fw.common.pattern.chain.ChainExceptionListener;
import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.support.exception.CoreException;

public class DefaultChainExceptionListener implements ChainExceptionListener {

	@Override
	public boolean onException(Context context, CoreException ce) {
		System.out.println("监听器捕获异常： " + ce.getMessage());
		System.out.println("context chain id: " + context.getChainId());
		return EXCEPTION_PROCESSED;//已处理异常，不抛出
		//return EXCEPTION_THROWN;//处理后抛出
	}

}
