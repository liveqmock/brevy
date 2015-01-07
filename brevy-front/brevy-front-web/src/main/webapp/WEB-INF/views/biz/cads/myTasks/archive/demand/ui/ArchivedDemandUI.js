/**
 * @module 技术中心已归档需求评估单
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.archive.demand.ArchivedDemandUI", {
	extend : "App.Module",	
	gridID : "ArchivedDemandReadMainGridID",
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.archive.demand.crud.ArchivedDemandRead", this)
			]
			
		});	
	}
});