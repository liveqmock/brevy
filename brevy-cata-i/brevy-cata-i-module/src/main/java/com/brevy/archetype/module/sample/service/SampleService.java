package com.brevy.archetype.module.sample.service;

import java.util.List;

import com.brevy.archetype.module.sample.model.SampleCity;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @Description 样例Service
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
public interface SampleService {
	/**
	 * @Description 获取所有城市
	 * @return
	 * @author caobin
	 */
	List<SampleCity> getAllCities();
	
	/**
	 * @Description 分页获取城市
	 * @param start 起始页
	 * @param limit 每页总数
	 * @return
	 * @author caobin
	 */
	PageList<SampleCity> getCitiesPaged(int start, int limit);
	
	/**
	 * @Description 获取所有城市总数
	 * @return
	 * @author caobin
	 */
	int getAllCitiesCount();
	
	/**
	 * @Description 通过主键获取一级市
	 * @return
	 * @author caobin
	 */
	List<SampleCity> getLv1City();
	
	/**
	 * @Description 通过主键获取二级市
	 * @param id
	 * @return
	 * @author caobin
	 */
	List<SampleCity> getLv2City(long id);
	
	/**
	 * @Description 通过主键获取城市名称
	 * @param id
	 * @return
	 * @author caobin
	 */
	SampleCity getCityById(long id);
	
	
	/**
	 * @Description 添加新城市
	 * @param sampleCity
	 * @author caobin
	 */
	void addCity(SampleCity sampleCity);
	
	
	/**
	 * @Description 移除城市
	 * @param id
	 * @author caobin
	 */
	void removeCity(long id);
}
