package com.brevy.fw.common.pattern.chain.impl;

import java.util.HashMap;
import java.util.Map;

import com.brevy.fw.common.pattern.chain.Context;
import com.brevy.fw.common.support.exception.CoreException;

/**
 * @Description 基础Context实现
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ContextBase extends HashMap<Object, Object> implements Context {

	private static final long serialVersionUID = -6662521421422812136L;

	public ContextBase() {
		super();
	}
	
	@Override
	public String getChainId() {
		return chaidId;
	}

	@Override
	public void setChainId(String chainId) {
		this.chaidId = chainId;
	}

	@Override
	public <K, V> void setData(K key, V value) {
		put(key, value);
	}

	@Override
	public <K, V> V getData(K key) {
		return (V)get(key);
	}
	
	@Override
	public <K, V> V removeData(K key) {
		return (V)remove(key);
	}
	
	@Override
	public <K, V> Map<K, V> getDataMap() {
		return Map.class.cast(this);
	}

	@Override
	public <K, V> void setDataMap(Map<K, V> dataMap) {
		if(dataMap != null)putAll(dataMap);
	}

	@Override
	public <R> void setResult(R result) {
		this.result = result;
	}

	@Override
	public <R> R getResult() {
		return (R)result;
	}
	
	@Override
	public <K, V> void setConfiguration(K cfgKey, V cfgVal) {
		confMap.put(cfgKey, cfgVal);
	}

	@Override
	public <K, V> V getConfiguration(K cfgKey) {
		return (V)confMap.get(cfgKey);
	}
	
	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getState() {
		return state;
	}
	
	@Override
	public void setException(CoreException ce) {
		this.exception = ce;
	}

	@Override
	public CoreException getException() {
		return exception;
	}

	/**
	 * 责任链唯一标识符
	 */
	private String chaidId;
	
	/**
	 * 结果
	 */
	private Object result;

	/**
	 * 配置信息
	 */
	private Map<Object, Object> confMap = new HashMap<Object, Object>();

	/**
	 * （业务）状态
	 */
	private String state;

	/**
	 * 异常信息
	 */
	private CoreException exception;
}
