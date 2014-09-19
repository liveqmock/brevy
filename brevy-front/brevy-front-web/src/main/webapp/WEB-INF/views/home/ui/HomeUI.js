/**
 * @module 入口模块起始页
 * @author caobin
 */
Ext.define("App.home.HomeUI", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {			
			bodyStyle: {
			    backgroundImage: "url('../resources/extjs/images/background/desk-white.jpg')",
			    backgroundRepeat: "no-repeat",
				backgroundSize: "100%"
			}
		});	
	}
});