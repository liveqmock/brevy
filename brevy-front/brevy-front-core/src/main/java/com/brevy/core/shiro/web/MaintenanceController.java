package com.brevy.core.shiro.web;

import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getLongValue;
import static org.apache.commons.collections.MapUtils.getString;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApApplication;
import com.brevy.core.shiro.model.ApGroup;
import com.brevy.core.shiro.model.ApGroupSingle;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApOperPerm;
import com.brevy.core.shiro.model.ApRoleSingle;
import com.brevy.core.shiro.model.ApUserSingle;
import com.brevy.core.shiro.model.CadDictDetail;
import com.brevy.core.shiro.service.MaintenanceService;
import com.brevy.core.shiro.support.realm.CustomJdbcRealm;
import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.core.support.exception.CoreException;
import com.brevy.core.support.web.BaseController;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @Description 权限维护（配置）控制器
 * @author caobin
 * @date 2013-12-31
 * @version 1.0
 */
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController extends BaseController {
	
	private final static String UNIQUE_GROUP_CODE = "用户组代码必须唯一";

	private final static String UNIQUE_ACCESS_PERM_CODE = "访问权限代码必须唯一";
	
	private final static String UNIQUE_OPER_PERM_CODE = "操作权限代码必须唯一";
	
	private final static String UNIQUE_ROLE_CODE = "角色代码必须唯一";
	
	private final static String UNIQUE_APPLICATION = "应用系统代码必须唯一";
	
	private final static String UNIQUE_USERNAME = "用户名必须唯一";
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@Autowired
	private CustomJdbcRealm customJdbcRealm;
	

	/*############################################  公共    ############################################*/
	
	/**
	 * @Description 获取动态菜单
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getDynamicMenu")
	@ResponseBody
	public List<ApMenu> getDynamicMenu(@RequestBody Map<String, String> p){
		return maintenanceService.findChildren(getLongValue(p, "appId",  ShiroUtils.getAppId()), getLongValue(p, "node"));
	}

	/**
	 * @Description 获取动态父级菜单
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/getDynamicParentMenu")
	@ResponseBody
	public List<ApMenu> getDynamicParentMenu(@RequestBody Map<String, String> p){
		return maintenanceService.findParent(getLongValue(p, "appId",  ShiroUtils.getAppId()), getLongValue(p, "node"));
	}
	
	
	/*############################################  功能菜单维护    ############################################*/
	
	/**
	 * @Description 保存（更新）菜单配置
	 * @param apMenu
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/funcmenu/save")
	@ResponseBody
	public ModelAndView saveOrUpdateFuncMenu(@RequestBody ApMenu apMenu){
		log.debug(">>>> apMenu from request is : {}", new Object[]{apMenu});
		maintenanceService.saveOrUpdateApMenu(apMenu);
		return this.successView();
	}
	
	/**
	 * @Description 保存（更新）菜单配置
	 * @param apMenu
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/funcmenu/dragAndDrop")
	@ResponseBody
	public ModelAndView dragAndDropFuncMenu(@RequestBody ApMenu apMenu){
		log.debug(">>>> apMenu from request is : {}", new Object[]{apMenu});
		long refreshNodeId = maintenanceService.updateAndRearrangeNodes(apMenu);
		return this.successView(this.createMav().addObject("refreshNodeId", refreshNodeId));
	}

	@RequestMapping("/funcmenu/addMenu")
	@ResponseBody
	public ModelAndView addMenu(@RequestBody ApMenu apMenu){
		log.debug(">>>> apMenu from request is : {}", new Object[]{apMenu});
		long sort = maintenanceService.countChildren(apMenu) + 1;
		apMenu.setSort(sort);
		maintenanceService.saveOrUpdateApMenu(apMenu);
		return this.successView();
	}
	
	@RequestMapping("/funcmenu/delMenu")
	@ResponseBody
	public ModelAndView delMenuCascade(@RequestBody ApMenu apMenu){
		log.debug(">>>> apMenu from request is : {}", new Object[]{apMenu});		
		maintenanceService.delApMenuCascade(apMenu);		
		return this.successView();
	}
	
	
	/*############################################  访问权限维护    ############################################*/
	
	/**
	 * @Description 保存（更新）访问权限
	 * @param apAccessPerm
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/accessAuth/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateAccessPerm(@RequestBody ApAccessPerm apAccessPerm){
		log.debug(">>>> apAccessPerm from request is : {}", new Object[]{apAccessPerm});
		if(apAccessPerm.getId() == 0 && !maintenanceService.checkApAccessPermCode(apAccessPerm.getCode())){//新增且重复code
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_ACCESS_PERM_CODE);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_ACCESS_PERM_CODE), errorFields);
		}
		maintenanceService.saveOrUpdateApAccessPerm(apAccessPerm);
		return this.successView();
	}
	
	
	
	@RequestMapping("/accessauth/getAccessAuthList")
	@ResponseBody
	public Page<ApAccessPerm> getAccessAuths(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "sort");
		//获取查询参数
		String keyword = getString(p, "query");
		Page<ApAccessPerm> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApAccessPerms(getLongValue(p, "appId"), pageable) : 
							maintenanceService.searchApAccessPermsByKeyword(keyword, getLongValue(p, "appId"), pageable)	;
		Iterator<ApAccessPerm> apAccessPermIter = pageList.iterator();
		while(apAccessPermIter.hasNext()){
			//移除关联数据
			apAccessPermIter.next().setRoles(null);
		}
		return pageList;
	}
	
	@RequestMapping("/accessAuth/delete")
	@ResponseBody
	public ModelAndView deleteAccessAuths(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			maintenanceService.deleteApAccessPerm(Arrays.asList(longs), getLongValue(p, "appId"));
		}
		return this.successView();	
	}
	
	
	/*############################################  操作权限维护    ############################################*/
	
	/**
	 * @Description 保存（更新）操作权限
	 * @param apOperPerm
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/operAuth/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateOperPerm(@RequestBody ApOperPerm apOperPerm){
		log.debug(">>>> apOperPerm from request is : {}", new Object[]{apOperPerm});
		if(apOperPerm.getId() == 0 && !maintenanceService.checkApOperPermCode(apOperPerm.getCode())){//新增且重复code
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_OPER_PERM_CODE);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_OPER_PERM_CODE), errorFields);
		}
		maintenanceService.saveOrUpdateApOperPerm(apOperPerm);
		return this.successView();
	}
	
	
	
	@RequestMapping("/operauth/getOperAuthList")
	@ResponseBody
	public Page<ApOperPerm> getOperAuths(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "sort");
		//获取查询参数
		String keyword = getString(p, "query");
		Page<ApOperPerm> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApOperPerms(getLongValue(p, "appId"), pageable) : 
							maintenanceService.searchApOperPermsByKeyword(keyword, getLongValue(p, "appId"), pageable)	;
		return pageList;
	}
	
	@RequestMapping("/operAuth/delete")
	@ResponseBody
	public ModelAndView deleteOperAuths(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			maintenanceService.deleteApOperPerm(Arrays.asList(longs), getLongValue(p, "appId"));
		}
		return this.successView();	
	}
	
	
	/*############################################  角色维护    ############################################*/
	
	/**
	 * @Description 保存（更新）角色
	 * @param apRole
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateRole(@RequestBody ApRoleSingle apRole){
		log.debug(">>>> apRole from request is : {}", new Object[]{apRole});
		if(apRole.getId() == 0 && !maintenanceService.checkApRoleCode(apRole.getCode())){//新增且重复code
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_OPER_PERM_CODE);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_ROLE_CODE), errorFields);
		}
		maintenanceService.saveOrUpdateApRoleSingle(apRole);
		return this.successView();
	}
	
	
	
	/**
	 * @description 获取角色列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getRoleList")
	@ResponseBody
	public Page<ApRoleSingle> getRoles(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "id");
		//获取查询参数
		String keyword = getString(p, "query");
		Page<ApRoleSingle> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApRoles(getLongValue(p, "appId"), pageable) : 
							maintenanceService.searchApRolesByKeyword(keyword, getLongValue(p, "appId"), pageable);
		/*Iterator<ApRole> apRoleIter = pageList.iterator();
		while(apRoleIter.hasNext()){
			ApRole ari = apRoleIter.next();
			//移除关联数据
			ari.setAccessPerms(null);
			ari.setMenus(null);
			ari.setOperPerms(null);
		}*/
		return pageList;
	}
	
	@RequestMapping("/role/delete")
	@ResponseBody
	public ModelAndView deleteRoles(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			maintenanceService.deleteApRole(Arrays.asList(longs), getLongValue(p, "appId"));
		}
		return this.successView();	
	}
	
	/**
	 * @Description 获取关联角色的动态菜单
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getRefRoleDynamicMenu")
	@ResponseBody
	public Collection<?> getRefRoleDynamicMenu(@RequestBody Map<String, String> p){
		return maintenanceService.findChildren(getLongValue(p, "appId",  ShiroUtils.getAppId()), getLongValue(p, "node"), getLongValue(p, "roleId"));
	}
	
	/**
	 * @Description 获取当前父菜单下的候选菜单
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getCandidateMenu")
	@ResponseBody
	public List<ApMenu> getCandidateMenus(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		return maintenanceService.findCandidateMenus(getLongValue(p, "appId",  ShiroUtils.getAppId()), getLongValue(p, "node"), getLongValue(p, "roleId"));
	}
	
	
	/**
	 * @Description 添加角色和菜单关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/addMenuRefRole")
	@ResponseBody
	public ModelAndView addMenuRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveRoleRefMenu(getLongValue(p, "roleId"), getLongValue(p, "menuId"));
		return this.successView();
	}
	
	/**
	 * @Description （级联）删除角色和菜单关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/delMenuRefRole")
	@ResponseBody
	public ModelAndView delMenuRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delRoleRefMenu(getLongValue(p, "roleId"), getLongValue(p, "menuId"));
		return this.successView();
	}
	
	
	/*############################################  角色关联访问权限    ############################################*/
	
	/**
	 * @Description 获取角色关联的访问权限
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getRefRoleAccessAuth")
	@ResponseBody
	public Page<ApAccessPerm> getRefRoleAccessAuth(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "sort");
		Page<ApAccessPerm> pageList = maintenanceService.findRoleRefAccessAuth(getLongValue(p, "roleId"), keyword, pageable);	
		Iterator<ApAccessPerm> apAccessPermIter = pageList.iterator();
		while(apAccessPermIter.hasNext()){
			ApAccessPerm aap = apAccessPermIter.next();
			//移除关联数据
			aap.setRoles(null);
		}
		return pageList;
	}
	
	
	/**
	 * @Description 获取关联角色可选的访问权限
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getCandidateAccessAuth")
	@ResponseBody
	public Page<ApAccessPerm> getCandidateAccessAuth(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "sort");
		Page<ApAccessPerm> pageList =  maintenanceService.findCandidateAccessAuth(
				getLongValue(p, "appId"), 
				getLongValue(p, "roleId"), 
				keyword, pageable);	
		Iterator<ApAccessPerm> apAccessPermIter = pageList.iterator();
		while(apAccessPermIter.hasNext()){
			ApAccessPerm aap = apAccessPermIter.next();
			//移除关联数据
			aap.setRoles(null);
		}
		return pageList;
	}
	
	
	/**
	 * @Description 添加角色和访问权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/addAccessAuthRefRole")
	@ResponseBody
	public ModelAndView addAccessAuthRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveRoleRefAccessAuth(getLongValue(p, "roleId"), getLongValue(p, "accessPermId"));
		return this.successView();
	}
	
	/**
	 * @Description 删除角色和访问权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/delAccessAuthRefRole")
	@ResponseBody
	public ModelAndView delAccessAuthRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delRoleRefAccessAuth(getLongValue(p, "roleId"), getLongValue(p, "accessPermId"));
		return this.successView();
	}
	
	
	/**
	 * @Description 批量添加角色和访问权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/addAccessAuthsRefRole")
	@ResponseBody
	public ModelAndView addAccessAuthsRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveRoleRefAccessAuths(getLongValue(p, "roleId"), getString(p, "accessPermIds"));
		return this.successView();
	}
	
	/**
	 * @Description 批量删除角色和访问权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/delAccessAuthsRefRole")
	@ResponseBody
	public ModelAndView delAccessAuthsRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delRoleRefAccessAuths(getLongValue(p, "roleId"), getString(p, "accessPermIds"));
		return this.successView();
	}
	
	
	
	/*############################################  角色关联操作权限    ############################################*/
	/**
	 * @Description 获取角色关联的操作权限
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getRefRoleOperAuth")
	@ResponseBody
	public Page<ApOperPerm> getRefRoleOperAuth(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "sort");
		return maintenanceService.findRoleRefOperAuth(getLongValue(p, "roleId"), keyword, pageable);	
	}
	
	
	/**
	 * @Description 获取关联角色可选的操作权限
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/getCandidateOperAuth")
	@ResponseBody
	public Page<ApOperPerm> getCandidateOperAuth(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "sort");
		return maintenanceService.findCandidateOperAuth(
				getLongValue(p, "appId"), 
				getLongValue(p, "roleId"), 
				keyword, pageable);	
	}
	
	
	/**
	 * @Description 添加角色和操作权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/addOperAuthRefRole")
	@ResponseBody
	public ModelAndView addOperAuthRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveRoleRefOperAuth(getLongValue(p, "roleId"), getLongValue(p, "operPermId"));
		return this.successView();
	}
	
	/**
	 * @Description 删除角色和操作权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/delOperAuthRefRole")
	@ResponseBody
	public ModelAndView delOperAuthRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delRoleRefOperAuth(getLongValue(p, "roleId"), getLongValue(p, "operPermId"));
		return this.successView();
	}
	
	
	/**
	 * @Description 批量添加角色和操作权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/addOperAuthsRefRole")
	@ResponseBody
	public ModelAndView addOperAuthsRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveRoleRefOperAuths(getLongValue(p, "roleId"), getString(p, "operPermIds"));
		return this.successView();
	}
	
	/**
	 * @Description 批量删除角色和操作权限关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/role/delOperAuthsRefRole")
	@ResponseBody
	public ModelAndView delOperAuthsRefRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delRoleRefOperAuths(getLongValue(p, "roleId"), getString(p, "operPermIds"));
		return this.successView();
	}
	
	/*############################################  用户组关联角色    ############################################*/
	
	/**
	 * @Description 保存（更新）用户组
	 * @param apGroup
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateUserGroup(@RequestBody ApGroupSingle apGroup){
		log.debug(">>>> apGroup from request is : {}", new Object[]{apGroup});
		if(apGroup.getId() == 0 && !maintenanceService.checkApGroupCode(apGroup.getCode())){//新增且重复code
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_GROUP_CODE);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_GROUP_CODE), errorFields);
		}
		maintenanceService.saveOrUpdateApGroupSingle(apGroup);
		return this.successView();
	}

	/**
	 * @description 删除用户组
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/delete")
	@ResponseBody
	public ModelAndView deleteUserGroups(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			maintenanceService.deleteApGroup(Arrays.asList(longs), getLongValue(p, "appId"));
		}
		return this.successView();	
	}
	
	/**
	 * 获取用户组
	 * @param p
	 * @return
	 */
	@RequestMapping("/userGroup/getUserGroupList")
	@ResponseBody
	public Page<ApGroupSingle> getUserGroups(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		//获取查询参数
		String keyword = getString(p, "query");
		Page<ApGroupSingle> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApGroups(getLongValue(p, "appId"), pageable) : 
							maintenanceService.searchApGroupsByKeyword(keyword, getLongValue(p, "appId"), pageable)	;
		/*Iterator<ApGroup> apGroupIter = pageList.iterator();
		while(apGroupIter.hasNext()){
			//移除关联数据
			apGroupIter.next().setRoles(null);
		}*/
		return pageList;
	}
	
	
	/**
	 * @Description 获取用户组关联的角色
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/getRefUserGroupRole")
	@ResponseBody
	public Page<ApRoleSingle> getRefUserGroupRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		Page<ApRoleSingle> pageList = maintenanceService.findUserGroupRefRole(getLongValue(p, "userGroupId"), keyword, pageable);	
		/*Iterator<ApRole> apRoleIter = pageList.iterator();
		while(apRoleIter.hasNext()){
			ApRole ar = apRoleIter.next();
			//移除关联数据
			ar.setMenus(null);
			ar.setOperPerms(null);
			ar.setAccessPerms(null);
		}*/
		return pageList;
	}
	
	
	/**
	 * @Description 获取关联用户组可选的角色
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/getCandidateRole")
	@ResponseBody
	public Page<ApRoleSingle> getCandidateRole(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		Page<ApRoleSingle> pageList =  maintenanceService.findCandidateRole(getLongValue(p, "appId"), getLongValue(p, "userGroupId"), keyword, pageable);	
		/*Iterator<ApRole> apRoleIter = pageList.iterator();
		while(apRoleIter.hasNext()){
			ApRole ar = apRoleIter.next();
			//移除关联数据
			ar.setMenus(null);
			ar.setOperPerms(null);
			ar.setAccessPerms(null);
		}*/
		return pageList;
	}
	
	
	/**
	 * @Description 添加用户组和角色的关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/addRoleRefUserGroup")
	@ResponseBody
	public ModelAndView addRoleRefUserGroup(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveUserGroupRefRole(getLongValue(p, "userGroupId"), getLongValue(p, "roleId"));
		return this.successView();
	}
	
	/**
	 * @Description 删除用户组和角色的关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/delRoleRefUserGroup")
	@ResponseBody
	public ModelAndView delRoleRefUserGroup(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delUserGroupRefRole(getLongValue(p, "userGroupId"), getLongValue(p, "roleId"));
		return this.successView();
	}
	
	
	/**
	 * @Description 批量添加用户组和角色关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/addRolesRefUserGroup")
	@ResponseBody
	public ModelAndView addRolesRefUserGroup(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveUserGroupRefRoles(getLongValue(p, "userGroupId"), getString(p, "roleIds"));
		return this.successView();
	}
	
	/**
	 * @Description 批量删除用户组和角色关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/userGroup/delRolesRefUserGroup")
	@ResponseBody
	public ModelAndView delRolesRefUserGroup(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delUserGroupRefRoles(getLongValue(p, "userGroupId"), getString(p, "roleIds"));
		return this.successView();
	}
	
	/*############################################  应用维护    ############################################*/
	
	/**
	 * @Description 保存（更新）访问权限
	 * @param apAccessPerm
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/app/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateApplication(@RequestBody ApApplication apApplication){
		log.debug(">>>> apApplication from request is : {}", new Object[]{apApplication});
		if(apApplication.getId() == 0 && !maintenanceService.checkApApplicationCode(apApplication.getCode())){//新增且重复code
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_APPLICATION);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_APPLICATION), errorFields);
		}
		maintenanceService.saveOrUpdateApApplication(apApplication);
		return this.successView();
	}
	
	
	
	@RequestMapping("/app/getAppList")
	@ResponseBody
	public Page<ApApplication> getApplications(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		//获取查询参数
		String keyword = getString(p, "query");
		Page<ApApplication> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApApplications(pageable) : 
							maintenanceService.searchApApplicationsByKeyword(keyword, pageable)	;
		return pageList;
	}
	
	@RequestMapping("/app/delete")
	@ResponseBody
	public ModelAndView deleteApplications(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			maintenanceService.deleteApApplication(Arrays.asList(longs));
		}
		return this.successView();	
	}
	
	/*############################################  用户维护    ############################################*/
	
	/**
	 * @description 获取用户列表
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/getUserList")
	@ResponseBody
	public Page<ApUserSingle> getUsers(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		PageBounds pageBounds = new PageBounds(getIntValue(p, PAGE), getIntValue(p, PAGE_SIZE));  
		
		//获取查询参数
		String keyword = getString(p, "query");
		PageList<ApUserSingle> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApUsers(pageBounds) : 
							maintenanceService.searchApUsersByKeyword(keyword, pageBounds);
		return this.toPage(pageList);
	}
		
	@RequestMapping("/user/getDictStore")
	@ResponseBody
	public List<CadDictDetail> getDictStore(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		return maintenanceService.findCadDictDetails(getLongValue(p, "dictId"));
	}
	
	/**
	 * @Description 保存（更新）用户
	 * @param apUser
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/saveOrUpdate")
	@ResponseBody
	public ModelAndView saveOrUpdateUser(@RequestBody ApUserSingle apUser){
		log.debug(">>>> apUser from request is : {}", new Object[]{apUser});
		if(apUser.getId() == 0 && !maintenanceService.checkApUserUsername(apUser.getUsername())){//新增且重复用户名
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_USERNAME);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_USERNAME), errorFields);
		}
		HashedCredentialsMatcher hcm = (HashedCredentialsMatcher)customJdbcRealm.getCredentialsMatcher();
		//密码处理
		
		if(apUser.getId() == 0){//insert	
			Md5Hash md5Hash = new Md5Hash(apUser.getPassword(), null, hcm.getHashIterations());
			String md5Hex = md5Hash.toHex();
			log.debug("md5 hex: {}", new Object[]{md5Hex});
			apUser.setPassword(md5Hex);
		}else{//update
			ApUserSingle apUserOriginal = maintenanceService.findUser(apUser.getId());
			if(!apUserOriginal.getPassword().equals(apUser.getPassword())){
				Md5Hash md5Hash = new Md5Hash(apUser.getPassword(), null, hcm.getHashIterations());
				String md5Hex = md5Hash.toHex();
				log.debug("md5 hex: {}", new Object[]{md5Hex});
				apUser.setPassword(md5Hex);
			}
		}
		maintenanceService.saveOrUpdateApUserSingle(apUser);
		return this.successView();
	}
	
	
	/**
	 * @description 删除用户
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/delete")
	@ResponseBody
	public ModelAndView deleteUsers(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		String params = getString(p, "ids");	
		if(StringUtils.isNotBlank(params)){
			String[] arrParams = params.split("\\,");
			Long[] longs = new Long[arrParams.length];
			for(int i = 0; i< arrParams.length; i++){
				longs[i] = Long.parseLong(arrParams[i]);
			}
			maintenanceService.deleteApUser(Arrays.asList(longs));
		}
		return this.successView();	
	}
	
	
	/**
	 * @Description 获取用户关联的系统应用
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/getRefUserApp")
	@ResponseBody
	public Page<ApApplication> getRefUserApp(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		Page<ApApplication> pageList = maintenanceService.findUserRefApp(getLongValue(p, "userId"), keyword, pageable);	
		return pageList;
	}
	
	
	/**
	 * @Description 获取关联用户可选的应用系统
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/getCandidateApp")
	@ResponseBody
	public Page<ApApplication> getCandidateApp(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		Page<ApApplication> pageList =  maintenanceService.findCandidateApp(getLongValue(p, "userId"), keyword, pageable);	
		return pageList;
	}
	
	/**
	 * @Description 添加用户和应用系统关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/addAppRefUser")
	@ResponseBody
	public ModelAndView addAppRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveUserRefApp(getLongValue(p, "userId"), getLongValue(p, "appId"));
		return this.successView();
	}
	
	/**
	 * @Description 批量添加用户和应用系统关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/addAppsRefUser")
	@ResponseBody
	public ModelAndView addAppsRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveUserRefApps(getLongValue(p, "userId"), getString(p, "appIds"));
		return this.successView();
	}
	
	
	/**
	 * @Description 删除用户关联应用系统关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/delAppRefUser")
	@ResponseBody
	public ModelAndView delAppRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delUserRefApp(getLongValue(p, "userId"), getLongValue(p, "appId"));
		return this.successView();
	}
	
	/**
	 * @Description 批量删除用户关联应用系统关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/delAppsRefUser")
	@ResponseBody
	public ModelAndView delAppsRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delUserRefApps(getLongValue(p, "userId"), getString(p, "appIds"));
		return this.successView();
	}
	

	/**
	 * @Description 获取用户关联的用户组
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/getRefUserGroup")
	@ResponseBody
	public Page<ApGroupSingle> getRefUserGroup(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		Page<ApGroupSingle> pageList = maintenanceService.findUserRefGroup(getLongValue(p, "userId"), keyword, pageable);	
		return pageList;
	}
	
	
	/**
	 * @Description 获取关联用户可选的用户组
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/getCandidateGroup")
	@ResponseBody
	public Page<ApGroupSingle> getCandidateGroup(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		//获取查询参数
		String keyword = getString(p, "query", "");
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE));
		Page<ApGroupSingle> pageList =  maintenanceService.findCandidateGroup(getLongValue(p, "userId"), keyword, pageable);	
		return pageList;
	}
	
	/**
	 * @Description 添加用户和用户组关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/addGroupRefUser")
	@ResponseBody
	public ModelAndView addGroupRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveUserRefGroup(getLongValue(p, "userId"), getLongValue(p, "groupId"));
		return this.successView();
	}
	
	/**
	 * @Description 批量添加用户和用户组关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/addGroupsRefUser")
	@ResponseBody
	public ModelAndView addGroupsRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.saveUserRefGroups(getLongValue(p, "userId"), getString(p, "groupIds"));
		return this.successView();
	}
	
	
	/**
	 * @Description 删除用户关联用户组关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/delGroupRefUser")
	@ResponseBody
	public ModelAndView delGroupRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delUserRefGroup(getLongValue(p, "userId"), getLongValue(p, "groupId"));
		return this.successView();
	}
	
	/**
	 * @Description 批量删除用户关联系统应用关系
	 * @param p
	 * @return
	 * @author caobin
	 */
	@RequestMapping("/user/delGroupsRefUser")
	@ResponseBody
	public ModelAndView delGroupsRefUser(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		maintenanceService.delUserRefGroups(getLongValue(p, "userId"), getString(p, "groupIds"));
		return this.successView();
	}

}
