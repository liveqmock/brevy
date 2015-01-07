/**
 * @module 表清空操作
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.maintenance.crud.TableReset", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/biz/cads/appSettings/maintenance/resetTables.json");
	},
	
	init : function(){
		var selectedIDs = this.params;
		var submitParams = "";
		for(var i = 0; i < selectedIDs.length; i++){
			submitParams += (selectedIDs[i].get("code") + ",");
		}
		submitParams = submitParams.replace(/\,$/ig,"");
		Ext.Ajax.request({
			url: this.requestUrl(),
			method: "POST",
			jsonData: {
		        tables: submitParams
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("TableResetReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.delSuccess, "br");
				Ext.getCmp("TableResetReadMainGridID").getSelectionModel().deselectAll();
			},
			scope: this
		});
	}
});