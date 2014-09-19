package com.brevy.batch.core.jobs.sample.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.brevy.batch.core.jobs.sample.dao.SampleDao;
import com.brevy.batch.core.jobs.sample.model.TestA;

public class SampleTasklet implements Tasklet {
	
	@Autowired
	private SampleDao sampleDao;
	
	
	@Override
	public RepeatStatus execute(StepContribution stepcontribution,
			ChunkContext chunkcontext) throws Exception {
		
		log.info(">>>> Executing sampleTasklet.");
		
		TestA testA = null;
		
		int count = sampleDao.countTestA();
		
		for(int i = count + 0; i < count + 20; i++){
			testA = new TestA();
			testA.setId("ID" + i);
			testA.setF1("F1_" + i);
			testA.setF2("F2_" + i);
			sampleDao.initTestA(testA);
		}
			
		
		log.info(">>>> Table TestA was initialized.");
		
		return RepeatStatus.FINISHED;
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass()); 

}
