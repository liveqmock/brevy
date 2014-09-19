package com.brevy.fw.common.core.security.shiro;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.brevy.fw.common.core.security.shiro.model.ApAccessPerm;
import com.brevy.fw.common.core.security.shiro.service.ShiroService;

public class JdbcChainDefinitionSection implements FactoryBean<Ini.Section>, InitializingBean {

	@Override
	public Section getObject() throws Exception {
		List<ApAccessPerm> apAccessPerms = shiroService.getAllAccessPermDetail(appId);
		Ini ini = new Ini();
		//init section
		ini.addSection(Ini.DEFAULT_SECTION_NAME);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		if(CollectionUtils.isNotEmpty(apAccessPerms)){	
			for(ApAccessPerm aap : apAccessPerms){
				log.debug("Adding {}={} into section...", new Object[]{aap.getUrlPattern(), aap.getPatternExpr()});
				section.put(aap.getUrlPattern(), aap.getPatternExpr());
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
		return false;
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(appId, "app id is required.");
		Assert.notNull(shiroService, "shiroService is required.");
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ShiroService shiroService;
	
	private Long appId;

	/**
	 * @param shiroService the shiroService to set
	 */
	public void setShiroService(ShiroService shiroService) {
		this.shiroService = shiroService;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

}
