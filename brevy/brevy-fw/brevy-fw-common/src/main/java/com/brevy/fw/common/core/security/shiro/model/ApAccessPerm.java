package com.brevy.fw.common.core.security.shiro.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ApAccessPerm implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String urlPattern;

    private String patternExpr;

	/**
	 * @return the urlPattern
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @param urlPattern the urlPattern to set
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	/**
	 * @return the patternExpr
	 */
	public String getPatternExpr() {
		return patternExpr;
	}

	/**
	 * @param patternExpr the patternExpr to set
	 */
	public void setPatternExpr(String patternExpr) {
		this.patternExpr = patternExpr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}