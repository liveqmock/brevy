<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">
<%@ include file="common/extjs.jsp" %>
<%
  String module =request.getParameter("module");
  String panelID = request.getParameter("panelID");
%> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />   
	</head>
	<body> 
		<script>
			Ext.QuickTips.init(); 
			Pub.utils.EffectUtils.fieldTips("side");	 
			Pub.resource.Loader.loadModule("<%=module%>", function(c){				
				var moduleObj = Pub.utils.ReflectionUtils.newInstance(c.moduleName, c);	
				moduleObj.beforeInit(c);
				var winConfig = moduleObj.init(c);
				var config = {
					id : c.moduleName,
					title : null, 
					border : false
				}
				Ext.applyIf(winConfig, config);										
				new Ext.Viewport({
				    layout:"fit",            		 
				    border:false,
				    items:[
				        winConfig
				    ]
				});
				moduleObj.afterInit(winConfig);       	
			
			});		
		</script>
	</body>
</html>
