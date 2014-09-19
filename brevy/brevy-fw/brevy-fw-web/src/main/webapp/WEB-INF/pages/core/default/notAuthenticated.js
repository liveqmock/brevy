Ext.fly("loading").fadeOut({
	opacity : 0,
	duration : 600,
	remove : false,
	scope : this,
	callback : function() {
		Ext.getBody().applyStyles({
			background: "url(resources/extjs/images/background/desktop.jpg)"
		});
		//初始化表单元素验证提示信息位置
		Pub.utils.EffectUtils.fieldTips("side");
		//加载登录模块
		Pub.resource.Loader.loadModule("/WEB-INF/pages/core/login/module.xml");
	}
});