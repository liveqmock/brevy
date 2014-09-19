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
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApOperPerm;
import com.brevy.core.shiro.model.ApRole;
import com.brevy.core.shiro.service.MaintenanceService;
import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.core.support.exception.CoreException;
import com.brevy.core.support.web.BaseController;

/**
 * @Description 权限维护（配置）控制器
 * @author caobin
 * @date 2013-12-31
 * @version 1.0
 */
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController extends BaseController {
	
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
	public ModelAndView saveOrUpdateOperPerm(@RequestBody ApRole apRole){
		log.debug(">>>> apRole from request is : {}", new Object[]{apRole});
		if(apRole.getId() == 0 && !maintenanceService.checkApRoleCode(apRole.getCode())){//新增且重复code
			Map<String, String> errorFields = new HashMap<String, String>();
			errorFields.put("code", UNIQUE_OPER_PERM_CODE);
			return this.failureView(this.createMav(), new CoreException(UNIQUE_ROLE_CODE), errorFields);
		}
		maintenanceService.saveOrUpdateApRole(apRole);
		return this.successView();
	}
	
	
	
	@RequestMapping("/role/getRoleList")
	@ResponseBody
	public Page<ApRole> getRoles(@RequestBody Map<String, String> p){
		log.debug(">>>> parameters from request are : {}", new Object[]{p});
		Pageable pageable = new PageRequest(getIntValue(p, PAGE) - 1, getIntValue(p, PAGE_SIZE), Direction.ASC, "id");
		//获取查询参数
		String keyword = getString(p, "query");
		Page<ApRole> pageList = 
				StringUtils.isBlank(keyword) ? 
						maintenanceService.findApRoles(getLongValue(p, "appId"), pageable) : 
							maintenanceService.searchApRolesByKeyword(keyword, getLongValue(p, "appId"), pageable);
		Iterator<ApRole> apRoleIter = pageList.iterator();
		while(apRoleIter.hasNext()){
			ApRole ari = apRoleIter.next();
			//移除关联数据
			ari.setAccessPerms(null);
			ari.setMenus(null);
			ari.setOperPerms(null);
		}
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
	
	
	
	
	@Autowired
	private MaintenanceService maintenanceService;

	private final static String UNIQUE_ACCESS_PERM_CODE = "访问权限代码必须唯一";
	
	private final static String UNIQUE_OPER_PERM_CODE = "操作权限代码必须唯一";
	
	private final static String UNIQUE_ROLE_CODE = "角色代码必须唯一";
}
