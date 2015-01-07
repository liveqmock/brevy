/**
 * @module 表重置
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.maintenance.TableResetUI", {
	extend : "App.Module",
	
	gridID : "TableResetReadMainGridID",
	
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.appSettings.maintenance.crud.TableResetRead", this)
			]
			
		});
	}
});