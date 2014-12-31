//GD model
Ext.define("GDModel", {
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


var GDDS = Ext.create("Ext.data.Store", {
	model: GDModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/myTasks/gc/getGDList.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});
