package com.brevy.batch.core.job;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.brevy.batch.core.joblaunchdetails.SimpleJobLaunchDetails;
import com.brevy.batch.core.test.support.AbstractSpringContextTestSupport;

/**
 * @Description 
 * @author caobin
 * @date 2013-10-8
 * @version 1.0
 */
public class SampleJobTestcase extends AbstractSpringContextTestSupport {

	@Test
	public void doSampleJob(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("randomKey", UUID.randomUUID().toString());
		sampleJobLauncher.executeJobs(params);
	}
	
	@Resource
	private SimpleJobLaunchDetails sampleJobLauncher;
}
