package com.brevy.test.core.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.service.ShiroService;
import com.brevy.test.support.AbstractSpringContextTestSupport;

/**
 * @Description 
 * @author caobin
 * @date 2013-12-9
 * @version 1.0
 */
public class ShiroServiceTestcase extends AbstractSpringContextTestSupport{

	@Test
	public void save(){
		ApMenu apMenu = new ApMenu();
		apMenu.setAppId(1000);
		apMenu.setParentId(-1);
		apMenu.setName("测试");
		apMenu.setLeaf("0");
		apMenu.setHierarchy(1);
		apMenu.setStatus("1");
		apMenu.setSort(1);
		
		ApMenu rtnApMenu = shiroService.saveApMenu(apMenu);
		log.info(">>>> {} Saved", new Object[]{rtnApMenu});
	}
	
	@Test(invocationCount=5000, threadPoolSize=100)
	public void testConcurrent(){
		System.out.println(">>>>>> TEST THREAD: " + Thread.currentThread().getId());
	}


	@Autowired
	private ShiroService shiroService;
}
