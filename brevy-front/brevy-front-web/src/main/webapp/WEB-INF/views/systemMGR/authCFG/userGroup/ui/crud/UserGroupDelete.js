/**
 * @module 用户组删除操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.userGroup.crud.UserGroupDelete", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/maintenance/userGroup/delete.json");
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
		        ids: submitParams,
		        appId: Ext.getCmp("tb.tbar").getValue()
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("UserGroupReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.delSuccess, "br");
				Ext.getCmp("UserGroupReadMainGridID").getSelectionModel().deselectAll();
				userGroupDS.reload();
			},
			scope: this
		});
	}
});