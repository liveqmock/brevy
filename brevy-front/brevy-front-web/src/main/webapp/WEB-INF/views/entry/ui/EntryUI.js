/**
 * @module 入口模块UI
 * @author caobin
 */
Ext.define("App.entry.EntryUI", {
	
	extend : "Ext.container.Viewport",
	
	_MainFrame : "_MainFrame",
	
	_hasDoLayout : false,
	
	initComponent : function(){
		this.layout = "border";
		this.items = [
			this.createHeaderPanel(),
			this.createCenterPanel(),
			this.createFooterPanel()
		];		
		this.callParent(arguments);
	},
	
	afterComponentLayout : function(){
		this.callParent(arguments);
		if(!this._hasDoLayout){
			Ext.getCmp(this._MainFrame).load(_ctxPath + "/common/Loader.jsp?module=/WEB-INF/views/home/module.xml");
			this._hasDoLayout = true;
		}
		
	},
	
	/**
	 * @description  默认整体框架模块(北部)
	 */
	createHeaderPanel : function(){
		return {
			region : "north",
			xtype : "panel",
			height : 48,
			bodyStyle : {
				backgroundImage : "url('resources/extjs/ext-theme-gray/images/panel-header/panel-header-default-top-bg.gif')",
				backgroundRepeat: "repeat-x"
			},
			html : Msg.App.header
		}
	},
	
	/**
	 * @description  默认整体框架模块(中部)
	 */
	createCenterPanel : function(){
		return {
			region : "center",
			xtype : "panel",
			border : false,
			layout : "border",
			items : [
				this.createTopBar(),
				this.createNestedFrame()
			]
		}
	},
	
	/**
	 * @description 创建顶部工具栏
	 */
	createTopBar : function(){
		return {
			height: 28,
			region: "north",
			xtype : "panel",
			border : false,
			layout : "border",
			items : [
				this.createMenuBar(),
				this.createInfoBar()
			]
		}
	},
	
	/**
	 * @description 创建信息栏
	 */
	createInfoBar : function(){
		return {
			region: "east",
			id : "_infoBar",
			xtype: "statusbar",
			busyText: Msg.App.userLoading,
			border : false,
			items: [
				"",
				{	
					text: "",			
					disabled: true,
					listeners : {
						afterrender : function(){
							this.removeClsWithUI("disabled");
							var me = this;
							Ext.getCmp("_infoBar").showBusy();
							Ext.Ajax.request({
							    url: "auth/getCurrentUser.json",
							    method: "POST",
							    success: function(response){
							    	Ext.getCmp("_infoBar").clearStatus();
							    	me.setIconCls(Ext.ux.Icons.user_gray);
							     	me.setText(Ext.JSON.decode(response.responseText));     	
							    }
							});
						}
					}
				},
				"-",
				{
					iconCls: Ext.ux.Icons.door_out,
					text: Msg.App.exit,
					handler: function(){
						Ext.Ajax.request({
						    url: "auth/logout.json",
						    method: "POST",
						    loadMask: true,
						    loadMaskMsg: Msg.App.exitMsgLoading,
						    success: function(response){
						       location.href = "default.html";
						    }
						});
					}
				}
			]
		}
	},
	
	/**
	 * @description 创建静态菜单栏
	 */
	createMenuBar : function(){	
		var me = this;
		return {
			border : false,
			region: "center",
			xtype: "staticmenubar",
			url: "auth/getStaticMenu.json",
			extraParams: {appId : 1000, rootValue : -1},
			showHomeButton : true,
			homeButtonIconCls : Ext.ux.Icons.house,
			homeButtonText : Msg.App.home,
			homeButtonHandler : function(){
				Ext.getCmp(this._MainFrame).load(_ctxPath + "/common/Loader.jsp?module=/WEB-INF/views/home/module.xml");
			},
			homeButtonHandlerScope : this,		
			busyText: Msg.App.menuLoading,
			manageHeight: false,
			minHeight:28,
			layout: {
			    overflowHandler: "Menu"
			}
		}
	},
	
	/**
	 * @description 创建内部框架
	 */
	createNestedFrame : function(){
		return {
			id: this._MainFrame,
			region: "center",
			loadMask: Msg.App.moduleLoading,
			border: false,
			xtype: "uxiframe"
		}
	},
	
	/**
	 * @description 默认整体框架模块(南部)
	 */
	createFooterPanel : function(){
		return {
			region : "south",
			xtype : "panel",
			height : 26,
			bodyStyle : {
				backgroundImage : this._barImageStyle,
				backgroundRepeat: "repeat-x"
			},
			html : Msg.App.footer
		}
	}
});