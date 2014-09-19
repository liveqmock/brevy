package com.ips.commons.support.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.ips.bmps.module.blend.bankupload.model.TcorInfAccsupplier;
import com.ips.bmps.support.exception.SysRuntimeException;


/**
 * JSON处理工具类
 * @author caobin
 *
 */
public class JsonUtils {

	private static Log log = LogFactory.getLog(JsonUtils.class);
	
	/**
	 * Map转换为JSON字符串
	 * @param map 需要转换的Map
	 * @return
	 */
	public static<E, V> String map2JsonString(Map<E, V> map){	
		try{
			ObjectMapper objectMapper = instanceObjectMapper();
			if(map == null || map.isEmpty())return EMPTY_JSON_STRING;
			return objectMapper.writeValueAsString(map);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * List转换为JSON字符串
	 * @Description
	 * @param list
	 * @return
	 * @author caobin
	 */
	public static<E> String list2JsonString(List<E> list){	
		try{
			ObjectMapper objectMapper = instanceObjectMapper();
			if(list == null || list.isEmpty())return EMPTY_JSON_STRING;
			return objectMapper.writeValueAsString(list);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * Object转换为JSON字符串
	 * @Description
	 * @param obj
	 * @return
	 * @author caobin
	 */
	public static String obj2JsonString(Object obj){	
		try{
			ObjectMapper objectMapper = instanceObjectMapper();
			if(obj == null)return EMPTY_JSON_STRING;
			return objectMapper.writeValueAsString(obj);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * JSON字符串转换为Map
	 * @param strJsonString 需要转换的JSON字符串
	 * @return
	 */
	public static<E, V> Map<E, V> jsonString2Map(String strJsonString){
		try {
			ObjectMapper objectMapper = instanceObjectMapper();
			if(StringUtils.isBlank(strJsonString))strJsonString = EMPTY_JSON_STRING;
			return objectMapper.readValue(strJsonString, Map.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
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
			ObjectMapper objectMapper = instanceObjectMapper();
			if(StringUtils.isBlank(strJsonString))strJsonString = EMPTY_JSON_STRING;
			return objectMapper.readValue(strJsonString, clazz);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		}
	}

	
	/**
	 * 实例化ObjectMapper
	 * @return
	 */
	public static ObjectMapper instanceObjectMapper(){
		JsonFactory jf = new JsonFactory();
		jf.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
		return new ObjectMapper(jf);
	}
	
	/**
	 * json转换为list
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static<T> List<T> json2ObjectList(String jsonStr , Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,clazz);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysRuntimeException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 空JSON字符串
	 */
	private final static String EMPTY_JSON_STRING = "{}";
}
