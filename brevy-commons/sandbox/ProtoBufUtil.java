package com.ips.commons.support.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.google.protobuf.Message;
import com.google.protobuf.AbstractMessage.Builder;

public class ProtoBufUtil {
	
	/**
	 * @Description copy message properties to bean 
	 * @param bean
	 * @param message
	 * @throws Exception
	 * @author caobin
	 */
	public static void message2Bean(Object bean, Message message)throws Exception{
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bean);
		if(propertyDescriptors != null){
			Method beanWriteMethod, beanReadMethod = null;
			for(PropertyDescriptor descriptor : propertyDescriptors){
				if((beanWriteMethod = PropertyUtils.getWriteMethod(descriptor)) != null 
						&& (beanReadMethod = PropertyUtils.getReadMethod(descriptor)) != null){
					Method builderReadMethod = MethodUtils.getAccessibleMethod(message.getClass(), beanReadMethod.getName());
					if(builderReadMethod == null)continue;
					String builderValue = String.class.cast(builderReadMethod.invoke(message));	
					if(StringUtils.isNotBlank(builderValue)){
						Class<?> paramType = beanWriteMethod.getParameterTypes()[0];
						beanWriteMethod.invoke(bean, ConvertUtils.convert(builderValue, paramType));
					}				
				}
			}
		}
	}
	
	
	/**
	 * @Description copy builder properties to bean 
	 * @param bean dest
	 * @param builder src
	 * @param beanType type of bean type
	 * @throws Exception
	 * @author caobin
	 */
	public static void builder2Bean(Object bean, Builder<?> builder)throws Exception{
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bean);
		if(propertyDescriptors != null){
			Method beanWriteMethod, beanReadMethod = null;
			for(PropertyDescriptor descriptor : propertyDescriptors){
				if((beanWriteMethod = PropertyUtils.getWriteMethod(descriptor)) != null 
						&& (beanReadMethod = PropertyUtils.getReadMethod(descriptor)) != null){
					Method builderReadMethod = MethodUtils.getAccessibleMethod(builder.getClass(), beanReadMethod.getName());
					if(builderReadMethod == null)continue;
					String builderValue = String.class.cast(builderReadMethod.invoke(builder));		
					if(StringUtils.isNotBlank(builderValue)){
						Class<?> paramType = beanWriteMethod.getParameterTypes()[0];
						beanWriteMethod.invoke(bean, ConvertUtils.convert(builderValue, paramType));
					}		
				}
			}
		}
	}
	
	
	/**
	 * @Description copy bean properties to builder (only support string type in proto builder)
	 * @param builder dest
	 * @param bean src
	 * @throws Exception
	 * @author caobin
	 */
	public static void bean2Builder(Builder<?> builder, Object bean) throws Exception{
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bean);
		if(propertyDescriptors != null){
			Method beanWriteMethod, beanReadMethod = null;
			for(PropertyDescriptor descriptor : propertyDescriptors){
				if((beanWriteMethod = PropertyUtils.getWriteMethod(descriptor)) != null 
						&& (beanReadMethod = PropertyUtils.getReadMethod(descriptor)) != null){
					Method builderWriteMethod = MethodUtils.getAccessibleMethod(builder.getClass(), beanWriteMethod.getName(), String.class);
					if(builderWriteMethod == null)continue;
					String beanValue = (String)ConvertUtils.convert(beanReadMethod.invoke(bean), String.class);
					if(beanValue != null)builderWriteMethod.invoke(builder, beanValue);
				}
			}
		}
	} 
	
}
