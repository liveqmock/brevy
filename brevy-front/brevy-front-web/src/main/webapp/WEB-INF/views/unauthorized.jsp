<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/extjs.jsp" %>
<html>
<head>
	<style type="text/css">
		.bodyBackground {
			overflow:hidden;
			height: 100%;
			background: url('<%=getServletContext().getContextPath()%>/resources/extjs/images/background/errorPageBackground.jpg') ;
			background-repeat: no-repeat;
			background-size: 100% 100%;
		}
	</style>
</head>
<body class="bodyBackground" >
<div style="position: relative; width:100%; height:100%;"> 
<p align="center">
	<img width="85%" height="100%" alt="" src="<%=getServletContext().getContextPath()%>/resources/extjs/images/background/403.png">
	<span style="position: absolute; top: 65%; left: 46.5%;"><a style="font-family:微软雅黑;font-size:18pt;color: #0091e4" href="<%=getServletContext().getContextPath()%>/default.html">返回首页</a></span>
</p>
 
</div>
	
</body>
</html>