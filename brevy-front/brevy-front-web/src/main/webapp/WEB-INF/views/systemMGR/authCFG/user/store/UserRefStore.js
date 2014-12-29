/** 用户关联应用系统部分DS **/
Ext.define("UserRefAppModel", {
    extend: "Ext.data.Model",
    fields:  [
    	"id", 
    	"name", 
    	"code",
    	"desc",
    	"status"
    ]
}); 
var userRefAppSelectedDS = Ext.create("Ext.data.Store", {
	model: UserRefAppModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/user/getRefUserApp.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});



var userRefAppCandidateDS = Ext.create("Ext.data.Store", {
	model: UserRefAppModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/user/getCandidateApp.json",
       	reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20,
    listeners : {
    	load : function(store, rs){
    		if(rs.length == 0){
    			Ext.getCmp("RefAppCandidateGridID").hide();  
    		}else{
    			Ext.getCmp("RefAppCandidateGridID").show();  
    		}
    	}
    }
});