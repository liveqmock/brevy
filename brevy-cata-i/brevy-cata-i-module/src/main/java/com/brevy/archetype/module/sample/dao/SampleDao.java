package com.brevy.archetype.module.sample.dao;

import java.util.List;
import java.util.Map;

import com.brevy.archetype.module.sample.model.SampleCity;
import com.brevy.archetype.support.mybatis.msi.MyBatisRepository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * @Description 样例DAO
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
@MyBatisRepository
public interface SampleDao {
	
	/**
	 * @Description 查询所有城市
	 * @return
	 * @author caobin
	 */
	List<SampleCity> queryAllCities();
	
	/**
	 * @Description 分页查询所有城市
	 * @param pageBounds
	 * @return
	 * @author caobin
	 */
	PageList<SampleCity> queryCitiesPaged(PageBounds pageBounds);
	
	/**
	 * @Description 查询所有城市总条数
	 * @return
	 * @author caobin
	 */
	int queryAllCitiesCount();
	
	/**
	 * @Description 通过主键查询一级市
	 * @return
	 * @author caobin
	 */
	List<SampleCity> queryLv1City();
	
	/**
	 * @Description 通过主键查询二级市
	 * @param id
	 * @return
	 * @author caobin
	 */
	List<SampleCity> queryLv2City(long id);
	
	/**
	 * @Description 通过主键查询城市名称
	 * @param id
	 * @return
	 * @author caobin
	 */
	SampleCity queryCityById(long id);
	
	
	/**
	 * @Description 增加新城市
	 * @param sampleCity
	 * @author caobin
	 */
	void insertCity(SampleCity sampleCity);
	
	
	/**
	 * @Description 通过主键删除城市
	 * @param id
	 * @author caobin
	 */
	void removeCity(long id);
	
}
