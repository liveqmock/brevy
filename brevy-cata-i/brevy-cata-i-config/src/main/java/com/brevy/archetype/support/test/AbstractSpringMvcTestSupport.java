package com.brevy.archetype.support.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description SpringMVC测试支持类
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
		"classpath:/spring-basic.xml", 
		"classpath:/spring-mvc.xml" 
})
@ActiveProfiles("dev")
// 激活dev配置
public abstract class AbstractSpringMvcTestSupport {
	
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());

	@Rule
	public TestName name = new TestName();
	
	@Autowired
	protected WebApplicationContext wac;
	
	protected MockMvc mockMvc;

	
	@Before
	public void setup() {
		log.info("Invoking test method：{}", new Object[]{name.getMethodName()});
		log.info("initializing MockMvc...");
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
