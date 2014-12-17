package com.brevy.plugin.remove;

import java.io.File;

/**
 * @Description 移除资源
 * @author caobin
 * @date 2013-7-18
 * @version 1.0
 */
public class Resource {
	
	/**
	 * 基目录
	 */
	private File baseDirectory;

	/**
	 * 文件/目录匹配表达式
	 */
	private String[] filePatterns;

	
	/**
	 * @return the baseDirectory
	 */
	public File getBaseDirectory() {
		return baseDirectory;
	}

	/**
	 * @param baseDirectory the baseDirectory to set
	 */
	public void setBaseDirectory(File baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	/**
	 * @return the filePatterns
	 */
	public String[] getFilePatterns() {
		return filePatterns;
	}

	/**
	 * @param filePatterns the filePatterns to set
	 */
	public void setFilePatterns(String[] filePatterns) {
		this.filePatterns = filePatterns;
	}
}
