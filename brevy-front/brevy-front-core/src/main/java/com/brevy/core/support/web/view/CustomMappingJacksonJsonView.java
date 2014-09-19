package com.brevy.core.support.web.view;

import static com.brevy.core.support.SupportConstants.WEB_CONTROLLER_THROWABLE_KEY;
import static com.brevy.core.support.SupportConstants.WEB_RSP_BODY;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_ERROR_CODE;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_ERROR_FIELDS;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_ERROR_FIELDS_KEY;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_ERROR_MESSAGE;
import static com.brevy.core.support.SupportConstants.WEB_RSP_HEAD_TRAN_SUCCESS;
import static com.brevy.core.support.SupportConstants.WEB_VIEW_RESULT_KEY;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.brevy.core.support.exception.CoreException;
import com.brevy.core.util.ExceptionUtils;


/**
 * @Description 针对特定的JSON格式定制的jsonView
 * @author caobin
 * @date 2013-7-17
 * @version 1.0
 */
public class CustomMappingJacksonJsonView extends MappingJackson2JsonView implements InitializingBean{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.json.MappingJacksonJsonView#filterModel(java.util.Map)
	 * 重构filterModel，对其中的model根据自定json的格式进行修改
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object filterModel(Map<String, Object> model) {
		//获取异常信息
		CoreException ce = (CoreException)model.remove(WEB_CONTROLLER_THROWABLE_KEY);	
		//获取错误标识字段
		Map<String, String> errorFields = (Map)model.remove(WEB_RSP_HEAD_ERROR_FIELDS_KEY);
		
		Map<String, Object> wrappedModel = new HashMap<String, Object>();
		Map<String, Object> rspHead = new HashMap<String, Object>();
		rspHead.put(WEB_RSP_HEAD_TRAN_SUCCESS, model.remove(WEB_VIEW_RESULT_KEY));		
		if(ce != null){
			log.error(ce.getMessage(), ce);
			if(ce.getMessageCode() != null){
				rspHead.put(WEB_RSP_HEAD_ERROR_CODE, ce.getMessageCode().getCode());
				rspHead.put(WEB_RSP_HEAD_ERROR_MESSAGE, messageSource.getMessage(
						ce.getMessageCode().getCode(), 
						ce.getMessageCode().getArgs(), 
						Locale.getDefault()
					)
				);	
			}else{
				rspHead.put(WEB_RSP_HEAD_ERROR_MESSAGE, 
						ExceptionUtils.getRootCause(ce) == null ? 
						ce.getMessage() : ExceptionUtils.getRootCause(ce).getMessage()
				);	
			}	
		}
		
		if(errorFields != null){
			rspHead.put(WEB_RSP_HEAD_ERROR_FIELDS, errorFields);
		}
		
		Object filtedModel = super.filterModel(model);		
		wrappedModel.put(WEB_RSP_HEAD, rspHead);	
		wrappedModel.put(WEB_RSP_BODY, filtedModel);	
		return wrappedModel;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(messageSource);
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MessageSource messageSource;

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
