<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ include file="/common/taglibs.jsp"%> 
<script>
	var _ctxPath = "<%=getServletContext().getContextPath()%>";
</script>

<pack:style src="/resources/extjs/ext-theme-gray/ext-theme-gray-all.css" />

<!-- fixed position of menuitem for left blank -->
<style>
	.x-menu-item-indent {
        margin-left: 0px;
    }
</style>

<!-- if path of resources is different, please create a new pack:style tag -->
<pack:style>
	<src>/resources/extjs/ux/ext-icons.css</src>	
	<src>/resources/extjs/ux/ext-ux-all.css</src>
</pack:style>

<pack:script>
	<!-- extJs core -->
	<src>/scripts/extjs/ext-all.js</src>
	
	<!-- locale message -->
	<src>/scripts/message/message-zh_CN.js</src>
	<!-- extJs Pub -->
	<src>/scripts/extjs/public-all-min.js</src>
	<!-- extJs ux -->
	<src>/scripts/extjs/ux/IFrame.js</src> 
	<src>/scripts/extjs/ux/Icons.js</src>
	<src>/scripts/extjs/ux/BizIcons.js</src>
	<src>/scripts/extjs/ux/PasswordField.js</src>
	<src>/scripts/extjs/ux/PasswordMeter.js</src>
	<src>/scripts/extjs/ux/CaptchaField.js</src>
	<src>/scripts/extjs/ux/StatusBar.js</src>	
	<src>/scripts/extjs/ux/StaticMenuBar.js</src>
	<src>/scripts/extjs/ux/VirtualKeyboard.js</src>
	<src>/scripts/extjs/ux/IconCombo.js</src>
	<src>/scripts/extjs/ux/TreeCombo.js</src>
	<src>/scripts/extjs/ux/GridCombo.js</src>
	<src>/scripts/extjs/ux/XFormPanel.js</src>
	<src>/scripts/extjs/ux/ClearButton.js</src>
	<src>/scripts/extjs/ux/Notification.js</src>
	<src>/scripts/extjs/ux/TreeViewDragDrop.js</src>
	<src>/scripts/extjs/ux/PagingToolbarResizer.js</src>
	<src>/scripts/extjs/ux/SearchField.js</src>
	<src>/scripts/extjs/ux/CustomVTypes.js</src>
	<!-- custom part -->	
	<src>/scripts/extjs/base/module.js</src>
	
	<!-- extJs locale -->
	<src>/scripts/extjs/locale/ext-lang-zh_CN.js</src>
</pack:script>

<script>
	Ext.QuickTips.init(); 
	Pub.Utils.fieldTips("side");
</script>