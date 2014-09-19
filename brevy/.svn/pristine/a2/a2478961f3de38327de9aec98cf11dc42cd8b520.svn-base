package com.brevy.spring.cxf.ws.service.fc.calculator;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.support.AbstractSpringContextTestSupport;

import com.brevy.spring.cxf.ws.services.fc.calculator.CalculatorPortType;
import com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType;

/**
 * @Description
 * @author caobin
 * @date 2013-6-5
 * @version 1.0
 */
public class CalculatorWSTestcase extends AbstractSpringContextTestSupport{
	
	@Value("${ws.fc.calculator.addr}")
	private String wsFcCalculatorAddr;
	@Resource
	private CalculatorPortType calculatorService;
	@Resource
	private CalculatorPortType calculatorClient;
	@Resource
	private CalculatorPortType jmsCalculatorClient;
	
	/**
	 * @Description 初始化Calculator的WS服务
	 * @author caobin
	 */
	@BeforeClass
	public void initCalculatorWebService(){
		log.info(">>>>> preparing to init web service <<<<<");
		//初始化测试端点
		this.initWsEndpoint(calculatorService, "fcCalculatorEndpoint", wsFcCalculatorAddr);
	}
	
	/**
	 * @Description 加法计算(WS)
	 * @author caobin
	 */
	@Test
	public void calculateAdd() {
		SettingsCType settings = new SettingsCType();
		settings.setScale(2);
		settings.setRoundingMode(BigDecimal.ROUND_HALF_UP);
		
		BigDecimal calResult = calculatorClient.add(new BigDecimal("5.6"), new BigDecimal("7.7"), settings);
		log.info("Get result(+): {}", new Object[]{calResult});
		
		Assert.assertEquals(calResult, new BigDecimal("13.30"));
	}
	
	/**
	 * @Description 加法计算(JMS)
	 * @author caobin
	 */
	@Test
	public void jmsCalculateAdd() {
		SettingsCType settings = new SettingsCType();
		settings.setScale(2);
		settings.setRoundingMode(BigDecimal.ROUND_HALF_UP);
		
		BigDecimal calResult = jmsCalculatorClient.add(new BigDecimal("5.6"), new BigDecimal("7.7"), settings);
		log.info("Get result(+): {}", new Object[]{calResult});
		
		Assert.assertEquals(calResult, new BigDecimal("13.30"));
	}
	
	/**
	 * @Description 减法计算(WS)
	 * @author caobin
	 */
	@Test
	public void calculateMinus(){
		SettingsCType settings = new SettingsCType();
		settings.setScale(4);
		settings.setRoundingMode(BigDecimal.ROUND_HALF_UP);
		
		BigDecimal calResult = calculatorClient.minus(new BigDecimal("10"), new BigDecimal("2.36"), settings);
		log.info("Get result(-): {}", new Object[]{calResult});
		
		Assert.assertEquals(calResult, new BigDecimal("7.6400"));
	}
	
	
	/**
	 * @Description 乘法计算(WS)
	 * @author caobin
	 */
	@Test
	public void calculateMultiply(){
		SettingsCType settings = new SettingsCType();
		settings.setScale(3);
		settings.setRoundingMode(BigDecimal.ROUND_HALF_UP);
		
		BigDecimal calResult = calculatorClient.multiply(new BigDecimal("3"), new BigDecimal("3.1"), settings);
		log.info("Get result(*): {}", new Object[]{calResult});
		
		Assert.assertEquals(calResult, new BigDecimal("9.300"));
	}
	
	/**
	 * @Description 除法计算(WS)
	 * @author caobin
	 */
	@Test
	public void calculateDivide(){
		SettingsCType settings = new SettingsCType();
		settings.setScale(8);
		settings.setRoundingMode(BigDecimal.ROUND_HALF_UP);
		
		BigDecimal calResult = calculatorClient.divide(new BigDecimal("22"), new BigDecimal("7"), settings);
		log.info("Get result(/): {}", new Object[]{calResult});
		
		Assert.assertEquals(calResult, new BigDecimal("3.14285714"));
	}

}
