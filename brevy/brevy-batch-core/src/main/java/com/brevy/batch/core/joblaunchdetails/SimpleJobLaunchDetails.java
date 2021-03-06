package com.brevy.batch.core.joblaunchdetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.JvmSystemExiter;
import org.springframework.batch.core.launch.support.SystemExiter;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.brevy.batch.core.joblaunchdetails.support.JobLaunchDetail;
import com.brevy.batch.core.joblaunchdetails.support.JobLaunchPolicy;

/**
 * @Description 简易JOB加载器
 * @author caobin
 * @date 2013-9-23
 * @version 1.0
 */
public class SimpleJobLaunchDetails implements ApplicationContextAware, InitializingBean {
	
	/**
	 * @Description 执行作业
	 * @param jobParams 作业参数
	 * @author caobin
	 */
	public void executeJobs(Map<String, String> jobParams){		
		if(jobParams == null){
			jobParams = new HashMap<String, String>();
		}
		jobParams.put("JOB_RUN_DATE", DateTime.now().toString("yyyy-MM-dd"));
		
		log.info(">>>> Starting to execute jobs (count: {})", new Object[]{jobDetails.size()});
		
		JobParameters jobParameters = null;
		JobExecution jobExecution = null;
		
		for(JobLaunchDetail jobDetail : jobDetails){
			log.info(">>>> Starting to execute job : {} ", new Object[]{jobDetail.getJobName()});
			//获取当前作业
			Job currentJob = fetchJob(jobDetail.getJobName());
			if(currentJob != null){
				JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
				for(Map.Entry<String, String> jobParam : jobParams.entrySet()){
					jobParametersBuilder.addString(jobParam.getKey(), jobParam.getValue());
				}
				jobParameters = jobParametersBuilder.toJobParameters();

				
				//判断相同JobParameter的Job是否已经成功完成过，完成过则不处理
				//JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobDetail.getJobName(), jobParameters);
				/*
				if(lastJobExecution != null && lastJobExecution.getStatus() == BatchStatus.COMPLETED){
					log.info(">>>> Job named [{}] with parameters [{}] has been executed successfully.", 
							new Object[]{jobDetail.getJobName(), jobParameters.toString()});
					continue;
				}*/
				
				//判断相同JobParameter的Job是否存在过，如果该Job状态失败，但是为不可restart的Job，那么该类Job也被忽略
				/*if(lastJobExecution != null && lastJobExecution.getStatus() != BatchStatus.COMPLETED && !currentJob.isRestartable()){
					log.info(">>>>> Job(unrestartable) named [{}] with parameter [{}] has been executed unsuccessfully.", 
							new Object[]{jobDetail.getJobName(), jobParameters.toString()});
					continue;
				}*/
				
				try {
					jobExecution = jobLauncher.run(currentJob, jobParameters);
				} catch (Exception e) {
					log.error("Job [{}] executing failed due to exceptions: {}", new Object[]{jobDetail.getJobName(), e});
					if(jobIgnore(jobDetail, jobLaunchPolicy)){
						log.info("Uncritical job [{}] was ignored.", new Object[]{jobDetail.getJobName()});
						continue;
					}else{
						//systemExiter.exit(1);
					}
				} 
				
				if(jobExecution.getStatus() != BatchStatus.COMPLETED){
					log.error("Job [{}] executing failed due to exceptions: {}", new Object[]{jobDetail.getJobName(), jobExecution.getAllFailureExceptions()});		
					//systemExiter.exit(1);
				}
				
				log.info("Job [{}] was completed.", new Object[]{jobDetail.getJobName()});
			} else if (currentJob == null && jobIgnore(jobDetail, jobLaunchPolicy)) {
				log.info("Uncritical job [{}] was ignored.", new Object[]{jobDetail.getJobName()});
				continue;
			} else {
				log.error("Current Job [{}] is null.", new Object[]{jobDetail.getJobName()});
			}
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	/**
	 * @Description 通过Job的bean name获取Job
	 * @param jobName job的bean name
	 * @return
	 * @author caobin
	 */
	protected Job fetchJob(String jobName) {
		Object obj = context.getBean(jobName);
		return obj instanceof Job ? (Job)obj : null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(jobLauncher, "jobLauncher is required");
		Assert.notNull(jobRepository, "jobRepository is required");
		Assert.notNull(jobDetails, "jobDetails is required");
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContext context;

	private JobLauncher jobLauncher;
	
	private JobRepository jobRepository;
	
	private List<JobLaunchDetail> jobDetails;
	
	//系统返回码
	private static SystemExiter systemExiter = new JvmSystemExiter();

	/**
	 * @param jobLauncher the jobLauncher to set
	 */
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	/**
	 * @param jobRepository the jobRepository to set
	 */
	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	/**
	 * @param jobDetails the jobDetails to set
	 */
	public void setJobDetails(List<JobLaunchDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}
	
	private JobLaunchPolicy jobLaunchPolicy = new JobLaunchPolicy(){
		@Override
		public boolean ignoreUncriticalJob() {
			return true;
		}
	};
	
	/**
	 * @Description 通过策略忽略作业
	 * @param jobDetail
	 * @param jobLaunchPolicy
	 * @return
	 * @author caobin
	 */
	private boolean jobIgnore(JobLaunchDetail jobDetail, JobLaunchPolicy jobLaunchPolicy){
		return !jobDetail.isCritical() && jobLaunchPolicy.ignoreUncriticalJob();
	}
	
}
