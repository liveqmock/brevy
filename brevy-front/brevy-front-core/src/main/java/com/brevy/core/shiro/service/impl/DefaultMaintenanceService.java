package com.brevy.core.shiro.service.impl;

import static com.brevy.core.shiro.ShiroConstants.FUNC_MENU_LEAF;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brevy.core.shiro.dao.ApAccessPermDao;
import com.brevy.core.shiro.dao.ApApplicationDao;
import com.brevy.core.shiro.dao.ApGroupDao;
import com.brevy.core.shiro.dao.ApGroupSingleDao;
import com.brevy.core.shiro.dao.ApMenuDao;
import com.brevy.core.shiro.dao.ApOperPermDao;
import com.brevy.core.shiro.dao.ApRefGroupRoleDao;
import com.brevy.core.shiro.dao.ApRefRoleAccessPermDao;
import com.brevy.core.shiro.dao.ApRefRoleMenuDao;
import com.brevy.core.shiro.dao.ApRefRoleOperPermDao;
import com.brevy.core.shiro.dao.ApRefUserApplicationDao;
import com.brevy.core.shiro.dao.ApRoleDao;
import com.brevy.core.shiro.dao.ApRoleSingleDao;
import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.model.ApGroupSingle;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApOperPerm;
import com.brevy.core.shiro.model.ApRefGroupRole;
import com.brevy.core.shiro.model.ApRefGroupRolePK;
import com.brevy.core.shiro.model.ApRefRoleAccessPerm;
import com.brevy.core.shiro.model.ApRefRoleAccessPermPK;
import com.brevy.core.shiro.model.ApRefRoleMenu;
import com.brevy.core.shiro.model.ApRefRoleMenuPK;
import com.brevy.core.shiro.model.ApRefRoleOperPerm;
import com.brevy.core.shiro.model.ApRefRoleOperPermPK;
import com.brevy.core.shiro.model.ApRole;
import com.brevy.core.shiro.model.ApRoleSingle;
import com.brevy.core.shiro.service.MaintenanceService;
import com.brevy.core.shiro.util.ShiroUtils;

/**
 * @Description 
 * @author caobin
 * @date 2013-12-31
 * @version 1.0
 */
@Service
public class DefaultMaintenanceService implements MaintenanceService {
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApMenuDao apMenuDao;

	@Autowired
	private ApAccessPermDao apAccessPermDao;
	
	@Autowired
	private ApRefRoleMenuDao apRefRoleMenuDao;
	
	@Autowired
	private ApRefRoleAccessPermDao apRefRoleAccessPermDao;
	
	@Autowired
	private ApRefRoleOperPermDao apRefRoleOperPermDao;
	
	@Autowired
	private ApRefGroupRoleDao apRefGroupRoleDao;

	@Autowired
	private ApOperPermDao apOperPermDao;

	@Autowired
	private ApRoleDao apRoleDao;
	
	@Autowired
	private ApRoleSingleDao apRoleSingleDao;
	
	@Autowired
	private ApGroupDao apGroupDao;
	
	@Autowired
	private ApGroupSingleDao apGroupSingleDao;
	
	@Autowired
	private ApApplicationDao apApplicationDao;
	
	@Autowired
	private ApRefUserApplicationDao apRefUserApplicationDao;
	
	@Autowired
	private AbstractShiroFilter abstractShiroFilter;


	@Override
	public List<ApMenu> findChildren(long appId, long parentId) {
		return apMenuDao.findByAppIdAndParentIdOrderBySortAsc(appId, parentId);
	}
	
	@Override
	public Collection<?> findChildren(final long appId, final long parentId, long roleId){
		ApRole apRole = apRoleDao.findOne(roleId);
		if(apRole == null)return null;
		Collection<?> c = CollectionUtils.select(apRole.getMenus(), new Predicate() {			
			@Override
			public boolean evaluate(Object object) {
				ApMenu apMenu = (ApMenu)object;
				return apMenu.getAppId() == appId && apMenu.getParentId() == parentId;
			}
		});
		Collections.sort((List)c, new Comparator<ApMenu>() {
			@Override
			public int compare(ApMenu o1, ApMenu o2) {
				return (int)(o1.getSort() - o2.getSort());
			}		
		});
		return c;

	}
	
	@Override
	public List<ApMenu> findParent(long appId, long parentId) {
		return apMenuDao.findByAppIdAndParentIdAndLeafOrderBySortAsc(appId, parentId, "0");
	}

	
	@Transactional
	@Override
	public void saveOrUpdateApMenu(ApMenu apMenu) {
		apMenuDao.save(apMenu);
	}
	
	@Transactional
	@Override
	public long updateAndRearrangeNodes(ApMenu apMenu) {
	
		//通过ID加载当前变更节点
		ApMenu targetApMenu = apMenuDao.findOne(apMenu.getId());
			
		//更新parentId、sort
		targetApMenu.setParentId(apMenu.getParentId());
		long originalSort = targetApMenu.getSort();
		targetApMenu.setSort(apMenu.getSort());
		apMenuDao.save(targetApMenu);		
		long newSort = targetApMenu.getSort();
		
		
		
		long sortMin = originalSort > newSort ? newSort : originalSort;
		long sortMax = originalSort > newSort ? originalSort : newSort;
		
		
		//重排大等于当前更新节点顺序号的节点
		//（移动后）同级重排
		log.info(">>> same-level resorting...");
		List<ApMenu> reArrangingApMenus = apMenuDao.findByAppIdAndParentIdAndIdNotAndSortBetweenOrderBySortAsc(
				targetApMenu.getAppId(), targetApMenu.getParentId(), targetApMenu.getId(), sortMin, sortMax
		);
		if(CollectionUtils.isNotEmpty(reArrangingApMenus)){
			for(ApMenu am : reArrangingApMenus){
				am.setSort(originalSort > newSort ? am.getSort() + 1 : am.getSort() - 1);
				apMenuDao.save(am);
			}
		}

		
		/** 注:跨级拖动重排有一定误操作风险，不允许操作 **/
		
		return apMenu.getParentId();
	}
	
	@Override
	public long countChildren(ApMenu apMenu) {
		return apMenuDao.countByAppIdAndParentId(apMenu.getAppId(), apMenu.getParentId());
	}

	@Transactional
	@Override
	public void delApMenuCascade(ApMenu apMenu) {
		apMenuDao.delete(apMenu.getId());
		apRefRoleMenuDao.deleteRelsByMenuId(apMenu.getId());
		//重排大等于当前更新节点顺序号的节点
		//（删除后）同级重排
		log.info(">>> same-level resorting...");
		List<ApMenu> reArrangingApMenus = apMenuDao.findByAppIdAndParentIdAndSortGreaterThanOrderBySortAsc(
				apMenu.getAppId(), apMenu.getParentId(), apMenu.getSort()
		);
		if(CollectionUtils.isNotEmpty(reArrangingApMenus)){
			for(ApMenu am : reArrangingApMenus){
				am.setSort(am.getSort() - 1);
				apMenuDao.save(am);
			}
		}
		//级联删除
		if(!StringUtils.equals(apMenu.getLeaf(), FUNC_MENU_LEAF)){
			delApMenus(apMenuDao.findByAppIdAndParentId(apMenu.getAppId(), apMenu.getId()));
		}
		
	}
	
	/**
	 * @Description 级联删除菜单
	 * @param apMenus
	 * @author caobin
	 */
	private void delApMenus(List<ApMenu> apMenus){
		if(CollectionUtils.isNotEmpty(apMenus)){
			for(ApMenu apMenu : apMenus){
				if(!StringUtils.equals(apMenu.getLeaf(), FUNC_MENU_LEAF)){
					apMenuDao.delete(apMenu.getId());
					apRefRoleMenuDao.deleteRelsByMenuId(apMenu.getId());
					delApMenus(apMenuDao.findByAppIdAndParentId(apMenu.getAppId(), apMenu.getId()));
				}else{
					apMenuDao.delete(apMenu.getId());
					apRefRoleMenuDao.deleteRelsByMenuId(apMenu.getId());
				}
			}
		}
	}
	
	@Override
	public Page<ApAccessPerm> findApAccessPerms(long appId, Pageable pageable) {
		return apAccessPermDao.findByAppId(appId, pageable);
	}

	@Override
	public Page<ApAccessPerm> searchApAccessPermsByKeyword(String keyword,
			long appId, Pageable pageable) {
		return apAccessPermDao.searchByKeyword("%".concat(keyword).concat("%"), appId, pageable);
	}
	
	@Transactional
	@Override
	public void saveOrUpdateApAccessPerm(ApAccessPerm apAccessPerm) {
		apAccessPermDao.save(apAccessPerm);
		ShiroUtils.reloadFilterChainDefinitions(apAccessPermDao.findByAppIdOrderBySortAsc(apAccessPerm.getAppId()), abstractShiroFilter);
	}

	@Override
	public boolean checkApAccessPermCode(String code) {
		return apAccessPermDao.findByCode(code) == null;
	}

	@Transactional
	@Override
	public void deleteApAccessPerm(Collection<Long> ids, long appId) {
		apAccessPermDao.deleteAccessPermByIds(ids);
		apRefRoleAccessPermDao.deleteRelsByAccessPermIds(ids);		
		ShiroUtils.reloadFilterChainDefinitions(apAccessPermDao.findByAppIdOrderBySortAsc(appId), abstractShiroFilter);
	}
	


	@Override
	public Page<ApOperPerm> findApOperPerms(long appId, Pageable pageable) {
		return apOperPermDao.findByAppId(appId, pageable);
	}

	@Override
	public Page<ApOperPerm> searchApOperPermsByKeyword(String keyword,
			long appId, Pageable pageable) {
		return apOperPermDao.searchByKeyword("%".concat(keyword).concat("%"), appId, pageable);
	}

	@Transactional
	@Override
	public void saveOrUpdateApOperPerm(ApOperPerm apOperPerm) {
		apOperPermDao.save(apOperPerm);
	}
	

	@Override
	public boolean checkApOperPermCode(String code) {
		return apOperPermDao.findByCode(code) == null;
	}

	@Transactional
	@Override
	public void deleteApOperPerm(Collection<Long> ids, long appId) {
		apOperPermDao.deleteOperPermByIds(ids);
		apRefRoleOperPermDao.deleteRelsByOperPermIds(ids);
	}
	
	
	@Override
	public Page<ApRoleSingle> findApRoles(long appId, Pageable pageable) {
		return apRoleSingleDao.findByAppId(appId, pageable);
	}

	@Override
	public Page<ApRoleSingle> searchApRolesByKeyword(String keyword, long appId,
			Pageable pageable) {
		return apRoleSingleDao.searchByKeyword("%".concat(keyword).concat("%"), appId, pageable);
	}

	@Transactional
	@Override
	public void saveOrUpdateApRoleSingle(ApRoleSingle apRole) {
		apRoleSingleDao.save(apRole);
	}

	@Override
	public boolean checkApRoleCode(String code) {
		return apRoleDao.findByCode(code) == null;
	}

	@Transactional
	@Override
	public void deleteApRole(Collection<Long> ids, long appId) {
		apRoleDao.deleteRoleByIds(ids);
		apRefRoleMenuDao.deleteRelsByRoldIds(ids);
		apRefRoleOperPermDao.deleteRelsByRoldIds(ids);
		apRefRoleAccessPermDao.deleteRelsByRoldIds(ids);		
	}

	@Override
	public List<ApMenu> findCandidateMenus(long appId, long parentId,
			long roleId) {
		return apMenuDao.findCandidateMenus(appId, parentId, roleId);
	}

	@Transactional
	@Override
	public void saveRoleRefMenu(long roleId, long menuId) {
		ApRefRoleMenu arm = new ApRefRoleMenu();
		ApRefRoleMenuPK pk = new ApRefRoleMenuPK();
		pk.setMenuId(menuId);
		pk.setRoleId(roleId);
		arm.setId(pk);
		apRefRoleMenuDao.save(arm);
	}

	@Transactional
	@Override
	public void delRoleRefMenu(long roleId, long menuId) {
		ApRefRoleMenu arm = new ApRefRoleMenu();
		ApRefRoleMenuPK pk = new ApRefRoleMenuPK();
		pk.setMenuId(menuId);
		pk.setRoleId(roleId);
		arm.setId(pk);
		apRefRoleMenuDao.delete(arm);	
		delRoleRefMenuCascade(arm);
	}
	

	/**
	 * @Description 级联删除角色菜单关系
	 * @param arm
	 * @author caobin
	 */
	private void delRoleRefMenuCascade(ApRefRoleMenu arm){
		ApMenu apMenu = apMenuDao.findOne(arm.getId().getMenuId());
		List<ApMenu> apMenus = apMenuDao.findByAppIdAndParentId(apMenu.getAppId(), apMenu.getId());
		if(CollectionUtils.isNotEmpty(apMenus)){
			log.debug(">>> deleting sub-reference of role and menu");
			for(ApMenu am : apMenus){
				ApRefRoleMenu sarm = new ApRefRoleMenu();
				ApRefRoleMenuPK pk = new ApRefRoleMenuPK();
				pk.setMenuId(am.getId());
				pk.setRoleId(arm.getId().getRoleId());
				sarm.setId(pk);
				apRefRoleMenuDao.delete(sarm);	
				delRoleRefMenuCascade(sarm);
			}
		}
	}

	@Override
	public Page<ApAccessPerm> findRoleRefAccessAuth(long roleId, String keyword, Pageable pageable) {
		return apRefRoleAccessPermDao.findRoleRefAccessAuth(roleId, "%".concat(keyword).concat("%"), pageable);
	}

	@Override
	public Page<ApAccessPerm> findCandidateAccessAuth(long appId, long roleId,
			String keyword, Pageable pageable) {
		return apRefRoleAccessPermDao.findCadidateRoleRefAccessAuth(appId, roleId, "%".concat(keyword).concat("%"), pageable);
	}

	@Transactional
	@Override
	public void saveRoleRefAccessAuth(long roleId, long accessPermId) {
		ApRefRoleAccessPerm arrap = new ApRefRoleAccessPerm();
		ApRefRoleAccessPermPK pk = new ApRefRoleAccessPermPK();
		pk.setAccessPermId(accessPermId);
		pk.setRoleId(roleId);
		arrap.setId(pk);
		apRefRoleAccessPermDao.save(arrap);
	}

	@Transactional
	@Override
	public void delRoleRefAccessAuth(long roleId, long accessPermId) {
		ApRefRoleAccessPermPK pk = new ApRefRoleAccessPermPK();
		pk.setAccessPermId(accessPermId);
		pk.setRoleId(roleId);
		apRefRoleAccessPermDao.delete(pk);
	}

	@Transactional
	@Override
	public void saveRoleRefAccessAuths(long roleId, String accessPermIds) {
		String[] arrayAccessPermId = accessPermIds.split("\\,");
		for(String accessPermId : arrayAccessPermId){
			saveRoleRefAccessAuth(roleId, Long.parseLong(accessPermId));
		}
	}

	@Transactional
	@Override
	public void delRoleRefAccessAuths(long roleId, String accessPermIds) {
		String[] arrayAccessPermId = accessPermIds.split("\\,");
		for(String accessPermId : arrayAccessPermId){
			delRoleRefAccessAuth(roleId, Long.parseLong(accessPermId));
		}
	}

	@Override
	public Page<ApOperPerm> findRoleRefOperAuth(long roleId, String keyword,
			Pageable pageable) {
		return apRefRoleOperPermDao.findRoleRefOperAuth(roleId, "%".concat(keyword).concat("%"), pageable);
	}

	@Override
	public Page<ApOperPerm> findCandidateOperAuth(long appId, long roleId,
			String keyword, Pageable pageable) {
		return apRefRoleOperPermDao.findCadidateRoleRefOperAuth(appId, roleId, "%".concat(keyword).concat("%"), pageable);
	}

	@Transactional
	@Override
	public void saveRoleRefOperAuth(long roleId, long operPermId) {
		ApRefRoleOperPerm arrap = new ApRefRoleOperPerm();
		ApRefRoleOperPermPK pk = new ApRefRoleOperPermPK();
		pk.setOperPermId(operPermId);
		pk.setRoleId(roleId);
		arrap.setId(pk);
		apRefRoleOperPermDao.save(arrap);
	}

	@Transactional
	@Override
	public void delRoleRefOperAuth(long roleId, long operPermId) {
		ApRefRoleOperPermPK pk = new ApRefRoleOperPermPK();
		pk.setOperPermId(operPermId);
		pk.setRoleId(roleId);
		apRefRoleOperPermDao.delete(pk);
	}

	@Transactional
	@Override
	public void saveRoleRefOperAuths(long roleId, String operPermIds) {
		String[] arrayOperPermId = operPermIds.split("\\,");
		for(String operPermId : arrayOperPermId){
			saveRoleRefOperAuth(roleId, Long.parseLong(operPermId));
		}
	}

	@Transactional
	@Override
	public void delRoleRefOperAuths(long roleId, String operPermIds) {
		String[] arrayOperPermId = operPermIds.split("\\,");
		for(String operPermId : arrayOperPermId){
			delRoleRefOperAuth(roleId, Long.parseLong(operPermId));
		}
	}
	
	@Transactional
	@Override
	public void saveOrUpdateApGroupSingle(ApGroupSingle apGroup) {		
		apGroupSingleDao.save(apGroup);
	}

	@Override
	public boolean checkApGroupCode(String code) {
		return apGroupSingleDao.findByCode(code) == null;
	}
	
	@Transactional
	@Override
	public void deleteApGroup(Collection<Long> ids, long appId) {
		apGroupDao.deleteGroupByIds(ids);
		apRefGroupRoleDao.deleteRelsByGroupIds(ids);		
	}
	

	@Override
	public Page<ApGroupSingle> findApGroups(long appId, Pageable pageable) {
		return apGroupSingleDao.findByAppId(appId, pageable);
	}
	

	@Override
	public Page<ApGroupSingle> searchApGroupsByKeyword(String keyword, long appId,
			Pageable pageable) {
		return apGroupSingleDao.searchByKeyword("%".concat(keyword).concat("%"), appId, pageable);
	}

	
	@Override
	public Page<ApRoleSingle> findUserGroupRefRole(long userGroupId, String keyword,
			Pageable pageable) {
		return apRefGroupRoleDao.findGroupRefRole(userGroupId, "%".concat(keyword).concat("%"), pageable);
	}

	@Override
	public Page<ApRoleSingle> findCandidateRole(long appId, long userGroupId,
			String keyword, Pageable pageable) {
		return apRefGroupRoleDao.findCadidateGroupRefRole(appId, userGroupId, "%".concat(keyword).concat("%"), pageable);
	}

	@Transactional
	@Override
	public void saveUserGroupRefRole(long userGroupId, long roleId) {
		ApRefGroupRole argr = new ApRefGroupRole();
		ApRefGroupRolePK pk = new ApRefGroupRolePK();
		pk.setGroupId(userGroupId);
		pk.setRoleId(roleId);
		argr.setId(pk);
		apRefGroupRoleDao.save(argr);
	}

	@Transactional
	@Override
	public void delUserGroupRefRole(long userGroupId, long roleId) {
		ApRefGroupRolePK pk = new ApRefGroupRolePK();
		pk.setGroupId(userGroupId);
		pk.setRoleId(roleId);
		apRefGroupRoleDao.delete(pk);
	}

	@Transactional
	@Override
	public void saveUserGroupRefRoles(long userGroupId, String roleIds) {
		String[] arrayRoleId = roleIds.split("\\,");
		for(String roleId : arrayRoleId){
			saveUserGroupRefRole(userGroupId, Long.parseLong(roleId));
		}
	}

	@Transactional
	@Override
	public void delUserGroupRefRoles(long userGroupId, String roleIds) {
		String[] arrayRoleId = roleIds.split("\\,");
		for(String roleId : arrayRoleId){
			delUserGroupRefRole(userGroupId, Long.parseLong(roleId));
		}
	}

	@Override
	public Page<ApApplication> findApApplications(Pageable pageable) {
		return apApplicationDao.findAll(pageable);
	}

	@Override
	public Page<ApApplication> searchApApplicationsByKeyword(String keyword,
			Pageable pageable) {
		return apApplicationDao.searchByKeyword("%".concat(keyword).concat("%"), pageable);
	}

	@Transactional
	@Override
	public void saveOrUpdateApApplication(ApApplication apApplication) {
		apApplicationDao.save(apApplication);
	}

	@Override
	public boolean checkApApplicationCode(String code) {
		return apApplicationDao.findByCode(code) == null;
	}

	@Transactional
	@Override
	public void deleteApApplication(Collection<Long> ids) {
		apApplicationDao.deleteApplicationByIds(ids);
		apRefUserApplicationDao.deleteRelsByApplicationIds(ids);	
	}

}
