package com.brevy.archetype.support.jms.listener;

/**
 * @Description 对象消息消费者监听器(可做类型重载)
 * @author caobin
 * @date 2014-8-21
 * @version 1.0
 */
public class MessageConsumerListener {
	
	public void receiveMessage(String message) {  
        System.out.println("String：" + message);  
    }  
      
    public void receiveMessage(Object message) {  
        System.out.println("Object: " + message);  
    }  
}
