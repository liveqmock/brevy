package com.ips.commons.support.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @Description 模板工具
 * @author caobin
 * @date 2012-11-26
 * @version 1.0
 */
public class TemplateUtils {

	/**
	 * @Description Velocity模板工具
	 * @author caobin
	 * @date 2012-11-26
	 * @version 1.0
	 */
	public static class VelocityHelper {
		/**
		 * 通过模板生成文件
		 * @param vmpath vm文件路径
		 * @param vmname vm文件名称
		 * @param outputpath 输出路径
		 * @param velocityContext 输入参数
		 * @throws IOException 
		 */
		public static void executeTemplateGenerator(String vmpath, String vmname, String outputpath, 
				VelocityContext velocityContext) throws IOException{
			Properties objProperties = new Properties();
			objProperties.put(VelocityEngine.FILE_RESOURCE_LOADER_PATH, vmpath);
			objProperties.put(VelocityEngine.ENCODING_DEFAULT, CHARSET);
			objProperties.put(VelocityEngine.INPUT_ENCODING, CHARSET);
			objProperties.put(VelocityEngine.OUTPUT_ENCODING, CHARSET);
			//objProperties.put(VelocityEngine.VM_LIBRARY, "macro.vm");
			VelocityEngine objVelocityEngine = new VelocityEngine();
			objVelocityEngine.init(objProperties);
			Template objTemplate = objVelocityEngine.getTemplate(vmname, CHARSET);
			BufferedWriter objBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputpath), CHARSET));
			objTemplate.merge(velocityContext, objBufferedWriter);
			IOUtils.closeQuietly(objBufferedWriter);
		}
		
		/**
		 * @Description 通过模板生成内容并通过response输出
		 * @param fullVmPath 完整vm路径(classpath开始，含文件名)
		 * @param response
		 * @param velocityContext
		 * @throws Exception
		 * @author caobin
		 */
		public static void executeTemplateGenerator(String fullVmPath, HttpServletResponse response, 
				VelocityContext velocityContext) throws Exception{
			Properties objProperties = new Properties();
			
			
			//设置classpath		
			objProperties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			objProperties.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
			
			//for debug
			//objProperties.put(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "D:/workspace/bmps/src/main/resources/");
			
			
			objProperties.put(VelocityEngine.ENCODING_DEFAULT, CHARSET);
			objProperties.put(VelocityEngine.INPUT_ENCODING, CHARSET);
			objProperties.put(VelocityEngine.OUTPUT_ENCODING, CHARSET);			
			VelocityEngine objVelocityEngine = new VelocityEngine();
			objVelocityEngine.init(objProperties);
			Template objTemplate = objVelocityEngine.getTemplate(fullVmPath, CHARSET);		
			BufferedWriter objBufferedWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), CHARSET));
			objTemplate.merge(velocityContext, objBufferedWriter);
			IOUtils.closeQuietly(objBufferedWriter);
		}

	}
	
	
	private final static String CHARSET = "UTF-8";
	
	/**
	 * @Description Freemarker模板工具
	 * @author caobin
	 * @date 2012-11-26
	 * @version 1.0
	 */
	public static class FreemarkerHelper {
		
	}
}
