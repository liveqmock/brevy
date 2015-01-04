package com.brevy.front.biz.cads.dao;

import org.springframework.data.repository.CrudRepository;

import com.brevy.front.biz.cads.model.CadRefDeptGd;
import com.brevy.front.biz.cads.model.CadRefDeptGdPK;

/**
 * @description 部门工单关联DAO
 * @author caobin
 * @date 2015年1月4日
 */
public interface CadRefDeptGdDao extends CrudRepository<CadRefDeptGd, CadRefDeptGdPK> {

}
