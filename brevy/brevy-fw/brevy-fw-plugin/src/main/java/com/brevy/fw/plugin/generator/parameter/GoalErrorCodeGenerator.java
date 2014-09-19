package com.brevy.fw.plugin.generator.parameter;


/**
 * @Description goal为errorCodeGenerator的操作
 * @author caobin
 * @date 2013-7-22
 * @version 1.0
 */
public class GoalErrorCodeGenerator {
	/**
	 * 错误码的属性文件(含包名，如msg/Errors)
	 */
	private String[] resourceFiles;

	/**
	 * 生成目标的java类(含包名，如com.xx.Errors)
	 */
	private String targetJavaClass;

	/**
	 * @return the resourceFiles
	 */
	public String[] getResourceFiles() {
		return resourceFiles;
	}

	/**
	 * @param resourceFiles the resourceFiles to set
	 */
	public void setResourceFiles(String[] resourceFiles) {
		this.resourceFiles = resourceFiles;
	}

	/**
	 * @return the targetJavaClass
	 */
	public String getTargetJavaClass() {
		return targetJavaClass;
	}

	/**
	 * @param targetJavaClass the targetJavaClass to set
	 */
	public void setTargetJavaClass(String targetJavaClass) {
		this.targetJavaClass = targetJavaClass;
	}
}
