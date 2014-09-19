package com.brevy.batch.core.jobs.sample.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.brevy.batch.core.jobs.sample.model.TestA;

public class SampleWriter implements ItemWriter<TestA> {

	@Override
	public void write(List<? extends TestA> items) throws Exception {
		log.info(">>>> Sample writer: {}", new Object[]{items});
		
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
