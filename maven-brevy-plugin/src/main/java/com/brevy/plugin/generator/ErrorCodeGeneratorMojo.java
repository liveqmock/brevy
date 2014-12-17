package com.brevy.plugin.generator;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.velocity.VelocityContext;
import org.joda.time.DateTime;

import com.brevy.util.TemplateUtils;

/**
 * 
 * ErrorCode生成GOAL#caobin#2013-7-22
 * 
 * @goal errorCodeGenerator
 */
public class ErrorCodeGeneratorMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Validate.notNull(goalErrorCodeGenerator.getTargetJavaClass(), "targetJavaClass is required");
		Validate.notNull(goalErrorCodeGenerator.getResourceFiles(), "resourceFiles is required");
		
		try {
			//读取资源文件
			Properties pf = new Properties();
			File f = null;
			for (String resourceFile : goalErrorCodeGenerator.getResourceFiles()) {
				f = new File(resources.get(0).getDirectory().concat("/")
						.concat(resourceFile).concat(".properties"));
				getLog().info(String.format("loading resource file : %s", f.getAbsolutePath()));
				pf.load(new FileInputStream(f));
				getLog().info(String.format("loaded resource file : %s", f.getAbsolutePath()));	
			}
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("package", StringUtils.substringBeforeLast(goalErrorCodeGenerator.getTargetJavaClass(), "."));//设置包名
			velocityContext.put("className", StringUtils.substringAfterLast(goalErrorCodeGenerator.getTargetJavaClass(), "."));//设置类名
			velocityContext.put("createdate", DateTime.now().toString("yyyy-MM-dd"));//设置创建日期
			velocityContext.put("prefix", "E");//设置前缀
			velocityContext.put("errors", pf);//设置映射属性
			
			getLog().info("generating error code file via template...");
			TemplateUtils.VelocityHelper.executeTemplateGenerator(
					"vtpl/ErrorCode.vm" ,
					new StringBuilder()
						.append(sourceDirectory).append("/")
						.append(goalErrorCodeGenerator.getTargetJavaClass().replace(".", "/"))
						.append(".java").toString()
						, 
					velocityContext
			);
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}

	/**
	 * goal为errorCodeGenerator的参数集
	 * @parameter
	 * @required
	 */
	private GoalErrorCodeGenerator goalErrorCodeGenerator;
	
	/**
	 * 源码文件目录
	 * @parameter expression="${project.build.sourceDirectory}"
	 * @required
	 * @readonly
	 */
	private File sourceDirectory;
	 
	/**
	 * 资源文件目录
	 * @parameter expression="${project.build.resources}"
	 * @required
	 * @readonly
	 */
	private List<Resource> resources;
	
}
