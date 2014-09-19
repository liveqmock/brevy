package com.brevy.fw.common.util;

import static com.brevy.fw.common.util.ExceptionUtils.unchecked;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * @Description JSON工具类
 * @author caobin
 * @date 2013-7-5
 * @version 1.0
 */
public class JsonUtils {
	
	/**
	 * Object转换为JSON字符串
	 * @Description
	 * @param obj 指定对象(POJO)
	 * @return
	 * @author caobin
	 */
	public static String obj2JsonString(Object obj){	
		try{
			ObjectMapper objectMapper = getObjectMapper();
			if(obj == null)return EMPTY_JSON_STRING;
			return objectMapper.writeValueAsString(obj);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		}
	}
	
	
	/**
	 * JSON字符串转换为Map
	 * @param strJsonString 需要转换的JSON字符串
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static<E, V> Map<E, V> jsonString2Map(String strJsonString){
		try {
			ObjectMapper objectMapper = getObjectMapper();
			if(StringUtils.isBlank(strJsonString))strJsonString = EMPTY_JSON_STRING;
			return objectMapper.readValue(strJsonString, Map.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		}
	}
	
	/**
	 * JSON字符串转换为Object
	 * @param strJsonString 需要转换的JSON字符串
	 * @param clazz
	 * @return
	 */
	public static<T> T jsonString2Object(String strJsonString, Class<T> clazz){
		try {
			ObjectMapper objectMapper = getObjectMapper();
			if(StringUtils.isBlank(strJsonString))strJsonString = EMPTY_JSON_STRING;
			return objectMapper.readValue(strJsonString, clazz);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw unchecked(e);
		}
	}

	/**
	 * @Description 获取ObjectMapper(单例，线程安全)
	 * @return
	 * @author caobin
	 */
	public static ObjectMapper getObjectMapper(){
		if(objectMapper == null){
			objectMapperLock.lock();
			try{
				if(objectMapper == null){
					objectMapper = new ObjectMapper();	
					objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					//所有浮点型用BigDecimal处理
					objectMapper.enable(Feature.USE_BIG_DECIMAL_FOR_FLOATS);
				}
			}catch(Exception e){
				log.error(e.getMessage(), e);
				throw unchecked(e);
			}finally{
				objectMapperLock.unlock();
			}
		}
		return objectMapper;
	}
	

	private static Log log = LogFactory.getLog(JsonUtils.class);
	
	/**
	 * 空JSON字符串
	 */
	private final static String EMPTY_JSON_STRING = "{}";
	
	/**
	 * ObjectMapper
	 */
	private static volatile ObjectMapper objectMapper;
	
	/**
	 * ObjectMapper锁
	 */
	private static Lock objectMapperLock = new ReentrantLock();
}
