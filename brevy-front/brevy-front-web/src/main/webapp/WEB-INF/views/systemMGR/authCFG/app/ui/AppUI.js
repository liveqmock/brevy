/**
 * @module 应用维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.app.AppUI", {
	extend : "App.Module",
	
	init : function(){
		return this.createSimpleInstance("App.systemMGR.authCFG.app.crud.AppRead", this);
	}
});