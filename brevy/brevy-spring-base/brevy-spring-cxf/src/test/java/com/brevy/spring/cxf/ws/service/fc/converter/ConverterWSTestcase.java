package com.brevy.spring.cxf.ws.service.fc.converter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.support.AbstractSpringContextTestSupport;

import com.brevy.spring.cxf.ws.services.fc.converter.ConvertCType;
import com.brevy.spring.cxf.ws.services.fc.converter.ConverterPortType;

/**
 * @Description 
 * @author caobin
 * @date 2013-6-5
 * @version 1.0
 */
public class ConverterWSTestcase extends AbstractSpringContextTestSupport {
	@Value("${ws.fc.converter.addr}")
	private String wsFcConverterAddr;
	@Resource
	private ConverterPortType converterService;
	@Resource
	private ConverterPortType converterClient;
	
	/**
	 * @Description 初始化Converter的WS服务
	 * @author caobin
	 */
	@BeforeClass
	public void initConverterWebService(){
		log.info(">>>>> preparing to init web service <<<<<");	
		//初始化测试端点
		this.initWsEndpoint(converterService, "fcConverterEndpoint", wsFcConverterAddr);
	}
	
	/**
	 * @Description 转换运算
	 * @author caobin
	 */
	@Test
	public void convert(){
		ConvertCType converterRequestPart = new ConvertCType();
		converterRequestPart.setParam(15);
		converterRequestPart.setRadix(16);
		
		String calResult = converterClient.convert(converterRequestPart);
		log.info("Get result: {}", new Object[]{calResult});
		
		Assert.assertEquals(calResult, "f");
	}
}
