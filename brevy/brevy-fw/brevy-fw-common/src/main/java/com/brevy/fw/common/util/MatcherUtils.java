package com.brevy.fw.common.util;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @Description 匹配工具类
 * @author caobin
 * @date 2013-7-5
 * @version 1.0
 */
public class MatcherUtils{
	
	/**
	 * @Description 创建一个新的路径匹配器
	 * @return
	 * @author caobin
	 */
	public static PathMatcher createNewPathMatcher(){
		return new AntPathMatcher();
	}

	/**
	 * @Description 路径匹配（默认路径匹配器）
	 * @param pattern 路径匹配模式
	 * @param path 真实路径
	 * @return
	 * @author caobin
	 */
	public static boolean pathMatch(String pattern, String path){
		return pathMatch(DEFAULT_PATH_MATCHER, pattern, path);
	}
	
	/**
	 * @Description 路径匹配（指定路径匹配器）
	 * @param pathMatcher 路径匹配器
	 * @param pattern 路径匹配模式
	 * @param path 真实路径
	 * @return
	 * @author caobin
	 */
	public static boolean pathMatch(PathMatcher pathMatcher, String pattern, String path){
		return pathMatcher.match(pattern, path);
	}
	
	
	/**
	 * @Description 获取匹配文件路径集合
	 * @param collected 符合条件的文件路径
	 * @param file 待匹配文件路径
	 * @param antPattern 匹配文件路径模式
	 * @param isBasePath 是否为基路径（最顶层路径）
	 * @author caobin
	 */
	public static void collectFiles(List<File> collected, File file, String antPattern, boolean isBasePath){
		//获取转换后的原文件路径
		String filePath = file.getAbsolutePath().replace("\\", "/");
		filePath = filePath.endsWith("/") ? filePath : filePath.concat("/");
		
		if(antPattern.contains("\\")){
			antPattern = antPattern.replace("\\", "/");
		}
		if(isBasePath){
			antPattern = filePath.concat(antPattern);
		}
		
		filePath = filePath.replaceFirst("/$", "");
		file = new File(filePath);
		
		if(file.isFile()){//文件处理		
			//匹配处理
			if(DEFAULT_PATH_MATCHER.match(antPattern, filePath)){
				collected.add(file);
				LOG.debug("Matched file: {}", file.getAbsolutePath());
			}
		}else{//目录处理
			File[] files = file.listFiles();
			if(files != null){
				for(File subFile : files){
					collectFiles(collected, subFile, antPattern, false);
				}
			}	
		}
	}
	
	/**
	 * 默认路径匹配器
	 */
	private final static PathMatcher DEFAULT_PATH_MATCHER = new AntPathMatcher();
	
	private static final Logger LOG = LoggerFactory.getLogger(MatcherUtils.class);
}
