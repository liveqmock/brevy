package com.brevy.fw.common.pattern.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.brevy.fw.common.pattern.chain.impl.ContextBase;
import com.brevy.fw.common.pattern.chain.spring.template.ChainTemplate;
import com.brevy.fw.common.support.exception.CoreException;
import com.brevy.fw.common.test.support.AbstractSpringContextTestSupport;

/**
 * @Description 
 * @author caobin
 * @date 2013-7-23
 * @version 1.0
 */
public class ChainPatternTestcase extends AbstractSpringContextTestSupport{

	@Test
	public void chainExecute(){
		Context ctx = new ContextBase();
		ctx.setChainId("All");
		try {
			chainExecutor.executeChain(ctx);
			log.debug("Get Exception: {}", new Object[]{ctx.getException()});
		} catch (CoreException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void executeActionFlow(){
		try {
			chainTemplate.executeActionFlow("All", "start", null);
		} catch (CoreException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Autowired
	private ChainExecutor chainExecutor;
	@Autowired
	private ChainTemplate chainTemplate;
}
