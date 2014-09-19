<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Start Booking</title>
</head>
<body>
	
	<%
	
		Map<String, String> maps = (Map)request.getAttribute("bookingBtns");
		for(Map.Entry<String, String> entry : maps.entrySet()){
			%>
			<input type="button" value="<%=entry.getValue()%>" onclick="javascript:location.href='pay.do?taskId=<%=entry.getKey()%>'" /><br/><br/>
			
			<%
		}
	
	%>
</body>
</html>