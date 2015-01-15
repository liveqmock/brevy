/**
 * @module 技术中心综合管理任务UI
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.catask.CataskUI", {
	extend : "App.Module",	
	gridID : "CataskReadMainGridID",
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.catask.crud.CataskRead", this)
			]
			
		});	
	}
});