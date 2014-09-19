package com.brevy.fw.common.pattern.chain;

import java.util.Map;

import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 责任链处理的上下文
 * @author caobin
 * @date 2012-12-18
 * @version 1.0
 */
public interface Context {
	
	/**
	 * @Description 获取责任链ID
	 * @return
	 * @author caobin
	 */
	String getChainId();
	
	/**
	 * @Description 设置责任链ID
	 * @param chainId 责任链ID
	 * @author caobin
	 */
	void setChainId(String chainId);

	/**
	 * @Description 设置数据
	 * @param key
	 * @param value
	 * @author caobin
	 */
	<K,V> void setData(K key, V value);
	
	/**
	 * @Description 通过键值获取数据
	 * @param key 键值
	 * @return
	 * @author caobin
	 */
	<K,V> V getData(K key);
	
	/**
	 * @Description 通过键值移除数据
	 * @param key 键值
	 * @return
	 * @author caobin
	 */
	<K,V> V removeData(K key);
	
	/**
	 * @Description 获取数据集
	 * @return
	 * @author caobin
	 */
	<K,V> Map<K,V> getDataMap();
	
	/**
	 * @Description 设置数据集
	 * @param dataMap 数据集
	 * @author caobin
	 */
	<K,V> void setDataMap(Map<K,V> dataMap);
	
	/**
	 * @Description 设置结果
	 * @param result 结果
	 * @author caobin
	 */
	<R> void setResult(R result);
	
	/**
	 * @Description 获取结果
	 * @return
	 * @author caobin
	 */
	<R> R getResult();
	
	/**
	 * @Description 设置配置项
	 * @param cfgKey
	 * @param cfgVal
	 * @author caobin
	 */
	<K,V> void setConfiguration(K cfgKey, V cfgVal);
	
	/**
	 * @Description 获取配置项
	 * @param cfgKey 配置Key
	 * @return
	 * @author caobin
	 */
	<K,V> V getConfiguration(K cfgKey);
	
	
	/**
	 * @Description 设置（业务）状态
	 * @param state （业务）状态
	 * @author caobin
	 */
	void setState(String state);
	
	/**
	 * @Description 获取（业务）状态
	 * @return
	 * @author caobin
	 */
	String getState();
	
	
	/**
	 * @Description 设置异常
	 * @param ce
	 * @author caobin
	 */
	void setException(CoreException ce);
	
	/**
	 * @Description 获取异常
	 * @return
	 * @author caobin
	 */
	CoreException getException();
}
