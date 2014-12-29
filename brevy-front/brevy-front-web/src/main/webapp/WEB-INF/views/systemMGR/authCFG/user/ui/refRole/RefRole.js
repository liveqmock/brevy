/**
 * @module 用户关联角色维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.refRole.RefRole", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefRolePanelID",
			title: this.refRoleTitle,
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
			id: "RefRoleNorthPanelID",
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
			id: "RefRoleWestPanelID",
			flex: 1,
			region: "center",
			tbar: this.createSelectedToolbar(),
			store: userRefRoleSelectedDS,
			viewConfig: {
		        stripeRows: true
		    },
		    columns: this.createSelectedColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: userRefRoleSelectedDS,
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
						var count = userRefRoleSelectedDS.getCount();
						var roleIds = [];
						if(count > 0){
							userRefRoleSelectedDS.each(function(r){
								roleIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/user/delRolesRefUser.json",
								method: "POST",
								jsonData: {
							        userId: selection.get("id"),
							        roleIds: roleIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefRoleWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									userRefRoleSelectedDS.reload();
									userRefRoleCandidateDS.reload();
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
				store: userRefRoleSelectedDS
			}
		];
	},
	
	//创建已选Grid的Columns
	createSelectedColumns : function(){
		var me = this;
		return [
				{xtype: "rownumberer"},
		    	{text: this.roleName, dataIndex: "name", flex: 3},
		    	{text: this.roleCode, dataIndex: "code", flex: 3},
	            {
	                text: this.refRoleWestText,
	                width: 115,
	                menuDisabled: true,
	                xtype: "actioncolumn",
	                tooltip: me.refRoleWestTooltip,
	                align: "center",
	                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                	var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
						Ext.Ajax.request({
							url: "../maintenance/user/delRoleRefUser.json",
							method: "POST",
							jsonData: {
						        userId: selection.get("id"),
						        roleId: record.get("id")
						    },
							loadMask: true,
							loadMaskEl: Ext.getCmp("RefRoleWestPanelID").getEl(),
							loadMaskMsg: Msg.App.updating,	
							success: function(response){ 
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
								userRefRoleSelectedDS.reload();
								userRefRoleCandidateDS.reload();
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
			id: "RefRoleCandidateGridID",
			flex: 1,
			title:"",
			region: "east",
			tbar: this.createCandidateToolbar(),
			store: userRefRoleCandidateDS,
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
		        store: userRefRoleCandidateDS,
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
						var count = userRefRoleCandidateDS.getCount();
						var roleIds = [];
						if(count > 0){
							userRefRoleCandidateDS.each(function(r){
								roleIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/user/addRolesRefUser.json",
								method: "POST",
								jsonData: {
							        userId: selection.get("id"),
							        roleIds: roleIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefRoleWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									userRefRoleSelectedDS.reload();
									userRefRoleCandidateDS.reload();
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
				store: userRefRoleCandidateDS
			}
		];
	},
	
	//创建待选Grid的Columns
	createCandidateColumns : function(){
		var me = this;
		return [
			{xtype: "rownumberer"},
		 	{text: this.roleName, dataIndex: "name", flex: 3},
		    {text: this.roleCode, dataIndex: "code", flex: 3},
		 	{
				text: this.refRoleEastActionText,
				width: 115,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refRoleEastActionTooltip,
				align: "center",
				handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
					var selection = Ext.getCmp("UserReadMainGridID").getSelectionModel().getSelection()[0];
					Ext.Ajax.request({
						url: "../maintenance/user/addRoleRefUser.json",
						method: "POST",
						jsonData: {
					        userId: selection.get("id"),
					        roleId: record.get("id")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("RefRoleCandidateGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							userRefRoleSelectedDS.reload();
							userRefRoleCandidateDS.reload();
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