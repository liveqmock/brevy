package com.brevy.commons.vfs.core.compress;

import java.io.File;

import org.apache.commons.vfs2.FileType;

public interface Compressor {

	/**
	 * @Description 解压文件到当前文件夹
	 * @param compressedFile 待解压文件 
	 * @author caobin
	 */
	void decompress(File compressedFile);
	
	/**
	 * @Description 解压文件到指定文件夹
	 * @param compressedFile 待解压文件 
	 * @param targetDir 目标目录（为空则解压至当前目录）
	 * @author caobin
	 */
	void decompress(File compressedFile, File targetDir);
	
	/**
	 * @Description 解压文件到指定文件夹
	 * @param compressedFile 待解压文件 
	 * @param internalFilePath 指定压缩包内部文件(夹)路径（为空则解压整个压缩包），如：/somepath/somefile
	 * @param targetDir 目标目录（为空则解压至当前目录）
	 * @author caobin
	 */
	void decompress(File compressedFile, String internalFilePath, File targetDir);
	
	/**
	 * @Description 解压文件到指定文件夹
	 * @param compressedFile 待解压文件
	 * @param internalFilePath 指定压缩包内部文件(夹)路径（为空则解压整个压缩包），如：/somepath/somefile
	 * @param targetDir 目标目录（为空则解压至当前目录）
	 * @param filetype 文件类型
	 * @author caobin
	 */
	void decompress(File compressedFile, String internalFilePath, File targetDir, FileType filetype);
	

	
	/**
	 * @Description 将指定多个文件（夹）打包
	 * @param sourceFileOrDirs 待打包文件（夹）
	 * @param targetFile 目标文件
	 * @author caobin
	 */
	void compress(File[] sourceFileOrDirs, File targetFile);
	
	
	/**
	 * @Description 将指定多个文件（夹）打包
	 * @param sourceFileOrDirs 待打包文件（夹）
	 * @param targetFile 目标文件
	 * @param charset 字符集
	 * @author caobin
	 */
	void compress(File[] sourceFileOrDirs, File targetFile, String charset);
	
	/**
	 * @Description 将指定文件（夹）打包,存放于当前目录
	 * @param sourceFileOrDir 待打包文件（夹）
	 * @author caobin
	 */
	void compress(File sourceFileOrDir);
	
	/**
	 * @Description 将指定文件（夹）打包
	 * @param sourceFileOrDir 待打包文件（夹）
	 * @param targetFile 目标文件
	 * @author caobin
	 */
	void compress(File sourceFileOrDir, File targetFile);
	
	/**
	 * @Description 将指定文件（夹）打包
	 * @param sourceFileOrDir 待打包文件（夹）
	 * @param targetFile 目标文件
	 * @param charset 字符集
	 * @author caobin
	 */
	void compress(File sourceFileOrDir, File targetFile, String charset);

	/**
	 * @Description 关闭VFS Manager
	 * @author caobin
	 */
	public void close();
}
