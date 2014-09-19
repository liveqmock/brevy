package com.brevy.archetype.support.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description Model基类
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1287855904350877605L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	
	
}
