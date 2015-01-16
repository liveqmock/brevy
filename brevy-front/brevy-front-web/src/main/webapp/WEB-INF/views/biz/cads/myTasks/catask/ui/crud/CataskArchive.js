/**
 * @module 综合管理任务归档操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.catask.crud.CataskArchive", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/catask/archive.json");
	},
	
	init : function(){
		var selectedIDs = this.params;
		var submitParams = "";
		for(var i = 0; i < selectedIDs.length; i++){
			submitParams += (selectedIDs[i].get("id") + ",");
		}
		submitParams = submitParams.replace(/\,$/ig,"");
		Ext.Ajax.request({
			url: this.requestUrl(),
			method: "POST",
			jsonData: {
		        cataskId: submitParams
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("CataskReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.archivedSuccess, "br");
				Ext.getCmp("CataskReadMainGridID").getSelectionModel().deselectAll();
				CataskDS.reload();
			},
			scope: this
		});
	}
});