//多文件上传组件
Ext.define('Ext.ux.uploadPanel.UploadPanel',{
    extend : 'Ext.grid.Panel',
    alias : 'widget.uploadpanel',
    width : "100%",
    height : "100%",
    columns : [
        {xtype: 'rownumberer'},
        {text: '文件名', flex:8, dataIndex: 'name', renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
       /* {text: '自定义文件名', width: 130,dataIndex: 'fileName',editor: {xtype: 'textfield'}},*/
        {text: '类型', flex:2, dataIndex: 'type'},
        {text: '大小', flex:2,dataIndex: 'size',renderer:function(v){
            return Ext.util.Format.fileSize(v);
        }},
        {text: '进度', flex:4,dataIndex: 'percent',renderer:function(v){         
            var stml =
                '<div>'+
                    '<div style="border:1px solid #008000;height:10px;width:115px;margin:2px 0px 1px 0px;float:left;">'+     
                        '<div style="float:left;background:#FFCC66;width:'+v+'%;height:8px;"><div></div></div>'+
                    '</div>'+
                //'<div style="text-align:center;float:right;width:40px;margin:3px 0px 1px 0px;height:10px;font-size:12px;">{3}%</div>'+           
            '</div>';
            return stml;
        }},
        {text: '状态', flex:3,dataIndex: 'status',renderer:function(v){
            var status;
            if(v==-1){
                status = "等待上传";
            }else if(v==-2){
                status =  "上传中...";
            }else if(v==-3){
                status =  "<div style='color:red;'>上传失败</div>";
            }else if(v==-4){
                status =  "上传成功";
            }else if(v==-5){
                status =  "停止上传";
            }      
            return status;
        }},
        {
            xtype:'actioncolumn',
            flex:1,
            align: 'center',
            items: [{
                iconCls: Ext.ux.Icons._delete,
                tooltip: '移除',
                handler: function(grid, rowIndex, colIndex) {
                    var id = grid.store.getAt(rowIndex).get('id');
                    grid.store.remove(grid.store.getAt(rowIndex));
                }
            }]
        }
    ],
   /* plugins: [
        Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit: 1
        })
    ],  */

   store : Ext.create('Ext.data.JsonStore',{
        autoLoad : false,
        fields : ['id','name','type','size','percent','status','fileName']
    }),
    addFileBtnText : 'Add File',
    uploadBtnText : 'Upload',
    removeBtnText : 'Remove All',
    cancelBtnText : 'Cancel',
    debug : false,
    file_size_limit : 100,//MB
    file_types : '*.*',
    file_types_description : 'All Files',
    file_upload_limit : 50,
    file_queue_limit : 0,
    post_params : {},
    upload_url : 'test.do',
    flash_url : "../scripts/extjs/ux/swfupload/swfupload.swf",
    flash9_url : "../scripts/extjs/ux/swfupload/swfupload_fp9.swf",
    initComponent : function(){  
        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'top',
            items: [
                {
                    xtype:'button',
                    itemId: 'addFileBtn',
                    iconCls : Ext.ux.Icons.fugue_plus_circle,
                    id : '_btn_for_swf_',
                    text : this.addFileBtnText
                },{ xtype: 'tbseparator' },{
                    xtype : 'button',
                    itemId : 'uploadBtn',
                    iconCls : Ext.ux.Icons.fugue_drive_upload,
                    text : this.uploadBtnText,
                    scope : this,
                    handler : this.onUpload
                },{ xtype: 'tbseparator' },{
                    xtype : 'button',
                    itemId : 'removeBtn',
                    iconCls : Ext.ux.Icons.fugue_bin_minus,
                    text : this.removeBtnText,
                    scope : this,
                    handler : this._onRemove
                },{ xtype: 'tbseparator' },{
                    xtype : 'button',
                    itemId : 'cancelBtn',
                    iconCls : Ext.ux.Icons.fugue_cross_circle,
                    disabled : true,
                    text : this.cancelBtnText,
                    scope : this,
                    handler : this.onCancelUpload
                }
            ]
        }];
         
        this.callParent();
        this.down('button[itemId=addFileBtn]').on({        
            afterrender : function(btn){
                var config = this.getSWFConfig(btn);       
                this.swfupload = new SWFUpload(config);
                Ext.get(this.swfupload.movieName).setStyle({
                    position : 'absolute',
                    top : 0,
                    left : -2
                });
            },
            scope : this,
            buffer:300
        });
        
    },
    getSWFConfig : function(btn){
        var me = this;
        var placeHolderId = Ext.id();
        var em = btn.getEl().child('em');
        if(em==null){
            em = Ext.get(btn.getId()+'-btnWrap');
        }      
        em.setStyle({
            position : 'relative',
            display : 'block'
        });
        em.createChild({
            tag : 'div',
            id : placeHolderId
        });
        return {
            debug: me.debug,
            flash_url : me.flash_url,
            flash9_url : me.flash9_url,
            upload_url: me.upload_url,
            post_params: me.post_params||{savePath:'upload\\'},
            file_size_limit : (me.file_size_limit*1024),
            file_types : me.file_types,
            file_types_description : me.file_types_description,
            file_upload_limit : me.file_upload_limit,
            file_queue_limit : me.file_queue_limit,
            button_width: em.getWidth(),
            button_height: em.getHeight(),
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,
            button_placeholder_id: placeHolderId,
            custom_settings : {
                scope_handler : me
            },
            swfupload_preload_handler : me.swfupload_preload_handler,
            file_queue_error_handler : me.file_queue_error_handler,
            swfupload_load_failed_handler : me.swfupload_load_failed_handler,
            upload_start_handler : me.upload_start_handler,
            upload_progress_handler : me.upload_progress_handler,
            upload_error_handler : me.upload_error_handler,
            upload_success_handler : me.upload_success_handler,
            upload_complete_handler : me.upload_complete_handler,
            file_queued_handler : me.file_queued_handler,
            //add by caobin
            file_dialog_start_handler : me.file_dialog_start_handler,
            //add by caobin
            file_dialog_complete_handler : me.file_dialog_complete_handler
        };
    },
    
    //add by caobin - add mask
    file_dialog_start_handler : function(){
    	this.customSettings.scope_handler.getEl().mask("正在加载文件，请稍候...");
    },
    
    //add by caobin - remove mask
    file_dialog_complete_handler : function(){
    	this.customSettings.scope_handler.getEl().unmask();
    },
    
    swfupload_preload_handler : function(){
        if (!this.support.loading) {
            Ext.Msg.show({
                title : '提示',
                msg : '浏览器Flash Player版本太低,不能使用该上传功能！',
                width : 250,
                icon : Ext.Msg.ERROR,
                buttons :Ext.Msg.OK
            });
            return false;
        }
    },
    file_queue_error_handler : function(file, errorCode, message){
        switch(errorCode){
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED : msg('上传文件列表数量超限');
            break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT : msg('文件大小限制: '+ this.settings.file_size_limit/1024 +'MB');
            break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE : msg('该文件大小为0字节');
            break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE : msg('该文件类型不允许上传');
            break;
        }
        function msg(info){
            Ext.Msg.show({
                title : '提示',
                msg : info,
                width : 250,
                icon : Ext.Msg.WARNING,
                buttons :Ext.Msg.OK
            });
        }
    },
    swfupload_load_failed_handler : function(){
        Ext.Msg.show({
            title : '提示',
            msg : 'SWFUpload加载失败！',
            width : 180,
            icon : Ext.Msg.ERROR,
            buttons :Ext.Msg.OK
        });
    },
    upload_start_handler : function(file){
        var me = this.settings.custom_settings.scope_handler;
        me.down('#cancelBtn').setDisabled(false);  
        var rec = me.store.getById(file.id);
        this.setFilePostName(encodeURIComponent(rec.get('fileName')));
    },
    upload_progress_handler : function(file, bytesLoaded, bytesTotal){
        var me = this.settings.custom_settings.scope_handler;      
        var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
        percent = percent == 100? 99 : percent;
        var rec = me.store.getById(file.id);
        rec.set('percent', percent);
        rec.set('status', file.filestatus);
        rec.commit();
    },
    upload_error_handler : function(file, errorCode, message){
        var me = this.settings.custom_settings.scope_handler;      
        var rec = me.store.getById(file.id);
        rec.set('percent', 0);
        rec.set('status', file.filestatus);
        rec.set('errCode', message);
        rec.commit();
    },
    upload_success_handler : function(file, serverData, responseReceived){
        var me = this.settings.custom_settings.scope_handler;      
        var rec = me.store.getById(file.id);
        if(Ext.JSON.decode(serverData).RSP_HEAD.TRAN_SUCCESS){  //modify by caobin - adapting json format        
            rec.set('percent', 100);
            rec.set('status', file.filestatus);        
        }else{
            rec.set('percent', 0);
            rec.set('status', SWFUpload.FILE_STATUS.ERROR);
        }
        rec.commit();
        if (this.getStats().files_queued > 0 && this.uploadStopped == false) {
            this.startUpload();
        }else{
            me.showBtn(me,true);
        }
    },
    upload_complete_handler : function(file){
         
    },
    file_queued_handler : function(file){
        var me = this.settings.custom_settings.scope_handler;
        me.store.add({
            id : file.id,
            name : file.name,
            fileName : file.name,
            size : file.size,
            type : file.type.replace(/^\./, "").toLowerCase(),
            status : file.filestatus,
            percent : 0
        });
        
    },
    onUpload : function(){
        if (this.swfupload&&this.store.getCount()>0) {
            if (this.swfupload.getStats().files_queued > 0) {
                this.showBtn(this,false);
                this.swfupload.uploadStopped = false;      
                this.swfupload.startUpload();
            }
        }
    },
    showBtn : function(me,bl){
        me.down('#addFileBtn').setDisabled(!bl);
        me.down('#uploadBtn').setDisabled(!bl);
        me.down('#removeBtn').setDisabled(!bl);
        me.down('#cancelBtn').setDisabled(bl);
        if(bl){
            me.down('actioncolumn').show();
        }else{
            me.down('actioncolumn').hide();
        }      
    },

    
    onRemove : function(){
        var ds = this.store;
        for(var i=0;i<ds.getCount();i++){
            var record =ds.getAt(i);
            var file_id = record.get('id');
            this.swfupload.cancelUpload(file_id,false);        
        }
        ds.removeAll(true);//modify by caobin-Pass true to prevent the record bulkremove and clear events from being fired.
        this.swfupload.uploadStopped = false;
    },
    
    _onRemove : function(){
        var ds = this.store;
        for(var i=0;i<ds.getCount();i++){
            var record =ds.getAt(i);
            var file_id = record.get('id');
            this.swfupload.cancelUpload(file_id,false);        
        }
        ds.removeAll();
        this.swfupload.uploadStopped = false;
    },
    
    onCancelUpload : function(){
        if (this.swfupload) {
            this.swfupload.uploadStopped = true;
            this.swfupload.stopUpload();
            this.showBtn(this,true);
        }
    }
});