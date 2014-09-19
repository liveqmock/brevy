package com.brevy.spring.cxf.ws.service.fc.formula;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.support.AbstractSpringContextTestSupport;

import com.brevy.spring.cxf.ws.services.fc.formula.ExprCType;
import com.brevy.spring.cxf.ws.services.fc.formula.FormulaPortType;
import com.brevy.spring.cxf.ws.services.fc.formula.SettingsCType;

/**
 * @Description 
 * @author caobin
 * @date 2013-6-6
 * @version 1.0
 */
public class FormulaWSTestcase extends AbstractSpringContextTestSupport {
	@Value("${ws.fc.formula.addr}")
	private String wsFcFormulaAddr;
	@Resource
	private FormulaPortType formulaService;
	@Resource
	private FormulaPortType formulaClient;
	
	/**
	 * @Description 初始化Formula的WS服务
	 * @author caobin
	 */
	@BeforeClass
	public void initFormulaWebService(){
		log.info(">>>>> preparing to init web service <<<<<");
		//初始化测试端点
		this.initWsEndpoint(formulaService, "fcFormulaEndpoint", wsFcFormulaAddr);
	}
	
	/**
	 * @Description 创建公式(WS)
	 * @author caobin
	 */
	@Test
	public void createFormula() {		
		ExprCType formulaRequestPart = new ExprCType();
		formulaRequestPart.setExpression("(5*2)/(3-1+6)+4-1/5");
		SettingsCType settings = new SettingsCType();
		settings.setScale(2);
		settings.setRoundingMode(BigDecimal.ROUND_HALF_UP);
		formulaRequestPart.setSettings(settings);
		BigDecimal result = formulaClient.createFormula(formulaRequestPart);
			
		log.info("Get result: {}", new Object[]{result});
		
		Assert.assertEquals(result, new BigDecimal("5.05"));
	}
}
