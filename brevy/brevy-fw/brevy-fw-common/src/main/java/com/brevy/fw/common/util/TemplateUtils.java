package com.brevy.fw.common.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

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
		 * @param outputpath 输出路径
		 * @param velocityContext 输入参数
		 * @throws IOException 
		 */
		public static void executeTemplateGenerator(String vmpath, String outputpath, VelocityContext velocityContext) throws IOException{
			Properties objProperties = new Properties();
			objProperties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			objProperties.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
			//objProperties.put(VelocityEngine.FILE_RESOURCE_LOADER_PATH, vmpath);
			objProperties.put(VelocityEngine.ENCODING_DEFAULT, "UTF-8");
			objProperties.put(VelocityEngine.INPUT_ENCODING, "UTF-8");
			objProperties.put(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
			//objProperties.put(VelocityEngine.VM_LIBRARY, "macro.vm");
			VelocityEngine objVelocityEngine = new VelocityEngine();
			objVelocityEngine.init(objProperties);
			Template objTemplate = objVelocityEngine.getTemplate(vmpath);
			BufferedWriter objBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputpath), "UTF-8"));
			objTemplate.merge(velocityContext, objBufferedWriter);
			objBufferedWriter.flush();
			objBufferedWriter.close();
		}

	}
	
	/**
	 * @Description Freemarker模板工具
	 * @author caobin
	 * @date 2012-11-26
	 * @version 1.0
	 */
	public static class FreemarkerHelper {
		//TODO: 扩展
	}
}
