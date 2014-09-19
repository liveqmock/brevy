package com.brevy.fw.common.pattern.chain.spring;

import java.util.Collection;

import com.brevy.fw.common.pattern.chain.Action;
import com.brevy.fw.common.pattern.chain.impl.ChainBase;

/**
 * @Description 
 * @author caobin
 * @date 2012-12-19
 * @version 1.0
 */
public class ChainEx extends ChainBase {

	public ChainEx() {
		super();
	}
	
	public ChainEx(Action action) {
		super(action);
	}
	
	public ChainEx(Action[] actions) {
		super(actions);
	}

	public ChainEx(Collection<Action> actions) {
		super(actions);
	}


	/**
	 * @Description 提供spring做set注入
	 * @param action
	 * @author caobin
	 */
	public void setAction(Action action) {
		addAction(action);
	}

	/**
	 * @Description 提供spring做set注入
	 * @param actions
	 * @author caobin
	 */
	public void setActions(Action[] actions) {
		if (actions == null) {
			throw new IllegalArgumentException();
		}
		for(Action action : actions){
			addAction(action);
		}
	}

}
