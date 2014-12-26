/**
 * @module 用户删除操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.crud.UserDelete", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/maintenance/user/delete.json");
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
			loadMaskEl: Ext.getCmp("UserReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.delSuccess, "br");
				Ext.getCmp("UserReadMainGridID").getSelectionModel().deselectAll();
				userDS.reload();
			},
			scope: this
		});
	}
});