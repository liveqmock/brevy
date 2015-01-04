package com.brevy.front.biz.cads.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.front.biz.cads.dao.CadGdAttachDao;
import com.brevy.front.biz.cads.dao.CadGdDao;
import com.brevy.front.biz.cads.dao.CadRefDeptGdDao;
import com.brevy.front.biz.cads.model.CadGd;
import com.brevy.front.biz.cads.model.CadGdAttach;
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
		CadGd savedGd = cadGdDao.save(cadGd);
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
		return savedGd;
	}

	@Transactional
	@Override
	public void addAttach(long cadGdId, String attachType, String attachPath) {
		//获取工单对象
		CadGd cadGd = cadGdDao.findOne(cadGdId);
		if(cadGd != null){
			cadGd.setAttachType(handleAttachType(cadGd.getAttachType(), attachType));			
			cadGdDao.save(cadGd);
		}
		//保存工单路径
		CadGdAttach cadGdAttach = new CadGdAttach();
		cadGdAttach.setGdId(cadGdId);
		cadGdAttach.setPath(attachPath);
		cadGdAttachDao.save(cadGdAttach);
	}
	
	/**
	 * @description 处理附件类型
	 * @param targetAttachType 目标附件类型
	 * @param attachType 附件类型
	 * @return
	 * @author caobin
	 */
	private String handleAttachType(String targetAttachType, String attachType){
		if(StringUtils.isEmpty(targetAttachType)){
			return attachType;
		}else{
			if(!targetAttachType.contains(attachType.toLowerCase())){
				targetAttachType = targetAttachType.concat(",").concat(attachType.toLowerCase());
			} 
			return targetAttachType;
		}
	}	
}
