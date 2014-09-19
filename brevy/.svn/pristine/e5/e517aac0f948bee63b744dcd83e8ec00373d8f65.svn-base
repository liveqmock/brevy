package com.brevy.spring.cxf.ws.services.fc.calculator.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessInInterceptor extends AbstractPhaseInterceptor<Message> {

	public AccessInInterceptor(){
		super(Phase.RECEIVE);
	}
	
	@Override
	public void handleMessage(Message message) throws Fault {
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		String ipAddress = request.getRemoteAddr();
		log.debug(">>>>> Get remote ip address: {}", new Object[]{ipAddress});
		
	}

	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
