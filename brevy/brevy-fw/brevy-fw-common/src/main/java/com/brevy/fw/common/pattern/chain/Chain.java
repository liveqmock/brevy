package com.brevy.fw.common.pattern.chain;


/**
 * @Description 操作链
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public interface Chain extends Action {
	
    /**
     * @Description 添加操作单元
     * @param action
     * @author caobin
     */
    void addAction(Action action);
}
