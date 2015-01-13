Ext.define("GDAttachmentModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "gdId", mapping: "gdId"},  
	    {name: "path", mapping: "path"}
    ]
});


var GDAttachmentDS = Ext.create("Ext.data.Store", {
	model: GDAttachmentModel,
	autoLoad: false,	
	proxy: {
        type: "jsonajax",
        url: "../biz/cads/myTasks/gd/getGDAttachmentList.json",
        reader: {
            type: "json"
        }
    }
});

//GD model
Ext.define("GDModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "name", mapping: "name"},  
	    {name: "recvDate", mapping: "recvDate", convert: Pub.Support.dateConvert},
	    {name: "execType", mapping: "execType", type: "int"},
	    {name: "type", mapping: "type", type: "int"},
	    {name: "briefName", mapping: "briefName"},
	    {name: "priority", mapping: "priority", type: "int"},
	    {name: "requireFinishTime", mapping: "requireFinishTime"},
	    {name: "estimateJob", mapping: "estimateJob"},
	    {name: "preCond", mapping: "preCond"},
	    {name: "implTeam", mapping: "implTeam"},
	    {name: "pmName", mapping: "pmName"},
	    {name: "startDate", mapping: "startDate", convert: Pub.Support.dateConvert},
	    {name: "ini", mapping: "ini", type: "int"},
	    {name: "rdp", mapping: "rdp", type: "int"},
	    {name: "ad", mapping: "ad", type: "int"},
	    {name: "scp", mapping: "scp", type: "int"},
	    {name: "sit", mapping: "sit", type: "int"},
	    {name: "uat", mapping: "uat", type: "int"},
	    {name: "pip", mapping: "pip", type: "int"},
	    {name: "smp", mapping: "smp", type: "int"},
	    {name: "progress", mapping: "progress"},
	    {name: "finishDate", mapping: "finishDate", convert: Pub.Support.dateConvert},
	    {name: "usingResource", mapping: "usingResource"},
	    {name: "usingTime", mapping: "usingTime"},
	    {name: "attachType", mapping: "attachType"},
	    {name: "remark", mapping: "remark"},
	    {name: "devFinishDate", mapping: "devFinishDate", convert: Pub.Support.dateConvert},
	    {name: "sitWorkload", mapping: "sitWorkload"},
	    {name: "sitFinishDate", mapping: "sitFinishDate", convert: Pub.Support.dateConvert},
	    {name: "status", mapping: "status", type: "int"},
	    {name: "gdType", mapping: "gdType", type: "int"},
	    {name: "prjType", mapping: "prjType", type: "int"}
    ]
});


var GDDS = Ext.create("Ext.data.Store", {
	model: GDModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/myTasks/gd/getGDList.json",
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

dictDSConfig.proxy.extraParams = {"dictId": 13};
var dictDS_13 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 14};
var dictDS_14 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 15};
var dictDS_15 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 16};
var dictDS_16 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 2};
var dictDS_2 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 1};
var dictDS_1 = Ext.create("Ext.data.Store", dictDSConfig);


var getDictDS_20 = function(){
	dictDSConfig.proxy.extraParams = {"dictId": 20};
	return Ext.create("Ext.data.Store", dictDSConfig);
}
	
dictDSConfig.proxy.extraParams = {"dictId": 22};
var dictDS_22 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 23};
var dictDS_23 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 24};
var dictDS_24 = Ext.create("Ext.data.Store", dictDSConfig);


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





var dictDetailMgrDS = null;
Ext.Ajax.request({
    url: "../biz/cads/appSettings/dictMgr/getAllDictDetailList.json",
    async: false,
    success: function(response){
    	dictDetailMgrDS = eval("(" + response.responseText + ")")
    }
});

