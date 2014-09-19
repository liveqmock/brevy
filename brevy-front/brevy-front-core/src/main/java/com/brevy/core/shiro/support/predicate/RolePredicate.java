package com.brevy.core.shiro.support.predicate;

import org.apache.commons.collections.Predicate;

import com.brevy.core.shiro.RoleType;
import com.brevy.core.shiro.model.ApRole;

/**
 * @Description 角色断言
 * @author caobin
 * @date 2013-12-24
 * @version 1.0
 */
public class RolePredicate implements Predicate {

	private RoleType role;
	
	private RolePredicate(RoleType role){
		this.role = role;
	}
	
	public static RolePredicate getInstance(RoleType role){
		return new RolePredicate(role);
	}
	
	@Override
	public boolean evaluate(Object object) {
		ApRole apRole = (ApRole)object;
		if(apRole != null && role != null){
			return role.getCode().equals(apRole.getType());
		}
		return false;
	}
}
