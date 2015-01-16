/**
 * @module 技术中心已归档综合管理任务
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.archive.catask.ArchivedCataskUI", {
	extend : "App.Module",	
	gridID : "ArchivedCataskReadMainGridID",
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.archive.catask.crud.ArchivedCataskRead", this)
			]
			
		});	
	}
});