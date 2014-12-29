/**
 * @module 用户关联应用系统维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.refApp.RefApp", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefAppPanelID",
			title: this.refAppTitle,
			iconCls: Ext.ux.Icons.table_gear,
			layout: "border",
			bodyStyle: Pub.Style.grayBody,
			items: [
				this.createUserInfoPanel(),
				this.createSelectedGrid(),
				this.createCandidateGrid()
			]
		});
	},
	
	//创建角色信息显示Panel
	createUserInfoPanel : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefAppNorthPanelID",
			region: "north",	
			style: {
				paddingLeft: "5px",
				paddingTop: "5px",
				paddingBottom: "5px"
			},
			border: 0,
			bodyStyle: Pub.Style.grayBody,
			html: "N/A"
		});
		
	},
	
	//创建已选Grid
	createSelectedGrid : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "RefAppWestPanelID",
			flex: 1,
			region: "center",
			tbar: this.createSelectedToolbar(),
			store: userRefAppSelectedDS,
			viewConfig: {
		        stripeRows: true
		    },
		    columns: this.createSelectedColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: userRefAppSelectedDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }]
		});
	},
	
	//创建已选Grid的工具栏
	createSelectedToolbar : function(){
		var me = this;
		return [
			{text: this.cancelRefs, iconCls: Ext.ux.Icons.fugue_cross_octagon, handler: function(btn){
				Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, me.confirmCancelAllRefs, null, function(c){
					if(c == "yes"){
						var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
						var count = userRefAppSelectedDS.getCount();
						var appIds = [];
						if(count > 0){
							userRefAppSelectedDS.each(function(r){
								appIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/user/delAppsRefUser.json",
								method: "POST",
								jsonData: {
							        userId: selection.get("id"),
							        appIds: appIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefAppWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									userRefAppSelectedDS.reload();
									userRefAppCandidateDS.reload();
								},
								scope: this
							});
						}	
					}					
				});
			}},
			"-",
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: userRefAppSelectedDS
			}
		];
	},
	
	//创建已选Grid的Columns
	createSelectedColumns : function(){
		var me = this;
		return [
				{xtype: "rownumberer"},
		    	{text: this.appName, dataIndex: "name", flex: 3},
		    	{text: this.appCode, dataIndex: "code", flex: 3},
	            {
	                text: this.refAppWestText,
	                width: 115,
	                menuDisabled: true,
	                xtype: "actioncolumn",
	                tooltip: me.refAppWestTooltip,
	                align: "center",
	                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                	var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
						Ext.Ajax.request({
							url: "../maintenance/user/delAppRefUser.json",
							method: "POST",
							jsonData: {
						        userId: selection.get("id"),
						        appId: record.get("id")
						    },
							loadMask: true,
							loadMaskEl: Ext.getCmp("RefAppWestPanelID").getEl(),
							loadMaskMsg: Msg.App.updating,	
							success: function(response){ 
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
								userRefAppSelectedDS.reload();
								userRefAppCandidateDS.reload();
							},
							scope: this
						});
	                },
	                getClass: function(v, meta, r){
	                	return Ext.ux.Icons.fugue_cross_circle;      	
	                }
	            }
		    ];
	},
	
	//创建待选Grid
	createCandidateGrid : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "RefAppCandidateGridID",
			flex: 1,
			title:"",
			region: "east",
			tbar: this.createCandidateToolbar(),
			store: userRefAppCandidateDS,
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    style:{
		    	borderStyle: "solid",
		    	borderLeft: "20",
		    	borderTop: "0",
		    	borderBottom: "0",
		    	borderRight: "0"
		    },
		    columns: this.createCandidateColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: userRefAppCandidateDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }]
		});	
	},
	
	//创建已选Grid的工具栏
	createCandidateToolbar : function(){
		var me = this;
		return [
			{text: this.addRefs, iconCls: Ext.ux.Icons.fugue_plus_octagon, handler: function(btn){
				Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, me.confirmAddAllRefs, null, function(c){
					if(c == "yes"){
						var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
						var count = userRefAppCandidateDS.getCount();
						var appIds = [];
						if(count > 0){
							userRefAppCandidateDS.each(function(r){
								appIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/user/addAppsRefUser.json",
								method: "POST",
								jsonData: {
							        userId: selection.get("id"),
							        appIds: appIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefAppWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									userRefAppSelectedDS.reload();
									userRefAppCandidateDS.reload();
								},
								scope: this
							});
						}	
					}
					
				});
			}},
			"-",
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: userRefAppCandidateDS
			}
		];
	},
	
	//创建待选Grid的Columns
	createCandidateColumns : function(){
		var me = this;
		return [
			{xtype: "rownumberer"},
		 	{text: this.appName, dataIndex: "name", flex: 3},
		    {text: this.appCode, dataIndex: "code", flex: 3},
		 	{
				text: this.refAppEastActionText,
				width: 115,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refAppEastActionTooltip,
				align: "center",
				handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
					var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
					Ext.Ajax.request({
						url: "../maintenance/user/addAppRefUser.json",
						method: "POST",
						jsonData: {
					        userId: selection.get("id"),
					        appId: record.get("id")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("RefAppCandidateGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							userRefAppSelectedDS.reload();
							userRefAppCandidateDS.reload();
						},
						scope: this
					});
				},
				getClass: function(){
					return Ext.ux.Icons.fugue_plus_circle;                	
				}
	        }        
		];
	}
});