/**
 * @module 数据字典维护
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.dictMgr.DictMgrUI", {
	extend : "App.Module",
	
	gridID : "DictMgrReadMainGridID",
	
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.appSettings.dictMgr.crud.DictMgrRead", this)
			]
			
		});
	}
});