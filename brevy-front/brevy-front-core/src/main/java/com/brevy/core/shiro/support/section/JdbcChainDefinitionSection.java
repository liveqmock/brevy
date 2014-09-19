package com.brevy.core.shiro.support.section;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.brevy.core.shiro.ShiroFilter;
import com.brevy.core.shiro.model.ApAccessPerm;
import com.brevy.core.shiro.service.ShiroService;


/**
 * @Description 基于数据库加载的访问权限
 * @author caobin
 * @date 2013-12-20
 * @version 1.0
 */
public class JdbcChainDefinitionSection implements FactoryBean<Ini.Section>, InitializingBean {

	
	@Override
	public Section getObject() throws Exception {
		List<ApAccessPerm> apAccessPerms = shiroService.findAccessPermByAppId(appId);
		Ini ini = new Ini();
		//initializing section
		ini.addSection(Ini.DEFAULT_SECTION_NAME);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		if(CollectionUtils.isNotEmpty(apAccessPerms)){
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
					log.debug(">>> Adding {}={} into section.", new Object[]{aap.getUrlPattern(), patternExpr});
					section.put(aap.getUrlPattern(), patternExpr.toString());
				}	
			}
		}	
		return section;
	}


	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(appId, "app id is required.");
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShiroService shiroService;
	
	private long appId;

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}

}
