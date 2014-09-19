package com.brevy.fw.common.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description Json工具类测试
 * @author caobin
 * @date 2013-7-5
 * @version 1.0
 */
public class JsonUtilsTestcase {

	/**
	 * @Description getObjectMapper的多线程测试
	 * @author caobin
	 */
	@Test(invocationCount=500, threadPoolSize=50)
	public void MultiThreadsTest_GetObjectMapper(){
		ObjectMapper objectMapper = JsonUtils.getObjectMapper();
		objectMapperSet.add(objectMapper.toString());
		log.info("Thread ID: [{}], Object Hashcode: [{}]", new Object[]{
				Thread.currentThread().getId(), 
				objectMapper
		});
		Assert.assertEquals(objectMapperSet.size(), 1);
	}
	
	/**
	 * @Description obj2JsonString数字类型测试
	 * @author caobin
	 */
	@Test
	public void obj2JsonString4Number(){
		String jsonString ="{\"fee\":789846041418.2341681,\"amt\":999999999999.99,\"tester\":\"George\"}";
		log.info("Json input: {}", new Object[]{JsonUtils.jsonString2Map(jsonString)});
		
		Map<String, Object> mpJsonObj = new HashMap<String, Object>();
		mpJsonObj.put("amt", new BigDecimal("999999999999.99"));
		mpJsonObj.put("fee", new BigDecimal("789846041418.2341681"));
		mpJsonObj.put("tester", "George");	
		log.info("Json output: {}", new Object[]{JsonUtils.obj2JsonString(mpJsonObj)});
	}

	
	private final static Set<String> objectMapperSet = new HashSet<String>();
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
