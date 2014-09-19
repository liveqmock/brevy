<%@ include file="extjs.jsp" %>
<%
  String module = request.getParameter("module");
  String panelID = request.getParameter("panelID");
  String moduleText = request.getParameter("moduleText")!= null ? java.net.URLDecoder.decode(request.getParameter("moduleText"), "UTF-8") : null;
  String moduleIcon = request.getParameter("moduleIcon");
  String appId = request.getParameter("appId");
%> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />   
	</head>
	<body> 
		<script> 
			Pub.ResLoader.loadModule("<%=module%>", function(c){
				if("<%=moduleText%>"){c.moduleText = "<%=moduleText%>";}
				if("<%=moduleIcon%>"){c.moduleIcon = "<%=moduleIcon%>";}
				var moduleObj = Ext.create(c.moduleName, c);
				moduleObj.beforeInit();
				var winConfig = moduleObj.init();
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
			},{appId: "<%=appId%>"});		
		</script>
	</body>
</html>
