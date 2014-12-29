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
    		if(rs.length == 0 && !store.filters.items[0]){
    			Ext.getCmp("RefAppCandidateGridID").hide();  
    		}else{
    			Ext.getCmp("RefAppCandidateGridID").show();  
    		}
    	}
    }
});


/** 用户关联用户组部分DS **/
Ext.define("UserRefGroupModel", {
    extend: "Ext.data.Model",
    fields:  [
    	"id", 
    	"name", 
    	"code",
    	"desc",
    	"status"
    ]
}); 
var userRefGroupSelectedDS = Ext.create("Ext.data.Store", {
	model: UserRefGroupModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/user/getRefUserGroup.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});



var userRefGroupCandidateDS = Ext.create("Ext.data.Store", {
	model: UserRefGroupModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/user/getCandidateGroup.json",
       	reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20,
    listeners : {
    	load : function(store, rs){
    		if(rs.length == 0 && !store.filters.items[0]){
    			Ext.getCmp("RefGroupCandidateGridID").hide();  
    		}else{
    			Ext.getCmp("RefGroupCandidateGridID").show();  
    		}
    	}
    }
});