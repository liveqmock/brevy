package com.brevy.fw.common.pattern.chain.action;

import com.brevy.fw.common.pattern.chain.Action;
import com.brevy.fw.common.pattern.chain.ChainIntercepter;
import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.pattern.chain.exception.ChainFailureException;
import com.brevy.fw.common.support.exception.CoreException;

public class Step2Action implements Action, ChainIntercepter {

	@Override
	public boolean execute(Context context) throws ChainExecutionException,
			ChainFailureException {
		System.out.println(">>>>> 执行 STEP2");
		System.out.println("pre-state: " + context.getState());
		int i = 5/0;//模拟异常
		context.setState("step2");	
		return CONTINUE_PROCESSING;
	}

	@Override
	public boolean preprocess(Context context) throws ChainExecutionException {
		System.out.println("========== 前拦截STEP2ACTION");
		return CONTINUE_PROCESSING;
	}

	@Override
	public boolean postprocess(Context context) throws ChainExecutionException {
		System.out.println("========== 后拦截STEP2ACTION");
		return CONTINUE_PROCESSING;
	}

	@Override
	public boolean handleException(Context context, CoreException ce) {
		System.out.println("========== 捕获异常： " + ce.getMessage());
		//return EXCEPTION_PROCESSED;//已处理异常，不抛出
		return EXCEPTION_THROWN;
	}

}
