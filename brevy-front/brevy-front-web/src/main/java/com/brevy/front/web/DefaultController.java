package com.brevy.front.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brevy.core.support.web.BaseController;

@Controller
public class DefaultController extends BaseController{	
	
	@RequestMapping("/index")
	public void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/views/default.jsp").forward(request, response);
	}
	
	@RequestMapping("/default")
	public String toDefault(){
		return "default";
	}
}
