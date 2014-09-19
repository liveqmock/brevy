package com.brevy.archetype.module.sample.model;

import com.brevy.archetype.support.model.BaseModel;

/**
 * @Description 样例Model-SampleCity
 * @author caobin
 * @date 2014-8-22
 * @version 1.0
 */
public class SampleCity extends BaseModel {

	private static final long serialVersionUID = -9029733312744005324L;
	
	private long id;
	private String cityName;
	private long parentCity;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the parentCity
	 */
	public long getParentCity() {
		return parentCity;
	}
	/**
	 * @param parentCity the parentCity to set
	 */
	public void setParentCity(long parentCity) {
		this.parentCity = parentCity;
	}
}
