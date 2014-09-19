package com.brevy.fw.common.pattern.chain.action;

import com.brevy.fw.common.pattern.chain.Action;
import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.pattern.chain.exception.ChainFailureException;

public class Step1Action implements Action {

	@Override
	public boolean execute(Context context) throws ChainExecutionException,
			ChainFailureException {
		System.out.println(">>>>> 执行 STEP1");
		System.out.println("pre-state: " + context.getState());
		context.setState("step1");
		return CONTINUE_PROCESSING;
	}

}
