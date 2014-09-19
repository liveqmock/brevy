package com.brevy.core.shiro.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brevy.core.shiro.ShiroFilter;
import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.model.ApGroup;
import com.brevy.core.shiro.model.ApMenu;
import com.brevy.core.shiro.model.ApRole;
import com.brevy.core.shiro.model.ApUser;
import com.brevy.core.shiro.model.ShiroUser;
import com.brevy.core.shiro.support.realm.CustomJdbcRealm;
import com.brevy.core.support.exception.CoreRuntimeException;

/**
 * @Description  基于Shiro framework的工具类
 * @author caobin
 * @date 2013-12-19
 * @version 1.0
 */
public class ShiroUtils {

	/**
	 * @Description 获取由Shiro管理的Session
	 * @return
	 * @author caobin
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * @Description 获取由Shiro管理的Session
	 * @param create 是否创建一个新的Session
	 * @return
	 * @author caobin
	 */
	public static Session getSession(boolean create){
		return SecurityUtils.getSubject().getSession(create);
	}
	
	/**
	 * @Description 获取当前登录用户
	 * @return
	 * @author caobin
	 */
	public static ShiroUser getCurrentUser(){
		return (ShiroUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * @Description 获取当前应用ID
	 * @return
	 * @author caobin
	 */
	public static long getAppId(){
		return getCurrentUser().getAppId();
	}
	
	/**
	 * @Description 获取菜单（关联角色）
	 * @param user
	 * @return
	 * @author caobin
	 */
	public static List<ApMenu> getMenus(ApUser user){
		Set<ApRole> roles = getRoles(user, true);
		if(roles != null){
			Set<ApMenu> menuset = new HashSet<ApMenu>();
			for(ApRole ar : roles){
				menuset.addAll(ar.getMenus());
			}
			List<ApMenu> menus = new ArrayList<ApMenu>(menuset);
			Collections.sort(menus, new Comparator<ApMenu>() {
				@Override
				public int compare(ApMenu o1, ApMenu o2) {
					if(o1.getParentId() > o2.getParentId()){
						return 1;
					}else if(o1.getParentId() == o2.getParentId()){
						if(o1.getSort() > o2.getSort()){
							return 1;
						}else if(o1.getSort() == o2.getSort()){
							return 0;
						}
					}				
					return -1;
				}
			});
			return menus;
		}
		return null;
	}
	
	/**
	 * @Description 获取组（关联角色）
	 * @param user
	 * @return
	 * @author caobin
	 */
	public static Set<ApGroup> getGroups(ApUser user){
		if(user != null && user.getGroups() != null){
			return user.getGroups();
		}
		return null;
	}

	
	/**
	 * @Description 获取角色
	 * @param user 用户
	 * @param mergeGroup 是否合并组中角色
	 * @param roletype 角色类型枚举
	 * @return
	 * @author caobin
	 */
	public static Set<ApRole> getRoles(ApUser user, boolean mergeGroup){
		if(user != null && user.getRoles() != null){
			if(mergeGroup){
				Set<ApRole> allRoles = new HashSet<ApRole>();
				allRoles.addAll(user.getRoles());
				if(user.getGroups() != null){
					for(ApGroup ag : user.getGroups()){
						if(ag.getRoles() != null){
							allRoles.addAll(ag.getRoles());
						}
					}
				}		
				return allRoles;			
			}else{
				return user.getRoles();
			}				
		}
		return null;
	}

	/**
	 * @Description 基于AccessPerm重新加载过滤器链定义
	 * @param apAccessPerms
	 * @param abstractShiroFilter
	 * @author caobin
	 */
	public static void reloadFilterChainDefinitions(List<ApAccessPerm> apAccessPerms, AbstractShiroFilter abstractShiroFilter){
		if(CollectionUtils.isNotEmpty(apAccessPerms)){
			LinkedHashMap<String, String> chains = new LinkedHashMap<String, String>();
			StringBuilder patternExpr = new StringBuilder();
			for(ApAccessPerm aap : apAccessPerms){
				if(StringUtils.isNotBlank(aap.getUrlPattern())){
					patternExpr = new StringBuilder();
					if(StringUtils.isNotBlank(aap.getAuthenticationFilter())){
						patternExpr.append(aap.getAuthenticationFilter());
					}
					if(StringUtils.isNotBlank(aap.getAuthorizationFilter())){
						if(patternExpr.length() > 0){
							patternExpr.append(",");	
						}
						
						patternExpr
							.append(ShiroFilter.RolesAuthorizationFilter.getCode())
							.append("[").append(aap.getCode()).append("]");
					}
					chains.put(aap.getUrlPattern(), patternExpr.toString());
				}	
			}
			reloadFilterChainDefinitions(chains, abstractShiroFilter);
		}		
	}
	
	
	/**
	 * @Description 重新加载shiro过滤器链定义
	 * @param chains  eg:key:/auth/*.html, value:authc,roles[admin] 
	 * @param abstractShiroFilter
	 * @author caobin
	 */
	@SuppressWarnings("unchecked")
	public static void reloadFilterChainDefinitions(LinkedHashMap<String, String> chains, AbstractShiroFilter abstractShiroFilter){
		if(MapUtils.isEmpty(chains)){
			LOG.warn("chains is empty, ignore reload action.");
			return;
		}
		
		DefaultFilterChainManager filterManager = getFilterChainManager(abstractShiroFilter);  
		LOG.debug("Before filterManager reloading: {}", new Object[]{getFiltersDescription(filterManager)});
		
		try{
			for(Entry<String, Filter> filterEntry : filterManager.getFilters().entrySet()) {  
	            if(PathMatchingFilter.class.isInstance(filterEntry.getValue())) {  
	                PathMatchingFilter filter = PathMatchingFilter.class.cast(filterEntry.getValue());  
	                Map<String, Object> appliedPaths = (Map<String, Object>)FieldUtils.readField(filter, "appliedPaths", true);  
	                synchronized (appliedPaths) {   
	                    appliedPaths.clear(); 
	                    LOG.debug("appliedPaths cleared");
	                }  
	                
	                synchronized (filterManager.getFilterChains()) { 
		                LOG.debug("Filter config: {}", new Object[]{filterManager.getFilterConfig()});  
		                filterManager.getFilterChains().clear();  
		                for(Entry<String,String> chain : chains.entrySet()){  
		                    filterManager.createChain(chain.getKey(), chain.getValue());  
		                    LOG.debug("creating chain: {} = {}", new Object[]{chain.getKey(),chain.getValue()});  
		                }  
		            }  
	            }  
	        }  
		}catch(Exception e){
			throw new CoreRuntimeException(e);
		}
		
		LOG.debug("FilterManager reloaded: {}", new Object[]{getFiltersDescription(filterManager)});	
	}
 
    /**
     * @Description 获取过滤器描述 
     * @param filterManager
     * @return
     * @author caobin
     */
    @SuppressWarnings("unchecked")
	public static String getFiltersDescription(DefaultFilterChainManager filterManager) {  
        StringBuilder filtersDesc = new StringBuilder();  
        try{
        	 for(Entry<String, Filter> filterEntryDesc : filterManager.getFilters().entrySet()) {  
                 filtersDesc.append("\n").append(filterEntryDesc.getKey()).append(":").append(filterEntryDesc.getValue()).append("\n");  
                 if(PathMatchingFilter.class.isInstance(filterEntryDesc.getValue())) {  
                     PathMatchingFilter filter = PathMatchingFilter.class.cast(filterEntryDesc.getValue());  
                     Map<String, Object> appliedPaths = (Map<String, Object>)FieldUtils.readField(filter, "appliedPaths", true);  
                     for(Entry<String, Object> paths : appliedPaths.entrySet()) {  
                         filtersDesc.append(paths.getKey()).append(" = ").append(Arrays.toString((String[])paths.getValue())).append("\n");  
                     }  
                 }  
             }  
        }catch(IllegalAccessException iae){
        	throw new CoreRuntimeException(iae);
        }
        return filtersDesc.toString();  
    } 
    
   
    /**
     * @Description 获取过滤器链管理器
     * @param abstractShiroFilter
     * @return
     * @author caobin
     */
    public static DefaultFilterChainManager getFilterChainManager(AbstractShiroFilter abstractShiroFilter) {  
        return ((DefaultFilterChainManager)((PathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver()).getFilterChainManager());  
    } 
    
    
    /**   
     * 通过ShiroUser移除权限缓存 
     * @param customJdbcRealm
     * @param principal 
     * @return boolean  
     */  
    public static boolean removeAuthCacheByShiroUser(CustomJdbcRealm customJdbcRealm, ShiroUser principal) {  
        try {  
        	customJdbcRealm.clearCachedAuthorizationInfo(principal);
            return true;  
        } catch (Exception e) {  
            LOG.error(ExceptionUtils.getFullStackTrace(e));  
            return false;  
        }  
    }  
    
    /**
     * @Description 移除所有权限缓存 
     * @param customJdbcRealm
     * @return
     * @author caobin
     */
    public static boolean removeAllAuthCache(CustomJdbcRealm customJdbcRealm){
    	 try {  
    		 customJdbcRealm.clearAllCachedAuthorizationInfo();
             return true;  
         } catch (Exception e) {  
             LOG.error(ExceptionUtils.getFullStackTrace(e));  
             return false;  
         }  
    }
    

    private final static Logger LOG = LoggerFactory.getLogger(ShiroUtils.class);
}
