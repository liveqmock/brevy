package com.brevy.fw.common.support.web.controller.binder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * @Description 自定义属性编辑器
 * @author caobin
 * @date 2013-4-19
 * @version 1.0
 */
public class CustomBindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		initDateBinder(binder, request);
	}

	/**
	 * @Description 日期属性编辑器
	 * @param binder
	 * @param request
	 * @author caobin
	 */
	private void initDateBinder(WebDataBinder binder, WebRequest request) {
		// 扩展日期绑定
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					setValue(DateUtils.parseDate(value, candidateDatePatterns));
				} catch (ParseException e) {
					log.error(e.getMessage(), e);
					setValue(null);
				}
			}

			public String getAsText() {
				return String.valueOf(getValue());
			}
		});
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 候选日期模式
	 */
	private String[] candidateDatePatterns;

	public void setCandidateDatePatterns(String[] candidateDatePatterns) {
		this.candidateDatePatterns = candidateDatePatterns;
	}
}
