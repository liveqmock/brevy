//ArchivedDemand model
Ext.define("ArchivedDemandModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "prjName", mapping: "prjName"},  
	    {name: "recvDate", mapping: "recvDate"},
	    {name: "priority", mapping: "priority", type: "int"},
	    {name: "requireFinishTime", mapping: "requireFinishTime", convert: Pub.Support.dateConvert},
	    {name: "estimateDev", mapping: "estimateDev"},
	    {name: "estimateTest", mapping: "estimateTest"},
	    {name: "preCond", mapping: "preCond"},
	    {name: "implTeam", mapping: "implTeam"},
	    {name: "startDate", mapping: "startDate", convert: Pub.Support.dateConvert},
	    {name: "attachType", mapping: "attachType"},
	    {name: "status", mapping: "status", type: "int"},
	    {name: "devFinishDate", mapping: "devFinishDate", convert: Pub.Support.dateConvert},
	    {name: "sitWorkload", mapping: "sitWorkload"},
	    {name: "sitFinishDate", mapping: "sitFinishDate", convert: Pub.Support.dateConvert},
	    {name: "remark", mapping: "remark"}
    ]
});


var ArchivedDemandDS = Ext.create("Ext.data.Store", {
	model: ArchivedDemandModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/myTasks/archive/getDemandHisList.json",
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

