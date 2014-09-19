/**
 * @module 访问权限维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.accessAuth.AccessAuthUI", {
	extend : "App.Module",
	
	init : function(){
		return this.createSimpleInstance("App.systemMGR.authCFG.accessAuth.crud.AccessAuthRead", this);
	}
});