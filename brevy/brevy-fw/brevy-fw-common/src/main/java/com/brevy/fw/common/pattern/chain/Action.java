package com.brevy.fw.common.pattern.chain;

import com.brevy.fw.common.pattern.chain.exception.ChainExecutionException;
import com.brevy.fw.common.pattern.chain.exception.ChainFailureException;




public interface Action {

    /**
     * 责任链继续委派后续Action处理
     */
    public static final boolean CONTINUE_PROCESSING = false;

    /**
     * 处理完成，责任链终止
     */
    public static final boolean PROCESSING_COMPLETE = true;
   
    /**
     * @Description 通过上下文执行责任链
     * @param context 上下文
     * @return 
     * @throws ChainExecutionException
     * @throws ChainFailureException
     * @author caobin
     */
    boolean execute(Context context) throws ChainExecutionException, ChainFailureException;

}
