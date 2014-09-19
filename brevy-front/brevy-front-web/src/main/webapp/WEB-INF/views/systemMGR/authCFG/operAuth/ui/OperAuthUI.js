/**
 * @module 操作权限维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.operAuth.OperAuthUI", {
	extend : "App.Module",
	
	init : function(){
		return this.createSimpleInstance("App.systemMGR.authCFG.operAuth.crud.OperAuthRead", this);
	}
});