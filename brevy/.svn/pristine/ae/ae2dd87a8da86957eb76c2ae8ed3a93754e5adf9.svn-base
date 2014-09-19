package com.brevy.fw.common.support.web.controller;

import static com.brevy.fw.common.support.SupportConstants.WEB_CONTROLLER_THROWABLE_KEY;
import static com.brevy.fw.common.support.SupportConstants.WEB_REQ_HEAD;
import static com.brevy.fw.common.support.SupportConstants.WEB_RSP_HEAD_ERROR_FIELDS_KEY;
import static com.brevy.fw.common.support.SupportConstants.WEB_VIEW_RESULT_FAILURE;
import static com.brevy.fw.common.support.SupportConstants.WEB_VIEW_RESULT_KEY;
import static com.brevy.fw.common.support.SupportConstants.WEB_VIEW_RESULT_SUCCESS;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.brevy.fw.common.pattern.chain.spring.template.ChainTemplate;
import com.brevy.fw.common.support.exception.CoreException;
import com.brevy.fw.common.support.web.http.RequestHead;

/**
 * @Description 控制器基类
 * @author caobin
 * @date 2013-7-15
 * @version 1.0
 */
public abstract class BaseController {
	
	/**
	 * @Description 创建ModelAndView
	 * @return
	 * @author caobin
	 */
	protected ModelAndView createMav(){
		return new ModelAndView();
	}
	
	/**
	 * @Description  创建ModelAndView
	 * @param viewName 视图名称
	 * @return
	 * @author caobin
	 */
	protected ModelAndView createMav(String viewName){
		return new ModelAndView(viewName);
	}
	
	/**
	 * @Description  创建ModelAndView
	 * @param viewName 视图名称
	 * @param model 模型
	 * @return
	 * @author caobin
	 */
	protected ModelAndView createMav(String viewName, Map<String, ?> model) {
		return new ModelAndView(viewName, model);
	}
	
	/**
	 * @Description 创建ModelAndView
	 * @param view 视图
	 * @param model 模型
	 * @return
	 * @author caobin
	 */
	protected ModelAndView createMav(View view, Map<String, ?> model) {
		return new ModelAndView(view, model);
	}
	
	
	/**
	 * @Description 返回成功视图(for json)
	 * @param mav ModelAndView
	 * @return
	 * @author caobin
	 */
	protected ModelAndView successView(ModelAndView mav){
		return mav.addObject(WEB_VIEW_RESULT_KEY, WEB_VIEW_RESULT_SUCCESS);
	}
	
	/**
	 * @Description 返回成功视图(for json)
	 * @return
	 * @author caobin
	 */
	protected ModelAndView successView(){
		return createMav().addObject(WEB_VIEW_RESULT_KEY, WEB_VIEW_RESULT_SUCCESS);
	}
	
	/**
	 * @Description 返回失败视图(for json)
	 * @param mav ModelAndView
	 * @param ce 异常
	 * @return
	 * @author caobin
	 */
	protected ModelAndView failureView(ModelAndView mav, CoreException ce){
		mav.addObject(WEB_CONTROLLER_THROWABLE_KEY, ce);
		return mav.addObject(WEB_VIEW_RESULT_KEY, WEB_VIEW_RESULT_FAILURE);
	}
	
	/**
	 * @Description 返回失败视图(for json)
	 * @param ce 异常
	 * @return
	 * @author caobin
	 */
	protected ModelAndView failureView(CoreException ce){
		ModelAndView mav = createMav();
		mav.addObject(WEB_CONTROLLER_THROWABLE_KEY, ce);
		return mav.addObject(WEB_VIEW_RESULT_KEY, WEB_VIEW_RESULT_FAILURE);
	}
	
	/**
	 * @Description 返回失败视图(for json)
	 * @param mav ModelAndView
	 * @param ce 异常
	 * @param errorFields 标识页面异常字段/信息，如：map.put("username", "用户状态异常")
	 * @return
	 * @author caobin
	 */
	protected ModelAndView failureView(ModelAndView mav, CoreException ce, Map<String, String> errorFields){
		mav.addObject(WEB_CONTROLLER_THROWABLE_KEY, ce).addObject(WEB_RSP_HEAD_ERROR_FIELDS_KEY, errorFields);
		return mav.addObject(WEB_VIEW_RESULT_KEY, WEB_VIEW_RESULT_FAILURE);
	}

	
	/**
	 * @Description 获取报文请求头
	 * @return
	 * @author caobin
	 */
	protected RequestHead getReqHead(){
		return (RequestHead)RequestContextHolder.getRequestAttributes().getAttribute(WEB_REQ_HEAD, RequestAttributes.SCOPE_SESSION);
	}
	
	
	/**
	 * @Description 执行action流程
	 * @param chainBeanName 责任链bean的id/name(设置到Context的chainId中)
	 * @param initState 初始（业务）状态(设置到Context的state中)
	 * @param dataMap 数据集(合并到Context的dataMap中)
	 * @author caobin
	 * @throws CoreException 
	 */
	protected void executeActionFlow(String chainBeanName, String initState, Map<String, Object> dataMap) throws CoreException{
		if(chainTemplate == null){
			throw new CoreException("must inject chainTemplate first.");
		}
		chainTemplate.executeActionFlow(chainBeanName, initState, dataMap);
	}
	
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 责任链模板
	 */
	private ChainTemplate chainTemplate;

	/**
	 * @param chainTemplate the chainTemplate to set
	 */
	public void setChainTemplate(ChainTemplate chainTemplate) {
		this.chainTemplate = chainTemplate;
	}
}
