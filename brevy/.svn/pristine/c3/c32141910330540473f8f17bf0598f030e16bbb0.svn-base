package com.brevy.batch.core.jobs.sample.dao;

import java.util.List;

import com.brevy.batch.core.jobs.sample.model.TestA;
import com.brevy.batch.support.mybatis.MyBatisRepository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@MyBatisRepository
public interface SampleDao {
	
	/**
	 * @Description 初始化TestA记录
	 * @param testA
	 * @author caobin
	 */
	void initTestA(TestA testA);
	
	
	/**
	 * @Description 计算TestA中的总记录条数
	 * @return
	 * @author caobin
	 */
	int countTestA();
	
	/**
	 * @Description 分页查询TestA表中数据
	 * @param pageBounds 
	 * @return
	 * @author caobin
	 */
	PageList<TestA> queryPaged(PageBounds pageBounds);
}
