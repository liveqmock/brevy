package com.brevy.batch.core.joblaunchdetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @Description Main Launcher
 * @author caobin
 * @date 2013-10-9
 * @version 1.0
 */
public class SimpleJobLaunchMain {

	/**
	 * @Description
	 * @param args
	 * @throws IOException
	 * @author caobin
	 * @throws ParseException 
	 */
	public static void main(String[] args)  {
		
		
		try {
			
			CommandLineParser cliParser = new BasicParser();
			
			Options options = new Options();
			options.addOption("h", "help", false, "Help info.");
			options.addOption("p", "profile", true, "Spring profile.");
			options.addOption("j", "job", true, "Launch job using job name.");
			options.addOption("d", "date", true, "Date info.");
			options.addOption("i", "identity", true, "Unique identity code.");
			
			CommandLine cli = cliParser.parse(options, args);
			
			String profileValue = null;
			String jobValue = null;
			String dateValue = null;
			String identityValue = null;
			
			
			if(cli.hasOption("h")){
				StringBuilder cliInfo = new StringBuilder();
				cliInfo.append("Usage:\n");
				cliInfo.append("\t-h --help\t\t\t帮助信息\n");
				cliInfo.append("\t-j --job <value>\t\t作业(任务)名称\n");
				cliInfo.append("\t-d --date <value>\t\t指定日期(yyyy-MM-dd)\n");
				cliInfo.append("\t-i --identity <value>\t\t唯一参数标识符(重启任务时作为参数使用)\n");
				System.out.println(cliInfo.toString());
				System.exit(0);
			}
			
			if(cli.hasOption("p")){
				profileValue = cli.getOptionValue("p");
			}
			
			if(cli.hasOption("j")){
				jobValue = cli.getOptionValue("j");
			}
					
			if(cli.hasOption("d")){
				dateValue = cli.getOptionValue("d");
				if(!dateValue.matches("^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$")){
					throw new RuntimeException(String.format("illegal date pattern: %s.", dateValue));
				};
			}
			
			if(cli.hasOption("i")){
				identityValue = cli.getOptionValue("i");
			}

			
			SimpleJobLaunchDetails jobLauncher = getContext(profileValue).getBean(jobValue, SimpleJobLaunchDetails.class);
			
			Map<String, String> jobParams = new HashMap<String, String>();
			jobParams.put("date", dateValue);
			jobParams.put("identity", identityValue);
			
			jobLauncher.executeJobs(jobParams);
			
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * @Description 
	 * @param profile
	 * @return
	 * @author caobin
	 */
	private static ClassPathXmlApplicationContext getContext(String profile){
		if(StringUtils.isBlank(profile)){
			System.out.println("\nCannot find actived spring profile, using dev.");
			profile = "dev";
		}else{
			System.out.println(String.format("Using actived spring profile: %s", profile));
		}
		System.setProperty("spring.profiles.active", profile);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/batch-core/spring-env.xml",
				"classpath*:/batch-core/spring-batch-*.xml",
				"classpath*:/batch-core/jobs/*-job.xml"
		);
		context.refresh();
		return context;	
	}
}
