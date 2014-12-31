/**
 * @module 技术中心工单
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.GDUI", {
	extend : "App.Module",
	
	gridID : "GDReadMainGridID",
	init : function(){
		return Ext.create('Ext.ux.uploadPanel.UploadPanel',{
		   title : 'UploadPanel for extjs 4.0',
		   addFileBtnText : '选择文件...',
		   uploadBtnText : '上传',
		   removeBtnText : '移除所有',
		   cancelBtnText : '取消上传',
		   file_size_limit : 100,//MB
		   upload_url :  this.getRequestRes('/biz/cads/myTasks/gd/fileUpload.json'),
		   post_params : {sid: Pub.httpOnlySession},//防止非IE内核浏览器session丢失,
		   file_types: "*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.zip;*.rar"
		});
		/*return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.biz.cads.myTasks.gd.crud.GDRead", this)
			]
			
		});*/
		
	}
});