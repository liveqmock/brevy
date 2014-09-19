package com.brevy.archetype.module.sample;

import static com.brevy.archetype.support.SupportConstants.WEB_VIEW_RESULT_KEY;
import static com.brevy.archetype.support.SupportConstants.WEB_VIEW_RESULT_SUCCESS;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.brevy.archetype.support.test.AbstractSpringMvcTestSupport;

/**
 * @Description 
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
public class SampleControllerTestcase extends AbstractSpringMvcTestSupport{
	
	/**
	 * @Description 显示所有城市
	 * @author caobin
	 */
	@Test
	public void showAllCities(){
		try {
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/sample/showAllCities.html"))//执行请求
					.andExpect(MockMvcResultMatchers.view().name("default")) //断言返回视图名称
					.andExpect(MockMvcResultMatchers.model().attributeExists("allCities"))//断言属性
					.andDo(MockMvcResultHandlers.print()) //输出整个响应结果信息
					.andReturn();
			Assert.assertEquals(WEB_VIEW_RESULT_SUCCESS, result.getModelAndView().getModel().get(WEB_VIEW_RESULT_KEY));			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		}  
	}
	
	
	/**
	 * @Description 分页显示城市
	 * @author caobin
	 */
	@Test
	public void showCitiesPaged(){
		try {
			MvcResult result = mockMvc
					.perform(
							MockMvcRequestBuilders.post("/sample/showCities.html")
							.param("start", "1")
							.param("limit", "10")
					)//执行请求 and 请求参数
					.andExpect(MockMvcResultMatchers.view().name("default")) //断言返回视图名称
					.andExpect(MockMvcResultMatchers.model().attributeExists("cities"))//断言属性
					.andDo(MockMvcResultHandlers.print()) //输出整个响应结果信息
					.andReturn();
			Assert.assertEquals(WEB_VIEW_RESULT_SUCCESS, result.getModelAndView().getModel().get(WEB_VIEW_RESULT_KEY));			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		}  
	}
}
