/**
 * @module 应用系统删除操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.app.crud.AppDelete", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/maintenance/app/delete.json");
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
		        ids: submitParams
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("AppReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.delSuccess, "br");
				Ext.getCmp("AppReadMainGridID").getSelectionModel().deselectAll();
				appDS.reload();
			},
			scope: this
		});
	}
});