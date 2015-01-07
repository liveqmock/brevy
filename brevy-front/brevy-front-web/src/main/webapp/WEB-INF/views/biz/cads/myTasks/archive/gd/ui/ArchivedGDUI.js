/**
 * @module 技术中心已归档工单
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.archive.gd.ArchivedGDUI", {
	extend : "App.Module",	
	gridID : "ArchivedGDReadMainGridID",
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.archive.gd.crud.ArchivedGDRead", this)
			]
			
		});	
	}
});