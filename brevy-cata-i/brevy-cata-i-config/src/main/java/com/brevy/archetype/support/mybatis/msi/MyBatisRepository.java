package com.brevy.archetype.support.mybatis.msi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Description 以注解方式来标识MyBatis的DAO
 * @author caobin
 * @date 2014-8-19
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyBatisRepository {

}
