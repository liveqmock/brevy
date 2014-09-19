Ext.QuickTips.init(); 
			Pub.utils.EffectUtils.fieldTips("side");	 
			Pub.resource.Loader.loadModule("/WEB-INF/pages/core/index/module.xml", function(c){				
				var moduleObj = Pub.utils.ReflectionUtils.newInstance(c.moduleName, c);		
				Pub.resource.Loader.jsPack(moduleObj.jsPack, function() {
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
			});