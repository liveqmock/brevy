/**
 * @module 字典删除操作
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.dictMgr.crud.DictDetailMgrDelete", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/biz/cads/appSettings/dictDetailMgr/delete.json");
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
			loadMaskEl: Ext.getCmp("DictDetailMgrReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.delSuccess, "br");
				Ext.getCmp("DictDetailMgrReadMainGridID").getSelectionModel().deselectAll();
				dictDetailMgrDS.reload();
			},
			scope: this
		});
	}
});