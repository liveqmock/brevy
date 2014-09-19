package com.brevy.batch.core.joblaunchdetails.support;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description Job加载细节
 * @author caobin
 * @date 2013-9-23
 * @version 1.0
 */
public class JobLaunchDetail {
	
	/**
	 * JOB名称
	 */
	private String jobName;
	
	/**
	 * （是否）关键任务
	 */
	private boolean critical;

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the critical
	 */
	public boolean isCritical() {
		return critical;
	}

	/**
	 * @param critical the critical to set
	 */
	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
