/**
 * @module 工单归档操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.crud.GDArchive", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/gd/archive.json");
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
		        gdId: submitParams
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("GDReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.archivedSuccess, "br");
				Ext.getCmp("GDReadMainGridID").getSelectionModel().deselectAll();
				GDDS.reload();
			},
			scope: this
		});
	}
});