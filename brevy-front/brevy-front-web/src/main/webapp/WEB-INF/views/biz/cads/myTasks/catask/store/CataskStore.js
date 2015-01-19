Ext.define("CataskAttachmentModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "cataskId", mapping: "cataskId"},  
	    {name: "path", mapping: "path"}
    ]
});


var CataskAttachmentDS = Ext.create("Ext.data.Store", {
	model: CataskAttachmentModel,
	autoLoad: false,	
	proxy: {
        type: "jsonajax",
        url: "../biz/cads/myTasks/catask/getCataskAttachmentList.json",
        reader: {
            type: "json"
        }
    }
});

//Catask model
Ext.define("CataskModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "operLv", mapping: "operLv"},  
	    {name: "importance", mapping: "importance", type: "int"},
	    {name: "category", mapping: "category", type: "int"},
	    {name: "source", mapping: "source"},
	    {name: "title", mapping: "title"},
	    {name: "reqFinishDate", mapping: "reqFinishDate", convert: Pub.Support.dateConvert},    
	    {name: "jobContent", mapping: "jobContent"},
	    {name: "progress", mapping: "progress"},
	    {name: "finishDate", mapping: "finishDate", convert: Pub.Support.dateConvert},
	    {name: "finishStatus", mapping: "finishStatus", type: "int"},
	    {name: "usingResource", mapping: "usingResource"},
	    {name: "attachType", mapping: "attachType"},
	    {name: "result", mapping: "result", type: "int"},
	    {name: "remark", mapping: "remark"},
	    {name: "userId", mapping: "userId"}
    ]
});


var CataskDS = Ext.create("Ext.data.Store", {
	model: CataskModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/myTasks/catask/getAllCataskList.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});


Ext.define("dictModel", {
	extend: "Ext.data.Model",
   	fields: ["id", "name"]
});


var dictDSConfig = {
	model: dictModel,
	autoLoad: false,	
	proxy: {
	    type: "jsonajax",
	    url: _ctxPath + "/maintenance/user/getDictStore.json",
	    reader: {
	        type: "json"
	    }
	}
}

dictDSConfig.proxy.extraParams = {"dictId": 25};
var dictDS_25 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 26};
var dictDS_26 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 27};
var dictDS_27 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 28};
var dictDS_28 = Ext.create("Ext.data.Store", dictDSConfig);



Ext.define("userModel", {
	extend: "Ext.data.Model",
   	fields: ["id", "chName"]
});

var userDS = Ext.create("Ext.data.Store", {
	model: userModel,
	autoLoad: false,	
	proxy: {
	    type: "jsonajax",
	    url: _ctxPath + "/maintenance/user/getAllUsers.json",
	    reader: {
	        type: "json"
	    }
	}
});

var userMgrDS = null;
Ext.Ajax.request({
    url: _ctxPath + "/maintenance/user/getAllUsers.json",
    async: false,
    success: function(response){
    	userMgrDS = eval("(" + response.responseText + ")")
    }
});

var dictDetailMgrDS = null;
Ext.Ajax.request({
    url: "../biz/cads/appSettings/dictMgr/getAllDictDetailList.json",
    async: false,
    success: function(response){
    	dictDetailMgrDS = eval("(" + response.responseText + ")")
    }
});

