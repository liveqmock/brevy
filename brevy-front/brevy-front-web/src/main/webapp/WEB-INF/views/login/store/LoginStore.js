/**
 * @module 登录模块所含Store
 * @author caobin
 */
Ext.define("App.login.LoginStore", {
	
	statics : {
		/** 应用系统Model **/
		getAppModel : function(){
			return Ext.define("App.login.LoginStore.AppModel", {
	    		extend: "Ext.data.Model",
		        fields: ["id", "name"]
		    });
		},
		
		/**
		 * @description 获取应用系统Store
		 * @param autoLoad default is false
		 * @static
		 * @author caobin
		 */
		getAppStore : function(autoLoad){
			return Ext.create("Ext.data.Store", {
				model: this.getAppModel(),
				proxy: {
				    type: "jsonajax",
				    url: _ctxPath + "/login/getAppStore.json",
				    extraParams: null,
				    reader: {
				        type: "json"
				    }
				},
				autoLoad: autoLoad ? autoLoad : false
			});
		}
	}
});
	
