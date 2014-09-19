package com.brevy.spring.wf.biz.reservation.servlet;

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

import com.brevy.spring.wf.support.ServletUtils;

public class StartBooking extends HttpServlet {

	private static final long serialVersionUID = -3111061636225819924L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RuntimeService runtimeService = ServletUtils.getBean(req, "runtimeService", RuntimeService.class);	
		runtimeService.startProcessInstanceByKey("process.reservation");
		log.info(">>>>>>>>>> 启动新流程：{}", new Object[]{"process.reservation"});	
		
		TaskService taskService = ServletUtils.getBean(req, "taskService", TaskService.class);
		
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("STEP1").list();
		
		Map<String, String> bookingMap = new HashMap<String, String>();
		
		if(CollectionUtils.isNotEmpty(tasks)){
			for(Task task : tasks){
				bookingMap.put(task.getId(), task.getName());
			}	
		}
		
		req.setAttribute("bookingBtns", bookingMap);
		req.getRequestDispatcher("booking.jsp").forward(req, resp);
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
