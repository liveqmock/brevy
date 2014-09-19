package com.brevy.batch.core.jobs.sample.listener;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @Description Job执行监听器
 * @author caobin
 * @date 2013-9-23
 * @version 1.0
 */
public class SampleJobExecutionListener implements JobExecutionListener {

	@Override
	public void afterJob(JobExecution jobexecution) {
		
	}

	@Override
	public void beforeJob(JobExecution jobexecution) {
		log.info(">>>> 检查TESTA表中是否有数据");
		
		
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
