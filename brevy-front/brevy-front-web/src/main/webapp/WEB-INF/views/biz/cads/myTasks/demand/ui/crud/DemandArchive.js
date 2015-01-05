/**
 * @module 需求评估单归档操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.demand.crud.DemandArchive", {
	extend : "App.Module",
	
	requestUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/demand/archive.json");
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
		        demandId: submitParams
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("DemandReadMainGridID").getEl(),
			loadMaskMsg: Msg.App.deleting,	
			success: function(response){ 
				Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.archivedSuccess, "br");
				Ext.getCmp("DemandReadMainGridID").getSelectionModel().deselectAll();
				DemandDS.reload();
			},
			scope: this
		});
	}
});