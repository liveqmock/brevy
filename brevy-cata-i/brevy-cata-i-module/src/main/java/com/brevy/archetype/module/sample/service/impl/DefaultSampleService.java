package com.brevy.archetype.module.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.archetype.module.sample.dao.SampleDao;
import com.brevy.archetype.module.sample.model.SampleCity;
import com.brevy.archetype.module.sample.service.SampleService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @Description 样例Service默认实现
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
@Service
public class DefaultSampleService implements SampleService {

	@Autowired
	private SampleDao sampleDao;
	
	@Override
	public List<SampleCity> getAllCities() {
		return sampleDao.queryAllCities();
	}

	@Override
	public PageList<SampleCity> getCitiesPaged(int start, int limit) {
		return sampleDao.queryCitiesPaged(new PageBounds(start, limit));
	}

	@Override
	public int getAllCitiesCount() {
		return sampleDao.queryAllCitiesCount();
	}

	@Override
	public List<SampleCity> getLv1City() {
		return sampleDao.queryLv1City();
	}

	@Override
	public List<SampleCity> getLv2City(long id) {
		return sampleDao.queryLv2City(id);
	}

	@Override
	public SampleCity getCityById(long id) {
		return sampleDao.queryCityById(id);
	}

	@Transactional
	@Override
	public void addCity(SampleCity sampleCity) {
		sampleDao.insertCity(sampleCity);
	}

	@Transactional
	@Override
	public void removeCity(long id) {
		sampleDao.removeCity(id);
	}
	
}
