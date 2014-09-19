package com.brevy.batch.core.jobs.sample.reader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.brevy.batch.core.joblaunchdetails.support.reader.CustomPagingItemReader;
import com.brevy.batch.core.jobs.sample.dao.SampleDao;
import com.brevy.batch.core.jobs.sample.model.TestA;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @Description 分页reader
 * @author caobin
 * @date 2013-9-23
 * @version 1.0
 */
public class SamplePagingReader extends CustomPagingItemReader<List<TestA>> {

	@Override
	protected List<TestA> execute() {
		log.info("===========================================> {}", new Object[]{pDate});
		
		PageBounds pageBounds = new PageBounds(this.getPage() + 1, this.getPageSize());
		PageList<TestA> lstTestA = sampleDao.queryPaged(pageBounds);
		log.info(">>>> Sample paging reader: count = {}", new Object[]{lstTestA.getPaginator().getTotalCount()});
		return lstTestA;
	}


	@Autowired
	private SampleDao sampleDao;
	
	@Value("#{jobParameters['date']}")
	private String pDate;
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
