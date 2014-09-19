<%@ page language="java" contentType="text/html;charset=utf-8" %> 
<%String jsResources = request.getParameter("js") ;%>
<%@ taglib uri="http://packtag.sf.net" prefix="pack" %>
{"js":'<pack:script><%=jsResources%></pack:script>',"success":true}