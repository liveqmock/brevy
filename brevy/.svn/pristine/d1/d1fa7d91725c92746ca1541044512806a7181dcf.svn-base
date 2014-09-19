package com.brevy.fw.common.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brevy.fw.common.support.exception.CoreRuntimeException;

/**
 * @Description 基于Shiro简化并封装的方法
 * @author caobin
 * @date 2013-7-29
 * @version 1.0
 */
public class ShiroUtils {

	/**
	 * @Description 获取当前用户的session id
	 * @return
	 * @author caobin
	 */
	public static String getSessionId(){
		return (String)SecurityUtils.getSubject().getSession().getId();
	}
	
	private final static Logger LOG = LoggerFactory.getLogger(ShiroUtils.class);
	
	/**
	 * @Description 重新加载shiro过滤器链定义
	 * @param chains  eg:key:/auth/*.html, value:authc,roles[admin] 
	 * @author caobin
	 */
	@SuppressWarnings("unchecked")
	public static void reloadFilterChainDefinitions(LinkedHashMap<String, String> chains){
		if(MapUtils.isEmpty(chains)){
			LOG.warn("chains is empty, ignore reload action.");
			return;
		}
		
		DefaultFilterChainManager filterManager = getFilterChainManager();  
		LOG.debug("Before filterManager reloading: {}", new Object[]{getFiltersDescription(filterManager)});
		
		try{
			for(Entry<String, Filter> filterEntry : filterManager.getFilters().entrySet()) {  
	            if(PathMatchingFilter.class.isInstance(filterEntry.getValue())) {  
	                PathMatchingFilter filter = PathMatchingFilter.class.cast(filterEntry.getValue());  
	                Map<String, Object> appliedPaths = (Map<String, Object>)FieldUtils.readField(filter, "appliedPaths", true);  
	                synchronized (appliedPaths) {   
	                    appliedPaths.clear(); 
	                    LOG.debug("cleared appliedPaths");
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
     * @return
     * @author caobin
     */
    public static DefaultFilterChainManager getFilterChainManager() {  
        return ((DefaultFilterChainManager)((PathMatchingFilterChainResolver)SpringContextHolder.getBean(AbstractShiroFilter.class).getFilterChainResolver()).getFilterChainManager());  
    } 
}
