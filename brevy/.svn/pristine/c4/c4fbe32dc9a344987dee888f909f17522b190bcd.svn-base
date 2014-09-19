package com.brevy.spring.wf.biz.hello.wfservice;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserWfService {
	
	@Transactional
	public void hello(){
		runtimeService.startProcessInstanceById("helloProcess");
	}
	
	@Autowired
	private RuntimeService runtimeService;
}
