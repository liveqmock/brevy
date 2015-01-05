<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/extjs/images/ico/favicon.ico" rel="shortcut icon" type="image/x-icon">
<style type="text/css">
	#loading2 {
		height: auto;
		position: absolute;
		left: 35%;
		top: 40%;
		padding: 2px;
	}
	
	#animDiv {
		position: absolute;
		left: 99%;
		top: 1%;
	}
	
	#loading2 .loading-indicator2 {
		color: #444;
		font: bold 25px sans-serif, Helvetica, Arial;
		height: auto;
		margin: 0;
		padding: 10px;
	}
	
	#loading2 .loading-msg {
		font-size: 12px;
		font-weight: normal;
	}


	.default-title-pos {
		margin-top: 10px;
		margin-left: 5px;
	}
	
	.default-title-font {
		font-size: 20px;
		font-weight: bolder;
		color: #da4f49;
	}
	
	.default-subtitle {
		margin-left: 15px;
		font-size: 11px;
		color: #5bb75b;
	}
	
	.default-footer {
		font-size: 12px;
		text-align: center;
		margin-top: 3px;
	}

</style>
<title>综合管理平台</title>


</head>
<body>	
	<div id="loading2">
		<div class="loading-indicator2">
			<img src="resources/extjs/images/extanim/extanim64.gif"
				width="64"
				style="margin-right: 8px; float: left; vertical-align: top;" />
			综合管理平台 <br /> <span class="loading-msg">正在加载资源，请稍候...</span>
			<%@ include file="/common/extjs.jsp"%>
		</div>
	</div>
	<script>
		if(window.parent.length > 0)window.parent.location = location;
		Ext.fly("loading2").hide();	
	</script>
	
	<shiro:authenticated>
		<script>
			Pub.ResLoader.loadModule(ExtRES + "entry/module.xml");		
		</script>	
	</shiro:authenticated>	
	
	<shiro:notAuthenticated>
		<script>
			Pub.ResLoader.loadModule(ExtRES + "login/module.xml");	
		</script>	
	</shiro:notAuthenticated>	
	
	<script>	
		Ext.getBody().applyStyles({
			background: 'url("resources/extjs/images/background/square.gif")'
		});
	</script>
</body>
</html>