/**
 * @description 静态菜单栏
 * @author caobin
 */
Ext.define("Ext.ux.StaticMenuBar", {
    extend: "Ext.ux.StatusBar",
    alias: "widget.staticmenubar", 
    
    //请求菜单JSON数据的URL
    url : null,
    //数据模型
    model : null,
    //需要额外提交的参数
    extraParams : {},
    //目标框架ID
    targetFrame : "_MainFrame",
    //显示首页按钮
    showHomeButton : false,
    //首页按钮图标
    homeButtonIconCls : null,
    //首页按钮文字
    homeButtonText : null,
    //首页按钮操作
    homeButtonHandler : Ext.emptyFn,
    //首页按钮操作作用域
    homeButtonHandlerScope : null,
    //显示退出按钮
    showExitButton : false,
    //退出按钮图标
    exitButtonIconCls : null,
    //退出按钮文字
    exitButtonText : null,
    //退出按钮操作
    exitButtonHandler : Ext.emptyFn,
    //退出按钮操作作用域
    exitButtonHandlerScope : null,
    
    moduleHref : _ctxPath + "/common/Loader.jsp?module=",
    
    afterRender : function(){
    	this.callParent(arguments);
    	this.showBusy();
    	Ext.create("Ext.data.Store", {
			model: this.model || this._getDefaultModel(),
			proxy: {
			    type: "jsonajax",
			    url: this.url,
			    extraParams : this.extraParams,
			    reader: {
			        type: "json"
			    }
			},
			autoLoad: true,
			listeners: {
				load : {			
					fn : function(t,r,s,e){
							Ext.each(r, function(n){
					    		this.insert(0, {
					    			 xtype : "splitbutton",
					   				 text : n.get("name"),
					   				 iconCls: n.get("icon"), 
					   				 menu : this._buildMenu(n.get("apMenus"))
					    		});
							}, this, true);
							if(this.showHomeButton){
								this.insert(0, {
					    			 xtype : "button",
					   				 iconCls : this.homeButtonIconCls,
					   				 text : this.homeButtonText,
					   				 listeners : {
					   				 	click : {
					   				 		fn : this.homeButtonHandler,
					   				 		scope : this.homeButtonHandlerScope
					   				 	}
					   				 }
					    		});
							}
							if(this.showExitButton){
								this.add({
					    			 xtype : "button",
					   				 iconCls : this.exitButtonIconCls,
					   				 text : this.exitButtonText,
					   				 listeners : {
					   				 	click : {
					   				 		fn : this.exitButtonHandler,
					   				 		scope : this.exitButtonHandlerScope
					   				 	}
					   				 }
					    		});
							}
							this.clearStatus();
					},				
					scope : this			
				}
			}
		});
    },
    
    /**
	 * @description 获取默认数据模型
	 * @return
	 * @author caobin
	 */
    _getDefaultModel : function(){
    	return Ext.define("_staticMenuBar_Model", {
    		extend: "Ext.data.Model",
	        fields: ["id", "name", "url", "moduleLocation", "icon", "apMenus"],
	        raw: {parentId : -1}
	    });
    },
    
    /**
	 * @description 构造菜单
	 * @param o 菜单数据
	 * @return
	 * @author caobin
	 */
    _buildMenu : function(o){
		if(o == null)return [];
		var result = [];
		Ext.each(o, function(n){
			if(n.apMenus){
				result.push({
					text: n.name, 
					id: n.id.toString(), 
					iconCls: n.icon, 
					menu:this._buildMenu(n.apMenus)
				});
			}else{
				result.push({
					text: n.name, 
					id: n.id.toString(),
					iconCls: n.icon,
					listeners : {				
						click : {
							fn : function(){
								if(n.moduleLocation){
									var extraParams = "moduleText=" + encodeURI(encodeURI(n.name)) + "&moduleIcon=" + n.icon + "&appId=" + n.appId;
									Ext.getCmp(this.targetFrame).load(this.moduleHref + n.moduleLocation + "&" + extraParams);
								}else if(n.url){
									Ext.getCmp(this.targetFrame).load(n.url);
								}			
							},
							scope : this	
						}		
					}, 
					xtype: "menuitem"
				});
			}
		}, this);
		return result;
	}
});