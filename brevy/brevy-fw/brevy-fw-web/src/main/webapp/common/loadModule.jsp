<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.BufferedReader, java.io.InputStreamReader, java.io.FileInputStream"%>
<%@ page import="org.json.XML, org.json.JSONObject"%> 

<%   
    String XMLFile = request.getParameter("xml");
    String isLocal = request.getParameter("isLocal");  
    response.setContentType("text/javascript");
    String xmlString ="";       
    
    try {   
	    	String path = getServletContext().getRealPath("/");
	     	BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path+XMLFile),"utf-8"));
			String line;
			while ((line = file.readLine()) != null)xmlString += line;	   
			out.println(XML.toJSONObject(xmlString).toString()); 
	        file.close();		
  	    } catch (Exception e) {
	        out.println(e);
	        e.printStackTrace();
	  	}  
	  	
%>