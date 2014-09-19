package com.brevy.spring.wf.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WfServlet extends HttpServlet{

	private static final long serialVersionUID = -3277589795531461185L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String processKey = req.getParameter("process");	
		String user = req.getParameter("user");
		String startNewProcess = req.getParameter("isNew");
		
		
		Map<String, String> groupMap = new HashMap<String, String>();
		groupMap.put("user", "userGroup");
		groupMap.put("manager", "managerGroup");
		
		
		String group = groupMap.get(user);
		log.info(">>>>>>>>>> 获取组：{}", new Object[]{group});
		
		
		//HistoryService historyService = getBean(req, "historyService", HistoryService.class);
		//HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processKey).singleResult();

		if("1".equals(startNewProcess)){
			RuntimeService runtimeService = getBean(req, "runtimeService", RuntimeService.class);	
			runtimeService.startProcessInstanceByKey(processKey);
			log.info(">>>>>>>>>> 启动流程：{}", new Object[]{processKey});
		}
		
		
		//
		/*TaskService taskService = getBean(req, "taskService", TaskService.class);
		List<Task> tasks = taskService.createTaskQuery().list();
		
		StringBuilder taskbuilder = new StringBuilder();
		
		if(tasks != null && !tasks.isEmpty()){
			for(Task task : tasks){
				taskbuilder.append("[").append(task.getName()).append("]&nbsp;");
			}	
		}*/
		
		//resp.getWriter().print(runtimeService.createProcessInstanceQuery().count());
		
		TaskService taskService = getBean(req, "taskService", TaskService.class);
		
		
		
	//	taskService.
		
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).list();	
	
		if(CollectionUtils.isNotEmpty(tasks)){
			for(Task task : tasks){
				if("1".equals(startNewProcess)){
					taskService.setVariable(task.getId(), "userKey", "测试流程变量");
					Map<String, Object> mv = new HashMap<String, Object>();
					taskService.setVariable(task.getId(), "userKey2", mv);
					log.info(">>>>>>>>>>存入流程变量");
				}else{
					log.info(">>>>>>>>>>取出流程变量:{}", new Object[]{taskService.getVariable(task.getId(), "userKey")});
				}
				taskService.claim(task.getId(), user);
				log.info(">>>>>>>>>>用户[{}]认领了任务[{}]", new Object[]{user, task.getName()});
			}	
		}
		
		
		tasks = taskService.createTaskQuery().taskAssignee(user).list();
		if(CollectionUtils.isNotEmpty(tasks)){
			for(Task task : tasks){
				taskService.complete(task.getId());
				log.info(">>>>>>>>>>用户[{}]已完成任务[{}]", new Object[]{user, task.getName()});
			}
		}
	}
	
	private static<T> T getBean(HttpServletRequest req, String beanName, Class<T> beanClazz) {
		ApplicationContext appctx = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		return appctx.getBean(beanName, beanClazz);
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
