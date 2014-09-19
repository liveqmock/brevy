<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.pageTitle}</title>
<style type="text/css">
#loading {
	height: auto;
	position: absolute;
	left: 35%;
	top: 40%;
	padding: 2px;
}

#after-loading {
	height: auto;
	position: absolute;
	left: 38%;
	top: 40%;
	width: 300px;
	padding: 2px;
}

#animDiv {
	position: absolute;
	left: 99%;
	top: 1%;
}

#loading .loading-indicator {
	color: #444;
	font: bold 25px sans-serif, Helvetica, Arial;
	height: auto;
	margin: 0;
	padding: 10px;
}

#after-loading .loading-indicator {
	color: #444;
	font: bold 25px sans-serif, Helvetica, Arial;
	height: auto;
	margin: 0;
	padding: 10px;
}

#loading .loading-msg {
	font-size: 12px;
	font-weight: normal;
}

#after-loading .loading-msg {
	font-size: 12px;
	font-weight: normal;
}


</style>
</head>
<body>
	<shiro:notAuthenticated>
		<div id="loading">
			<div class="loading-indicator">
				<img src="resources/extjs/images/senchaanim/senchaanim120.gif"
					width="64"
					style="margin-right: 8px; float: left; vertical-align: top;" />
				BREVY - 应用展示 <br /> <span class="loading-msg">正在加载资源，请稍候...</span>
				<%@ include file="/common/extjs.jsp"%>
			</div>
		</div>
		<!-- 未验证 -->
		<pack:script src="/WEB-INF/pages/core/default/notAuthenticated.js" />
	</shiro:notAuthenticated>
	
	<shiro:authenticated>
		<div id="after-loading">
			<div class="loading-indicator">
				<img src="resources/extjs/images/extanim/extanim64.gif"
					width="64"
					style="margin-right: 8px; float: left; vertical-align: top;" />
				正在进入首页 <br /> <span class="loading-msg">正在加载资源，请稍候...</span>
				<%@ include file="/common/extjs.jsp"%>
			</div>
		</div>
		<!-- 已验证 -->
		<pack:script src="/WEB-INF/pages/core/default/authenticated.js" />
	</shiro:authenticated>
	
</body>
</html>