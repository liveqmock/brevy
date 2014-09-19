/**
 * @module 角色删除操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.role.crud.RoleDelete", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/maintenance/role/delete.json");
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
			loadMaskEl: Ext.getCmp("RoleReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.delSuccess, "br");
				Ext.getCmp("RoleReadMainGridID").getSelectionModel().deselectAll();
				roleDS.reload();
			},
			scope: this
		});
	}
});