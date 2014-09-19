/**
 * @module 功能菜单维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.funcMenu.FuncMenuUI", {
	extend : "App.Module",
	
	moduleLocPrefix: "/WEB-INF/views/",
	
	formUrl: "/maintenance/funcmenu/save.json",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {	
			layout: "border",
			items: [this.createWestTreePanel(), this.createCentralFormPanel()]
		});	
	},
	
	//获取tbar的store
	getTbarStore : function(){
		var store = App.login.LoginStore.getAppStore(true);
		store.on("load", function(){
			Ext.getCmp("wtp.tbar").setValue(this.params.appId - 0);
			Ext.getCmp("iif.form").hide();
		}, this);
		return store;
	},
	
	//创建树面板
	createWestTreePanel : function(){
		
		return {
			id: "wtp.panel",
			region: "west",
			collapsible : true,	
			split : true,
			title: this.funcMenu,
			xtype: "treepanel",
			useArrows: true,
			viewConfig: {
			    loadMask: {msg: Msg.App.menuLoading},
			    draggable: false,
                plugins: [
                    Ext.create("Ext.tree.plugin.TreeViewDragDrop", {
                        ptype: "treeviewdragdrop",
                        ddGroup: "treeTOtree",
                        enableDrop: true
                    })
                        
                ],
                listeners : {
                	beforedrop: {
                		fn : function(node, data, overModel, dropPosition, dropHandlers, e){
                			var n = data.records[0].data;
                			dropHandlers.wait = true;
                			if(n.parentId != overModel.data.parentId || dropPosition == "append"){
                				Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, this.cannotMoveItemUsingSkipLv);
                				dropHandlers.cancelDrop();
                			}else{
                				dropHandlers.processDrop();
                				this.reloadParentDS();
                			}
                		},
                		scope : this
                		
                	},
                	drop: {
                		fn : function(node, data){
	                		var n = data.records[0].data;  
							Ext.Ajax.request({
								url: "../maintenance/funcmenu/dragAndDrop.json",
								method: "POST",
								jsonData: {
							        id: n.id,
							        parentId: n.parentId,
							        sort: n.index + 1
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("wtp.panel").getEl(),
								loadMaskMsg: this.updateAndRearrangeNodesWaitMsg,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
									var r = eval("(" + response.responseText + ")");
									var node = treeDS.getNodeById(r.RSP_BODY.refreshNodeId);
									treeDS.load({node: node});
									this.formClear();
								},
								scope: this
							});
	                	},
                		scope : this
                	
                	}
                }
			},
			tools:[
				{
				    type: "collapse",
				    tooltip: this.collapseMenuitems,
				    callback: function(o) {
				       o.collapseAll();
				    }
				},
				{
				    type: "expand",
				    tooltip: this.expandMenuitems,
				    callback: function(o) {
				        o.expandAll();
				    }
				}
			],
			tbar: [{
			  	fieldLabel: this.currentAppname,
			  	labelWidth: Ext.isChrome ? 80 : 70,
			  	id: "wtp.tbar",
				name: "appid",
				xtype: "combo",
				triggerAction: "all",
				forceSelection: true,
				editable: false,
				queryMode: "local",
				store: this.getTbarStore(),
				displayField: "name",
				valueField: "id",
				listeners: {
					change: {
						fn: function(o, newVal, oldVal){
							this.formClear();
							if(oldVal){
								var store = Ext.getCmp("wtp.panel").getStore();
								var node = store.getRootNode();
								store.getProxy().setExtraParam("appId", newVal)
								store.load({node: node});
								this.reloadParentDS();
							}	
							Ext.getCmp("iif.form").hide();
						},
						scope: this
					
					}
				}
			}],
		    width: 270,
		    store: treeDS,
		    rootVisible: true,
		    listeners : {
		    	select: {
		    		fn: function(m, r){
				    		var n = r.data;
				    		if(n.root){
				    			this.formClear();
				    			Ext.getCmp("iif.form").hide();
				    			return;
				    		}else{
				    			Ext.getCmp("iif.form").show();
				    		};
				    		//通过setValues设置后，reset时可不重置
				    		Ext.getCmp("iif.form").getForm().setValues([    			
				    			{id: "cfp.appId", value: n.appId},
				    			{id: "cfp.id", value: n.id},
				    			{id: "cfp.opid", value: ""},
				    			{id: "cfp.parentId", value: n.parentId},
				    			{id: "cfp.hierarchy", value: n.hierarchy},
				    			{id: "cfp.name", value: n.text.split("&nbsp;").shift()},
				    			{id: "cfp.moduleLocation", value: n.moduleLocation ? n.moduleLocation : this.moduleLocPrefix},
				    			{id: "cfp.icon", value: n.icon},
				    			{id: "cfp.leaf", value: n.leaf ? "1" : "0"},
				    			{id: "cfp.status", value: n.status},
				    			{id: "cfp.sort", value: n.sort}
				    		]);
				    	},
				    scope: this
		    	},
		    	
		    	itemcontextmenu: {
		    		fn: function(o, record, item, index, e){
		    			var me = this;
		    			e.preventDefault();
		    			var pnode = record;
		    			if(record.isRoot()){
		    				Ext.create("Ext.menu.Menu", {
			    				items: [{
			    					iconCls: Ext.ux.Icons.folder_add,
			    					text: this.addParentMenuitem,
			    					handler: function(){
			    						me.addMenuHandler(pnode);
			    					}
			    				},{
			    					iconCls: Ext.ux.Icons.page_add,
							        text: this.addMenuitem,
			    					handler: function(){
			    						me.addMenuHandler(pnode, true);
			    					}
							    }]
							}).showAt(e.getXY());
		    			}else if(record.isLeaf()){
		    				Ext.create("Ext.menu.Menu", {
			    				items: [{
							    	iconCls: Ext.ux.Icons.page_delete,
							        text: this.delMenuitem,
							        handler: function(){
							        	me.deleteMenuHandler(pnode, true)
							        }
							    }]
							}).showAt(e.getXY());
		    			}else{
		    				Ext.create("Ext.menu.Menu", {
			    				items: [{
			    					iconCls: Ext.ux.Icons.folder_add,
			    					text: this.addParentMenuitem,
			    					handler: function(){
			    						me.addMenuHandler(pnode);
			    					}
			    				},{
			    					iconCls: Ext.ux.Icons.page_add,
							        text: this.addMenuitem,
							        handler: function(){
			    						me.addMenuHandler(pnode, true);
			    					}
							    }, {
							    	iconCls: Ext.ux.Icons.folder_delete,
							        text: this.delMenuitem,
							        handler: function(){
							        	me.deleteMenuHandler(pnode)
							        }
							    }]
							}).showAt(e.getXY());
		    			}
		    			
						
		    		},
		    		scope: this
		    	}
		    }
		}
	},
	
	/**
	 * @description 新增菜单
	 * @param {object} pnode 父节点
	 * @param {boolean} leaf 叶子节点类型 
	 * @author caobin
	 */
	addMenuHandler : function(pnode, leaf){
		var me = this;
		pnode = pnode || treeDS.getRootNode();
		Ext.Ajax.request({
			url: "../maintenance/funcmenu/addMenu.json",
			method: "POST",
			jsonData: {
				appId: Ext.getCmp("wtp.tbar").getValue(),
		        parentId: pnode.data.id,
		        name: this.newNode,
		        leaf: leaf ? "1" : "0",
		        hierarchy: pnode.data.depth + 1,
		        status: "1",
		        moduleLocation: this.moduleLocPrefix
		    },
			loadMask: true,
			loadMaskEl: Ext.getCmp("wtp.panel").getEl(),
			loadMaskMsg: this.createNodeWaitMsg,	
			success: function(response){ 
				me.reloadParentDS();
				treeDS.load({node: pnode, callback: function(records){	
					if(records[0] && !records[0].parentNode.isExpanded()){						
						records[0].parentNode.expand();
					}
				}});			
			},
			scope: this
		});
	},
	
	/**
	 * @description 删除菜单
	 * @param {object} pnode 父节点
	 * @param {boolean} leaf 叶子节点类型 
	 * @author caobin
	 */
	deleteMenuHandler : function(pnode, leaf){
		var me = this;
		Pub.MsgBox.showMsgBox(
			Pub.MsgBox.CONFIRM, 
			leaf ? Msg.Prompt.confirmDelNode : Msg.Prompt.confirmDelNodeCascade, 
			"<font color=red>" + pnode.data.text.split("&nbsp;").shift() + "</font>", 
			function(text){
				if(text === "yes"){
					Ext.Ajax.request({
						url: "../maintenance/funcmenu/delMenu.json",
						method: "POST",
						jsonData: {
							appId: Ext.getCmp("wtp.tbar").getValue(),
					        id: pnode.data.id,
					        parentId: pnode.data.parentId,
					        leaf: leaf ? "1" : "0",
					        sort: pnode.data.sort
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("wtp.panel").getEl(),
						loadMaskMsg: this.deleteNodeWaitMsg,	
						success: function(response){ 
							this.formClear();
							me.reloadParentDS();
							treeDS.load({node: pnode.parentNode});							
						},
						scope: this
					});
				}
			}, this
		)
	},
	
	//创建表单面板
	createCentralFormPanel : function(){
		var appId = {
			id: "cfp.appId",
			name: "appId",
			xtype : "hidden",
			value : null
		}
		
		var id = {
			id: "cfp.id",
			name: "id",
			xtype : "hidden",
			value : null
		}
		
		
		var hierarchy = {
			id: "cfp.hierarchy",
			name: "hierarchy",
			xtype : "hidden",
			value : null
		}
		
		var name = {
			id: "cfp.name",
			fieldLabel : this.required(this.name),
			name : "name",
			flex: 1,
			//maxWidth: 420,
			allowBlank : false,
			xtype : "textfield",
			maxLength : 32

		};
		
		var icon = {
			id: "cfp.icon",
			fieldLabel: this.icon,
			name: "icon",
			flex: 1,
			//maxWidth : 420,
			xtype: "iconcombo",
			editable: false,
			store: menuIconStore,
			valueField: "code",
			displayField: "name",
			iconClsField: "code",
			triggerAction: "all",
			mode: "local",
			plugins: ["clearbutton"]	
		};

		var moduleLocation = {
			id: "cfp.moduleLocation",
			fieldLabel : this.moduleLocation,
			name : "moduleLocation",
			flex: 1, 
			//maxWidth : 840,
			newLine : true,	
			xtype : "textfield",
			value : this.moduleLocPrefix,
			maxLength : 384
		};
		
		var opid = {
			id: "cfp.opid",
			xtype : "hidden",
			value : null
		}
		
		var parentId = {
			id: "cfp.parentId",
			fieldLabel: this.required(this.parentId),
			name: "parentId",
			flex: 1, 
			newLine : true,
			xtype: "treecombo",
			allowBlank : false,
			//maxWidth: 420,
			treeHeight: 200,
		    store: treeComboDS,
		    matchFieldWidth: true,
		    selectChildren: false,
		    canSelectFolders: true,
		    listeners: {
		    	change: function(t, n, o){
		    		Ext.getCmp("cfp.opid").setValue(o || n);
		    		if(n){
		    			Ext.getCmp("cfp.hierarchy").setValue(t.records[0] ? t.records[0].data.depth + 1 : 1);
		    		}
		    		
		    	}
		    }
		}
		
		var leaf = {
			id: "cfp.leaf",
			fieldLabel: this.required(this.leaf),
			name: "leaf",
			flex: 1,
			allowBlank : false,
			xtype: "combo",
			editable: false,
			store: leafStore,
			//maxWidth: 420,
			valueField: "code",
			displayField: "name",
			triggerAction: "all",
			mode: "local"
		}	
		
		var status = {
			id: "cfp.status",
			fieldLabel: this.required(this.status),
			name: "status",
			flex: 1,
			newLine : true,
			allowBlank : false,
			//maxWidth: 420,
			xtype: "combo",
			editable: false,
			store: statusStore,
			valueField: "code",
			displayField: "name",
			triggerAction: "all",
			mode: "local"
		}
		
		var sort = {
			id: "cfp.sort",
			fieldLabel: this.required(this.sort),
			name: "sort",
			flex: 1,
			allowBlank : false,
			//maxWidth: 420,
			xtype: "numberfield",
			minValue: 1,
			maxValue: 9999,
			allowDecimals: false
		}
		
		var saveBtn = {
			text: Msg.App.save,
			width : 100,
			style : {marginRight: "30px;"},
			formBind : true,
			handler : function(b) {
				this.formSubmit(b.up("form").getForm());
			},
			scope: this
		};
						
		var resetBtn = {
			text: Msg.App.reset,	
			width : 100,
			handler : function(b) {
				b.up("form").getForm().reset();
			},
			scope : this
		}

		
		var itemsInfoForm = {	
			id: "iif.form",
			url: this.basePath + this.formUrl,
			method: "POST",
			jsonSubmit: true,
			// If set to true, reset() resets to the last loaded or setValues() data instead of when the form was first created.
			trackResetOnLoad: true,
			flex: 1,
			border: false,
			width: "100%",
			height: 190,
			frame : false,
			bodyStyle: Pub.Style.grayBody,
			xtype : "xform",
			fieldSetConfig : {
				title: this.fieldsetTitle,
				collapsible: false
			},
			items:[
				appId, id, hierarchy, name, 
				icon, moduleLocation, opid, parentId, leaf, status, sort
			],
			buttonAlign: "center",
			buttons: [saveBtn, resetBtn]
		};
		
	
		var form = {
			region : "center",
			layout : "hbox",
			xtype: "form",
			bodyStyle: Pub.Style.grayBody,
			title: this.moduleText,
			iconCls: this.moduleIcon,
			items : [itemsInfoForm]
		}
		
		return form;
	},
	
	//表单提交
	formSubmit: function(form) {
		if (form.isValid()) {
			form.submit({
				waitTitle : Msg.Prompt.infoTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {

					Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");
	
					if(Ext.getCmp("cfp.opid").getValue() != Ext.getCmp("cfp.parentId").getValue()){
						Ext.getCmp("wtp.panel").getSelectionModel().deselectAll();
						treeDS.load({node: treeDS.getRootNode()});							
					}else{
						treeDS.load({node: treeDS.getNodeById(Ext.getCmp("cfp.parentId").getValue())});
					}
					this.formClear();
					
					this.reloadParentDS();
				},
				failure : function(form, action) {			
					Pub.MsgBox.showMsgBox(
						Pub.MsgBox.ERROR, action.result.RSP_HEAD.ERROR_MESSAGE);
				},
				scope: this
			});
		}
	},
	
	//重新加载TreeCombo数据源
	reloadParentDS: function(){
		treeComboDS.getProxy().setExtraParam("appId", Ext.getCmp("wtp.tbar").getValue());
		treeComboDS.reload({node: treeComboDS.getRootNode()});
		var tree = Ext.getCmp("cfp.parentId").tree;
		tree.expandAll();
		tree.getSelectionModel().deselectAll();
	},
	
	//清空IconCombo
	clearIconCombo: function(){
		Ext.getCmp("cfp.icon").setValue("");
	},
	
	//清空FORM栏位
	formClear: function(){
		Ext.getCmp("iif.form").getForm().setValues([    			
			{id: "cfp.appId", value: ""},
			{id: "cfp.id", value: ""},
			{id: "cfp.opid", value: ""},
			{id: "cfp.parentId", value: ""},
			{id: "cfp.hierarchy", value: ""},
			{id: "cfp.name", value: ""},
			{id: "cfp.moduleLocation", value: this.moduleLocPrefix},
			{id: "cfp.icon", value: ""},
			{id: "cfp.leaf", value: ""},
			{id: "cfp.status", value: ""},
			{id: "cfp.sort", value: ""}
		]);
		Ext.getCmp("iif.form").getForm().reset();
	}
});