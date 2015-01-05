Ext.define("DemandAttachmentModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "demandId", mapping: "demandId"},  
	    {name: "path", mapping: "path"}
    ]
});


var DemandAttachmentDS = Ext.create("Ext.data.Store", {
	model: DemandAttachmentModel,
	autoLoad: false,	
	proxy: {
        type: "jsonajax",
        url: "../biz/cads/myTasks/demand/getDemandAttachmentList.json",
        reader: {
            type: "json"
        }
    }
});

//Demand model
Ext.define("DemandModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "prjName", mapping: "prjName"},  
	    {name: "recvDate", mapping: "recvDate"},
	    {name: "priority", mapping: "priority"},
	    {name: "requireFinishTime", mapping: "requireFinishTime"},
	    {name: "estimateDev", mapping: "estimateDev"},
	    {name: "estimateTest", mapping: "estimateTest"},
	    {name: "preCond", mapping: "preCond"},
	    {name: "implTeam", mapping: "implTeam"},
	    {name: "startDate", mapping: "startDate"},
	    {name: "attachType", mapping: "attachType"},
	    {name: "status", mapping: "status"},
	    {name: "remark", mapping: "remark"}
    ]
});


var DemandDS = Ext.create("Ext.data.Store", {
	model: DemandModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/myTasks/demand/getDemandList.json",
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

dictDSConfig.proxy.extraParams = {"dictId": 21};
var dictDS_21 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 15};
var dictDS_15 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 16};
var dictDS_16 = Ext.create("Ext.data.Store", dictDSConfig);

dictDSConfig.proxy.extraParams = {"dictId": 2};
var dictDS_2 = Ext.create("Ext.data.Store", dictDSConfig);


var dictDetailMgrDS = null;
Ext.Ajax.request({
    url: "../biz/cads/appSettings/dictMgr/getAllDictDetailList.json",
    async: false,
    success: function(response){
    	dictDetailMgrDS = eval("(" + response.responseText + ")")
    }
});

