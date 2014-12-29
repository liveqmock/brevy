/**
 * @module 用户关联用户组维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.refGroup.RefGroup", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefGroupPanelID",
			title: this.refGroupTitle,
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
	
	//创建用户信息显示Panel
	createUserInfoPanel : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefGroupNorthPanelID",
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
			id: "RefGroupWestPanelID",
			flex: 1,
			region: "center",
			tbar: this.createSelectedToolbar(),
			store: userRefGroupSelectedDS,
			viewConfig: {
		        stripeRows: true
		    },
		    columns: this.createSelectedColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: userRefGroupSelectedDS,
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
						var count = userRefGroupSelectedDS.getCount();
						var groupIds = [];
						if(count > 0){
							userRefGroupSelectedDS.each(function(r){
								groupIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/user/delGroupsRefUser.json",
								method: "POST",
								jsonData: {
							        userId: selection.get("id"),
							        groupIds: groupIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefGroupWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									userRefGroupSelectedDS.reload();
									userRefGroupCandidateDS.reload();
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
				store: userRefGroupSelectedDS
			}
		];
	},
	
	//创建已选Grid的Columns
	createSelectedColumns : function(){
		var me = this;
		return [
				{xtype: "rownumberer"},
		    	{text: this.groupName, dataIndex: "name", flex: 3},
		    	{text: this.groupCode, dataIndex: "code", flex: 3},
	            {
	                text: this.refGroupWestText,
	                width: 115,
	                menuDisabled: true,
	                xtype: "actioncolumn",
	                tooltip: me.refGroupWestTooltip,
	                align: "center",
	                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                	var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
						Ext.Ajax.request({
							url: "../maintenance/user/delGroupRefUser.json",
							method: "POST",
							jsonData: {
						        userId: selection.get("id"),
						        groupId: record.get("id")
						    },
							loadMask: true,
							loadMaskEl: Ext.getCmp("RefGroupWestPanelID").getEl(),
							loadMaskMsg: Msg.App.updating,	
							success: function(response){ 
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
								userRefGroupSelectedDS.reload();
								userRefGroupCandidateDS.reload();
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
			id: "RefGroupCandidateGridID",
			flex: 1,
			title:"",
			region: "east",
			tbar: this.createCandidateToolbar(),
			store: userRefGroupCandidateDS,
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
		        store: userRefGroupCandidateDS,
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
						var count = userRefGroupCandidateDS.getCount();
						var groupIds = [];
						if(count > 0){
							userRefGroupCandidateDS.each(function(r){
								groupIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/user/addGroupsRefUser.json",
								method: "POST",
								jsonData: {
							        userId: selection.get("id"),
							        groupIds: groupIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefGroupWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									userRefGroupSelectedDS.reload();
									userRefGroupCandidateDS.reload();
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
				store: userRefGroupCandidateDS
			}
		];
	},
	
	//创建待选Grid的Columns
	createCandidateColumns : function(){
		var me = this;
		return [
			{xtype: "rownumberer"},
		 	{text: this.groupName, dataIndex: "name", flex: 3},
		    {text: this.groupCode, dataIndex: "code", flex: 3},
		 	{
				text: this.refGroupEastActionText,
				width: 115,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refGroupEastActionTooltip,
				align: "center",
				handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
					var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
					Ext.Ajax.request({
						url: "../maintenance/user/addGroupRefUser.json",
						method: "POST",
						jsonData: {
					        userId: selection.get("id"),
					        groupId: record.get("id")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("RefGroupCandidateGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							userRefGroupSelectedDS.reload();
							userRefGroupCandidateDS.reload();
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