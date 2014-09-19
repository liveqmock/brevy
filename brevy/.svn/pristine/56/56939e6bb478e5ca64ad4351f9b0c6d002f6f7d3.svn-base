package com.brevy.fw.common.pattern.chain.impl;


import java.util.Collection;

import com.brevy.fw.common.pattern.chain.Action;
import com.brevy.fw.common.pattern.chain.Chain;
import com.brevy.fw.common.pattern.chain.ChainIntercepter;
import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.pattern.chain.exception.ChainFailureException;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 基础Chain实现
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class ChainBase implements Chain {

    public ChainBase() {

    }

    public ChainBase(Action action) {
        addAction(action);
    }

    public ChainBase(Action[] actions) {
        if (actions == null) {
            throw new IllegalArgumentException();
        }
        for(Action action : actions){
        	addAction(action);
        }
    }

    public ChainBase(Collection<Action> actions) {
        if (actions == null) {
            throw new IllegalArgumentException();
        }
        for(Action action : actions){
        	addAction(action);
        }
    }

   

    public void addAction(Action action) {
        if (action == null) {
            throw new IllegalArgumentException();
        }      
        Action[] results = new Action[actions.length + 1];
        System.arraycopy(actions, 0, results, 0, actions.length);
        results[actions.length] = action;
        actions = results;
    }

    public boolean execute(Context context) throws ChainExecutionException, ChainFailureException{
        if (context == null) {
            throw new IllegalArgumentException();
        }
        
        boolean saveResult = false;
        ChainIntercepter ci = null;
        
        for(Action action : actions){
        	if(saveResult)break;
        	if(action instanceof ChainIntercepter){      		
        		try{
        			ci = (ChainIntercepter)action;
        			if(ci.preprocess(context))continue;
        			saveResult = action.execute(context);
        			if(ci.postprocess(context))continue;
            	}catch(Throwable t){
            		if(ci == null){
            			throw new ChainFailureException(t);
            		}else{
            			if(!ci.handleException(context, new CoreException(t))){
            				throw new ChainFailureException(t);
            			}
            		}
            	}
    		}else{
    			saveResult = action.execute(context);
    		}
        }
        
        return saveResult;
    }

    protected Action[] getActions() {
        return (actions);
    }
    
    protected Action[] actions = new Action[0];
}
