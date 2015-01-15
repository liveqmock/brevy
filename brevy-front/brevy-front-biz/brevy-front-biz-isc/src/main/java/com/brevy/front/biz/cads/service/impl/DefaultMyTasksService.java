package com.brevy.front.biz.cads.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.front.biz.cads.dao.CadCataskAttachDao;
import com.brevy.front.biz.cads.dao.CadCataskDao;
import com.brevy.front.biz.cads.dao.CadCataskHisDao;
import com.brevy.front.biz.cads.dao.CadDemandAttachDao;
import com.brevy.front.biz.cads.dao.CadDemandDao;
import com.brevy.front.biz.cads.dao.CadDemandHisDao;
import com.brevy.front.biz.cads.dao.CadGdAttachDao;
import com.brevy.front.biz.cads.dao.CadGdDao;
import com.brevy.front.biz.cads.dao.CadGdHisDao;
import com.brevy.front.biz.cads.dao.CadRefDeptDemandDao;
import com.brevy.front.biz.cads.dao.CadRefDeptGdDao;
import com.brevy.front.biz.cads.model.CadCatask;
import com.brevy.front.biz.cads.model.CadCataskAttach;
import com.brevy.front.biz.cads.model.CadCataskHis;
import com.brevy.front.biz.cads.model.CadDemand;
import com.brevy.front.biz.cads.model.CadDemandAttach;
import com.brevy.front.biz.cads.model.CadDemandHis;
import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadGdAttach;
import com.brevy.front.biz.cads.model.CadGdHis;
import com.brevy.front.biz.cads.model.CadRefDeptDemand;
import com.brevy.front.biz.cads.model.CadRefDeptDemandPK;
import com.brevy.front.biz.cads.model.CadRefDeptGd;
import com.brevy.front.biz.cads.model.CadRefDeptGdPK;
import com.brevy.front.biz.cads.service.MyTasksService;

@Service
public class DefaultMyTasksService implements MyTasksService {
	
	@Autowired
	private CadGdDao cadGdDao;
	
	@Autowired
	private CadRefDeptGdDao cadRefDeptGdDao;
	
	@Autowired
	private CadGdAttachDao cadGdAttachDao;
	
	@Autowired
	private CadGdHisDao cadGdHisDao;
	
	@Autowired
	private CadDemandDao cadDemandDao;
	
	@Autowired
	private CadRefDeptDemandDao cadRefDeptDemandDao;
	
	@Autowired
	private CadDemandAttachDao cadDemandAttachDao;
	
	@Autowired
	private CadDemandHisDao cadDemandHisDao;
	
	@Autowired
	private CadCataskDao cadCataskDao;
	
	@Autowired
	private CadCataskAttachDao cadCataskAttachDao;
	
	@Autowired
	private CadCataskHisDao cadCataskHisDao;

	@Override
	public Page<CadGd> findGDsRefDept(String keyword, Pageable pageable) {
		return cadGdDao.searchByKeyword(ShiroUtils.getCurrentUser().getDeptId(),"%".concat(keyword).concat("%"), pageable);
	}
	
	@Override
	public Page<CadGd> findAllGDs(String keyword, Pageable pageable) {
		return cadGdDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}


	@Transactional
	@Override
	public CadGd saveOrUpdateCadGd(CadGd cadGd) {
		CadGd savedGd = null;
		if(cadGd.getId() > 0){//update part of GD
			savedGd = cadGdDao.findOne(cadGd.getId());
			BeanUtils.copyProperties(cadGd, savedGd, new String[]{
				"id", "attachType","briefName","estimateJob","execType",
				"implTeam", "implTeamIds", "name", "pmName", "pmNameIds",
				"preCond","preCondIds","priority","recvDate","requireFinishTime",
				"startDate", "type", "assignToDept", "gdType", "prjType"
			});
			cadGdDao.save(savedGd);
		}else{
			savedGd = cadGdDao.save(cadGd);
			//部门分配
			long[] assignedDepts = savedGd.getAssignToDept();
			for(long assignedDept : assignedDepts){
				CadRefDeptGdPK pk = new CadRefDeptGdPK();
				pk.setGdId(savedGd.getId());
				pk.setDeptId(assignedDept);
				CadRefDeptGd cadRefDeptGd = new CadRefDeptGd();
				cadRefDeptGd.setId(pk);
				cadRefDeptGdDao.save(cadRefDeptGd);
			}	
			
		}
		
		return savedGd;
	}

	@Transactional
	@Override
	public void addGdAttach(long cadGdId, String attachType, String attachPath) {
		//获取工单对象
		CadGd cadGd = cadGdDao.findOne(cadGdId);
		if(cadGd != null){
			cadGd.setAttachType(mergeAttachType(cadGd.getAttachType(), attachType));			
			cadGdDao.save(cadGd);
		}
		//保存工单路径
		CadGdAttach cadGdAttach = new CadGdAttach();
		cadGdAttach.setGdId(cadGdId);
		cadGdAttach.setPath(attachPath);
		cadGdAttachDao.save(cadGdAttach);
	}
	
	

	@Override
	public Iterable<CadGdAttach> findAllGDAttachments(long gdId) {
		return cadGdAttachDao.findByGdId(gdId);
	}

	@Override
	public CadGdAttach findGdAttachment(long attachId) {
		return cadGdAttachDao.findOne(attachId);
	}

	@Transactional
	@Override
	public void gdArchive(long gdId) {
		//将主表记录移至归档表
		CadGd cadGd = cadGdDao.findOne(gdId);
		CadGdHis cadGdHis = new CadGdHis();
		BeanUtils.copyProperties(cadGd, cadGdHis);
		cadGdHisDao.save(cadGdHis);		
		
		//删除主表记录及其关联关系的记录
		cadGdDao.delete(cadGd);
		cadGdAttachDao.deleteByGdId(gdId);
		cadRefDeptGdDao.deleteByGdId(gdId);
	}	

	@Override
	public Page<CadDemand> findDemandsRefDept(String keyword, Pageable pageable) {
		return cadDemandDao.searchByKeyword(ShiroUtils.getCurrentUser().getDeptId(),"%".concat(keyword).concat("%"), pageable);
	}
	
	@Override
	public Page<CadDemand> findAllDemands(String keyword, Pageable pageable) {
		return cadDemandDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}


	@Transactional
	@Override
	public CadDemand saveOrUpdateCadDemand(CadDemand cadDemand) {	
		CadDemand savedDemand = null;
		if(cadDemand.getId() > 0){//update part of Demand
			savedDemand = cadDemandDao.findOne(cadDemand.getId());
			BeanUtils.copyProperties(cadDemand, savedDemand, new String[]{
				"id", "attachType", "assignToDept","implTeam","implTeamIds",
				"preCond","preCondIds","priority","prjName",
				"recvDate","requireFinishTime"
			});
			cadDemandDao.save(savedDemand);
		}else{
			savedDemand = cadDemandDao.save(cadDemand);
			//部门分配
			long[] assignedDepts = savedDemand.getAssignToDept();
			for(long assignedDept : assignedDepts){
				CadRefDeptDemandPK pk = new CadRefDeptDemandPK();
				pk.setDemandId(savedDemand.getId());
				pk.setDeptId(assignedDept);
				CadRefDeptDemand cadRefDeptDemand = new CadRefDeptDemand();
				cadRefDeptDemand.setId(pk);
				cadRefDeptDemandDao.save(cadRefDeptDemand);
			}				
		}		
		return savedDemand;
	}

	@Transactional
	@Override
	public void addDemandAttach(long cadDemandId, String attachType, String attachPath) {
		//获取需求评估单对象
		CadDemand cadDemand = cadDemandDao.findOne(cadDemandId);
		if(cadDemand != null){
			cadDemand.setAttachType(mergeAttachType(cadDemand.getAttachType(), attachType));			
			cadDemandDao.save(cadDemand);
		}
		//保存需求评估单路径
		CadDemandAttach cadDemandAttach = new CadDemandAttach();
		cadDemandAttach.setDemandId(cadDemandId);
		cadDemandAttach.setPath(attachPath);
		cadDemandAttachDao.save(cadDemandAttach);
	}


	@Override
	public Iterable<CadDemandAttach> findAllDemandAttachments(long demandId) {
		return cadDemandAttachDao.findByDemandId(demandId);
	}

	@Override
	public CadDemandAttach findDemandAttachment(long attachId) {
		return cadDemandAttachDao.findOne(attachId);
	}

	@Transactional
	@Override
	public void demandArchive(long demandId) {
		//将主表记录移至归档表
		CadDemand cadDemand = cadDemandDao.findOne(demandId);
		CadDemandHis cadDemandHis = new CadDemandHis();
		BeanUtils.copyProperties(cadDemand, cadDemandHis);
		cadDemandHisDao.save(cadDemandHis);		
		
		//删除主表记录及其关联关系的记录
		cadDemandDao.delete(cadDemand);
		cadDemandAttachDao.deleteByDemandId(demandId);
		cadRefDeptDemandDao.deleteByDemandId(demandId);
	}

	@Override
	public Page<CadGdHis> findAllArchivedGDs(String keyword, Pageable pageable) {
		return cadGdHisDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

	@Override
	public Page<CadDemandHis> findAllArchivedDemands(String keyword,
			Pageable pageable) {
		return cadDemandHisDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

	@Override
	public Page<CadCatask> findAllCatasks(String keyword, Pageable pageable) {
		return cadCataskDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

	@Transactional
	@Override
	public CadCatask saveOrUpdateCatask(CadCatask cadCatask) {
		CadCatask savedCadCatask = null;
		if(cadCatask.getId() > 0){//update part of Catask
			savedCadCatask = cadCataskDao.findOne(cadCatask.getId());
			BeanUtils.copyProperties(cadCatask, savedCadCatask, new String[]{
				"id", "title", "reqFinishDate", "importance", "source", 
				"category", "attachType", "result"
			});
			cadCataskDao.save(savedCadCatask);
		}else{
			savedCadCatask = cadCataskDao.save(cadCatask);
		}	
		return savedCadCatask;
	}

	@Transactional
	@Override
	public void addCataskAttach(long cataskId, String attachType,
			String attachPath) {
		//获取综合任务管理对象
		CadCatask cadCatask = cadCataskDao.findOne(cataskId);
		if(cadCatask != null){		
			cadCatask.setAttachType(mergeAttachType(cadCatask.getAttachType(), attachType));				
			cadCataskDao.save(cadCatask);
		}
		//保存综合任务管理附件路径
		CadCataskAttach cadCataskAttach = new CadCataskAttach();
		cadCataskAttach.setCataskId(cataskId);
		cadCataskAttach.setPath(attachPath);
		cadCataskAttachDao.save(cadCataskAttach);
	}

	@Override
	public Iterable<CadCataskAttach> findAllCataskAttachments(long cataskId) {
		return cadCataskAttachDao.findByCataskId(cataskId);
	}

	@Override
	public CadCataskAttach findCataskAttachment(long attachId) {
		return cadCataskAttachDao.findOne(attachId);
	}

	@Transactional
	@Override
	public void cataskArchive(long cataskId) {
		//将主表记录移至归档表
		CadCatask cadCatask = cadCataskDao.findOne(cataskId);
		CadCataskHis cadCataskHis = new CadCataskHis();
		BeanUtils.copyProperties(cadCatask, cadCataskHis);
		cadCataskHisDao.save(cadCataskHis);		
		
		//删除主表记录及其关联关系的记录
		cadCataskDao.delete(cadCatask);
		cadCataskAttachDao.deleteByCataskId(cataskId);
	}

	@Override
	public Page<CadCataskHis> findAllArchivedCatasks(String keyword,
			Pageable pageable) {
		return cadCataskHisDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}	

	
	
	/**
	 * @description 合并附件类型
	 * @param originalAttachType 原始附件类型
	 * @param targetAttachType 目标附件类型
	 * @return
	 * @author caobin
	 */
	private String mergeAttachType(String originalAttachType, String targetAttachType){
		if(StringUtils.isEmpty(targetAttachType)){
			return originalAttachType;
		}else{
			for(String type : targetAttachType.split("\\,")){
				if(!StringUtils.contains(originalAttachType, type.toLowerCase())){
					originalAttachType = StringUtils.isEmpty(originalAttachType) ? 
							StringUtils.defaultString(originalAttachType).concat(type.toLowerCase()) : originalAttachType.concat(",").concat(type.toLowerCase());
				}
			}
			return originalAttachType;
		}	
	}

}
