var permittedTablesDS = Ext.create('Ext.data.Store', {
    fields:['name', 'code'],
    data:{'items':[
        {name: "技术中心工单表",  code:"CAD_GD"},
        {name: "技术中心工单附件表",  code:"CAD_GD_ATTACH"},
        {name: "技术中心工单部门关联表",  code:"CAD_REF_DEPT_GD"},
        {name: "技术中心工单归档表",  code:"CAD_GD_HIS"},
        {name: "技术中心需求评估单表",  code:"CAD_DEMAND"},
        {name: "技术中心需求评估单附件表",  code:"CAD_DEMAND_ATTACH"},
        {name: "技术中心需求评估单部门关联表",  code:"CAD_REF_DEPT_DEMAND"},
        {name: "技术中心需求评估单归档表",  code:"CAD_DEMAND_HIS"},
        {name: "技术中心综合管理任务表",  code:"CAD_CATASK"},
        {name: "技术中心综合管理任务附件表",  code:"CAD_CATASK_ATTACH"},
        {name: "技术中心综合管理任务归档表",  code:"CAD_CATASK_HIS"}
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});