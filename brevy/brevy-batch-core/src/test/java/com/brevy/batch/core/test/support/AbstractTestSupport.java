package com.brevy.batch.core.test.support;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description 后台单元测试基类
 * @author caobin
 * @date 2012-11-19
 * @version 1.0
 */

public abstract class AbstractTestSupport{
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp(){
		log.info("调用测试方法: {}", new Object[]{name.getMethodName()});
	}
	
	@After
	public void tearDown(){
		//TODO ...
	}
}
