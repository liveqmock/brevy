//ArchivedGD model
Ext.define("ArchivedGDModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "name", mapping: "name"},  
	    {name: "recvDate", mapping: "recvDate"},
	    {name: "execType", mapping: "execType"},
	    {name: "type", mapping: "type"},
	    {name: "briefName", mapping: "briefName"},
	    {name: "priority", mapping: "priority"},
	    {name: "requireFinishTime", mapping: "requireFinishTime"},
	    {name: "estimateJob", mapping: "estimateJob"},
	    {name: "preCond", mapping: "preCond"},
	    {name: "implTeam", mapping: "implTeam"},
	    {name: "pmName", mapping: "pmName"},
	    {name: "startDate", mapping: "startDate"},
	    {name: "ini", mapping: "ini"},
	    {name: "rdp", mapping: "rdp"},
	    {name: "ad", mapping: "ad"},
	    {name: "scp", mapping: "scp"},
	    {name: "sit", mapping: "sit"},
	    {name: "uat", mapping: "uat"},
	    {name: "pip", mapping: "pip"},
	    {name: "smp", mapping: "smp"},
	    {name: "progress", mapping: "progress"},
	    {name: "finishDate", mapping: "finishDate"},
	    {name: "usingResource", mapping: "usingResource"},
	    {name: "usingTime", mapping: "usingTime"},
	    {name: "attachType", mapping: "attachType"}
    ]
});


var ArchivedGDDS = Ext.create("Ext.data.Store", {
	model: ArchivedGDModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/myTasks/archive/getGDHisList.json",
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

dictDSConfig.proxy.extraParams = {"dictId": 20};
var dictDS_20 = Ext.create("Ext.data.Store", dictDSConfig);


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

