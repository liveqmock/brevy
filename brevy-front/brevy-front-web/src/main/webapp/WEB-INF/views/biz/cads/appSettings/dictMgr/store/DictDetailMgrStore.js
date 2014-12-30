//dictMgr model
Ext.define("dictDetailMgrModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
    	{name: "dictId", mapping: "dictId"},
	    {name: "name", mapping: "name"},  
	    {name: "code", mapping: "code"},
	    {name: "desc", mapping: "desc"}
    ]
});


var dictDetailMgrDS = Ext.create("Ext.data.Store", {
	model: dictDetailMgrModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/appSettings/dictDetailMgr/getDictDetailList.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});
