package com.brevy.core.shiro.dao;

import com.brevy.core.shiro.model.ApUserSingle;
import com.brevy.core.support.annotation.MyBatisRepository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @description 用户DAO
 * @author caobin
 * @date 2014年12月26日
 */
@MyBatisRepository
public interface ApUserMapper {


	/**
	 * @description 分页查询用户列表
	 * @param pageBounds
	 * @return
	 * @author caobin
	 */
	PageList<ApUserSingle> findAll(PageBounds pageBounds);	
	
	/**
	 * @Description 关键字分页检索用户
	 * @param keyword 关键字
	 * @param pageBounds
	 * @return
	 * @author caobin
	 */
	PageList<ApUserSingle> searchByKeyword(String keyword, PageBounds pageBounds);
}
