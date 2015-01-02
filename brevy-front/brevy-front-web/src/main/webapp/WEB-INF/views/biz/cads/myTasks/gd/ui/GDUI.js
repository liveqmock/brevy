/**
 * @module 技术中心工单
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.GDUI", {
	extend : "App.Module",
	
	gridID : "GDReadMainGridID",
	init : function(){
		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.gd.crud.GDRead", this)
			]
			
		});
		
	}
});