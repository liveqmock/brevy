package com.brevy.archetype.support.jms.converter;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.util.ObjectUtils;

/**
 * @Description 对象消息转换器
 * @author caobin
 * @date 2014-8-21
 * @version 1.0
 */
public class ObjectMessageConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		if(!(object instanceof Serializable)){
			throw new MessageConversionException(String.format(MESSAGE_CONVERSION_EX, ObjectUtils.nullSafeClassName(object)));
		}
		return session.createObjectMessage((Serializable)object);
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		return ((ObjectMessage)message).getObject();
	}
	
	private final static String MESSAGE_CONVERSION_EX = "Cannot convert object of type [%s] to JMS message. Supported message payloads is: Serializable object.";
}
