/**
 * @module 主页框架模块
 * @author caobin
 */
Ext.define("Brevy.module.core.Home", {
	extend : "Brevy.base.Module", 
	
	init : function(){
		
		var centerPanel = new Ext.tab.Panel({
			id : "_MainFrameID",
			region : "center",
			margins: "0 6 6 0",
		    items: [/*{
		    	//closable : true,
		    	
		    	title : "测试IFRAME",
		    	xtype: "uxiframe",
		    	loadMask : "加载中，请稍候......"
		    }*/]
		});
		
		centerPanel.openTab = function(panel, id) {
			var o = (typeof panel == "string" ? panel : id || panel.id);
			var tab = this.getComponent(o);
			if (tab) {
				this.setActiveTab(tab);
			} else {
				panel.id = o;
				var p = this.add(panel);
				this.setActiveTab(p);
				
				Ext.getCmp(panel.id).load("Loader.jsp?module=/WEB-INF/pages/core/index/module.xml");
			}
		}
		
		centerPanel.closeTab = function(panel, id) {
			var o = (typeof panel == "string" ? panel : id || panel.id);
			var tab = this.getComponent(o);
			if (tab) {
				this.remove(tab);
			}
		}
		
		
		/*var MainPanel = function() {
			this.openTab = function(panel, id) {
				var o = (typeof panel == "string" ? panel : id || panel.id);
				var tab = this.getComponent(o);
				if (tab) {
					this.setActiveTab(tab);
				} else {
					//this.loading();
					panel.id = o;
					var p = this.add(panel);
					this.setActiveTab(p);
				}
			}
			

			this.closeTab = function(panel, id) {
				var o = (typeof panel == "string" ? panel : id || panel.id);
				var tab = this.getComponent(o);
				if (tab) {
					this.remove(tab);
				}
			}
		}*/

		/*var centerPanel = new MainPanel({		
			id : "mainCenterID",
			xtype : "tab",
			region : "center",
			margins: "0 5 5 0",
			resizeTabs : true,
			minTabWidth : 135,
			tabWidth : 135,
			enableTabScroll : true,
			activeTab : 0,
			//plugins : new Ext.ux.TabCloseMenu(),
			//slideDuration: .15,
			items : [{
        xtype : "component",
        autoEl : {
            tag : "iframe",
            src : "common/Loader.jsp?module=/WEB-INF/pages/core/index/module.xml"
        }
    }*//*{
				closable : false,
				iconCls : Ext.ux.Icons.house,
				title : "测试IFRAME",
				layout : "fit",
				xtype : "uxiframe",
				src : "common/Loader.jsp?module=/WEB-INF/pages/core/index/module.xml"
				
					,
				listeners : {
					load : function() {
						centerPanel.loaded();
					}
				}
			}]
		});*/
		
		var panel = {
			id : "detention",
			title : "测试2",
			animCollapse : false,
			constrainHeader : true,
			layout : "fit",
			border : false,
			closable : true,
			xtype : "uxiframe"
		};
		//Ext.getCmp("_MainFrameID").openTab(panel);
		var store = Ext.create('Ext.data.TreeStore', {
		    root: {
		        expanded: true,
		        children: [
		            { text: "detention", leaf: true },
		            { text: "homework", expanded: true, children: [
		                { text: "book report", leaf: true },
		                { text: "algebra", leaf: true}
		            ] },
		            { text: "buy lottery tickets", leaf: true }
		        ]
		    }
		});


		
		
		var tree = new Ext.tree.TreePanel({
			title : "tree",
			border : true,
			region : "west",
			collapsible : true,
			split : true,
			collapseMode : "mini",
			margins: "0 0 5 5",
			// hideCollapseTool : true,
			useArrows : true,
			width : 210,
			height : Ext.getBody().getViewSize().height,
			animate : true,
			animCollapse : true,
			autoScroll : true,
			store : store,
			rootVisible : false//,
			/*loader : new Ext.tree.TreeLoader({
				dataUrl : "ext/staticTreeListQuery.biz"
			}),*/
			/*root : new Ext.tree.AsyncTreeNode({
				expanded : true,
				listeners : {
					beforeload : function(n) {
						tree.body.mask(pub.all.resource.common.loading,
								"x-mask-loading")
					},
					load : function(n) {
						//tree.body.unmask();
					}
				}
			})*/,
			listeners: {
				
			}
		});
		
		tree.on("itemclick", function(n){
			var panel = {
				id : "home_id2",
				title : "测试2",
				animCollapse : false,
				constrainHeader : true,
				layout : "fit",
				border : false,
				closable : true,
				xtype : "uxiframe"
			};
			
			
			Ext.getCmp("_MainFrameID").openTab(panel);
		})
		
		
		Ext.getBody().createChild({
			tag : "div",
			id : "top",
			html : '<table width="100%"><tr>'
					+ '<td>' 
					/*+ '<img src="images/logos/extjs.gif">'
					+ '<img src="images/logos/struts2.gif">'
					+ '<img src="images/logos/hibernate.gif">'
					+ '<img src="images/logos/spring.gif">' */
					+ '</td>'
					+ '<td align="right"><td/></table>'/*,
			style : 'height:100%; BACKGROUND-IMAGE: url(images/others/topbg.gif); BACKGROUND-REPEAT: repeat-x'*/
		});
	
		var panel = null;
		
		
		//centerPanel.loading();// home page loading
		
		
		var viewport = new Ext.container.Viewport({
			layout : "border",
			items : [{
				margins: "0 0 5 0",
				region : "north",
				border : false,
				contentEl : "top",
				height : 40
			}, tree, centerPanel]
		});	
	/*	
		var o = (typeof panel == "string" ? panel : id || panel.id);
				var tab = this.getComponent(o);
				if (tab) {
					this.setActiveTab(tab);
				} else {
					//this.loading();
					panel.id = o;
					var p = this.add(panel);
					this.setActiveTab(p);
				}*/
		
		var panel = {
			id : "home_id",
			title : "测试",
			animCollapse : false,
			constrainHeader : true,
			layout : "fit",
			border : false,
			closable : true,
			xtype : "uxiframe"
		};
		
		//Ext.getCmp("_MainFrameID").items.getAt(0).load("Loader.jsp?module=/WEB-INF/pages/core/index/module.xml");
		Ext.getCmp("_MainFrameID").openTab(panel);
		//if(Ext.isGecko)
		//	tree.setHeight(tree.getSize().height + 1.5);//调整ff的treepanel高度
	}
});

