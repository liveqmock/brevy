/**
 * @module 技术中心需求评估单
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.demand.DemandUI", {
	extend : "App.Module",	
	gridID : "DemandReadMainGridID",
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.demand.crud.DemandRead", this)
			]
			
		});	
	}
});