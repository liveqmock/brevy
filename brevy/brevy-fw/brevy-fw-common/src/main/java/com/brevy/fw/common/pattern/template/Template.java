package com.brevy.fw.common.pattern.template;

import com.brevy.fw.common.pattern.template.callback.Callback;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 模板接口
 * @author caobin
 * @date 2013-7-24
 * @version 1.0
 */
public interface Template<R> {
	
	/**
	 * @Description 模板执行方法
	 * @param callback 回调
	 * @param params 附加参数
	 * @return
	 * @throws CoreException
	 * @author caobin
	 */
	R doExecute(Callback callback, Object... params) throws CoreException;
}
