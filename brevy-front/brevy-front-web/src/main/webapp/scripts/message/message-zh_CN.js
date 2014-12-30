/**
 * @description 消息定义
 * @author caobin
 */
Ext.define("Msg", {
	
	statics : {
		HttpCode : {
			//自定义session超时响应码 
			SC_SESSION_TIMEOUT : 505,
			//拒绝访问
			SC_FORBIDDEN : 403
		},
		
		/** 常规消息定义 **/
		
		//交互信息
		Prompt : {
			infoTitle : "信息",
			saveSuccess: "保存成功",
			updateSuccess: "更新成功",
			delSuccess: "删除成功",
			warnTitle : "警告",
			warnCheck: "请选择一条记录",
			warnChecks: "请选择记录",
			errorTitle : "错误",		
			saveFailed: "保存失败",
			updateFailed: "更新失败",
			delFailed: "删除失败",
			invalidMsgCode: "无效的消息代码-{0}",
			requestTimeout: "请求超时",
			incorrectUsernameAndPassword: "用户名密码错误",
			requestTimeout: "请求超时",
			sessionTimeout: "会话超时，请重新登录",
			loginFailed: "登录失败-{0}",
			requestFailed: "请求失败<p>-&nbsp;HTTP响应状态: {0}</p><p>-&nbsp;错误信息: {1}</p>",
			moduleLoadFailed: "模块加载失败<p>-&nbsp;错误信息: {0}</p>",
			confirmTitle : "确认",
			confirmDelRec: "您确认删除当前选中记录吗？",
			confirmDelNode: "您确认删除当前选中节点{0}吗？",
			confirmDelNodeCascade: "您确认删除当前选中节点{0}及其所有子节点吗？",
			blankText : "{0}不能为空",
			requiredText : "{0}必填",
			SC_403 : "403 - 拒绝访问"
		},
		

		/** 模块信息定义 **/
		//应用常规
		App : {
			/** HEADER **/
			header : "<div class=default-title-pos><span class=default-title-font>BREVY FRONT FRAMEWORK</span><span class=default-subtitle>version:1.0.0</span></div>",
		
			/** FOOTER **/
			footer : "<div class=default-footer><span>Copyright 2013 - 201X G.Z. All Rights Reserved</span></div>",
		
			/** LOADING MESSAGE **/
			menuLoading : "正在加载菜单项...",
			
			pageLoading : "正在加载页面...",
			
			moduleLoading : "正在加载模块...",
			
			exitMsgLoading : "正在退出系统...",
			
			userLoading : "正在加载用户...",
			
			saving : "正在保存，请稍候...",
			
			updating : "正在更新，请稍候...",
			
			deleting : "正在删除，请稍候...",
			
			home : "首页",
			
			exit : "登出",
			
			save : "保存",
			
			update : "更新",
			
			reset : "重置",
			
			add : "添加",
			
			edit : "编辑",
			
			del: "删除"
		},
		
		Constants : {
			yes : "是",
			no : "否",
			enable : "启用",
			disable : "禁用",
			valid : "有效",
			invalid : "无效",
			rootMenu : "顶级菜单",
			relMenu : "已关联菜单列表",
			anonFilter : "匿名过滤器",
			authenFilter : "身份认证过滤器",
			authorFilter : "角色鉴权过滤器"
			
		},


		
		//Login模块
		"App.login.LoginUI" : {
			loginTitle : "用户登录",
			appname : "应用系统",
			emptyAppname : "请选择一个应用系统",
			blankAppname : "应用系统必选",
			username : "用户名",	
			emptyUsername : "请输入用户名",
			blankUsername : "用户名必填",
			password : "密码",
			emptyPassword : "请输入密码",
			blankPassword : "密码必填",
			captcha : "验证码",
			emptyCaptcha : "请输入验证码",
			blankCaptcha : "验证码必填",
			rememberMe : "记住我",
			forgetPassword : "忘记密码",
			login : "登&nbsp;&nbsp;录",
			reset : "重&nbsp;&nbsp;置",
			forgetPassword : "忘记密码？",
			loginWaitMsg : "正在登录，请稍候..."
		},
		
		//功能菜单维护模块
		"App.systemMGR.authCFG.funcMenu.FuncMenuUI" : {
			currentAppname : "当前应用系统",
			collapseMenuitems : "收起菜单项",
			expandMenuitems : "展开菜单项",
			name : "名称",
			icon : "图标",
			parentId : "父菜单项",
			moduleLocation : "加载模块位置",
			leaf : "叶子菜单",
			status : "状态",
			sort : "排序号",
			fieldsetTitle : "菜单基本信息",
			addParentMenuitem: "创建父菜单项",
			addMenuitem: "创建叶子菜单项",
			delMenuitem: "删除菜单项",
			createNodeWaitMsg: "正在创建菜单项",
			deleteNodeWaitMsg: "正在删除菜单项",
			updateAndRearrangeNodesWaitMsg: "正在更新和重排节点，请稍候...",
			cannotMoveItemUsingSkipLv: "不能跨级移动菜单项",
			newNode: "新建菜单项"
		},
		
		
		"App.systemMGR.authCFG.accessAuth.crud.AccessAuthRead" : {
			currentAppname: "当前应用系统",
			name: "访问权限名称",
			code: "访问权限代码",
			urlPattern: "URL匹配模式",
			authenFilter: "身份验证过滤器",
			authorFilter: "鉴权过滤器",
			status: "状态",
			sort: "排序编号",
			valid: "有效",
			invalid: "无效",
			keywordSearch: "关键字查询"
		},
		
		"App.systemMGR.authCFG.accessAuth.crud.AccessAuthCreate" : {
			name: "访问权限名称",
			code: "访问权限代码",
			urlPattern: "URL匹配模式",
			authenFilter: "身份验证过滤器",
			authorFilter: "鉴权过滤器",
			status: "状态",
			sort: "排序编号",
			accessPermBasicInfo: "访问权限基础信息",
			createAccessPermTitle: "添加访问权限"	
		},
		
		
		"App.systemMGR.authCFG.accessAuth.crud.AccessAuthUpdate" : {
			name: "访问权限名称",
			code: "访问权限代码",
			urlPattern: "URL匹配模式",
			authenFilter: "身份验证过滤器",
			authorFilter: "鉴权过滤器",
			status: "状态",
			sort: "排序编号",
			accessPermBasicInfo: "访问权限基础信息",
			editAccessPermTitle: "编辑访问权限"	
		},
		
		"App.systemMGR.authCFG.operAuth.crud.OperAuthRead" : {
			name: "操作权限名称",
			code: "操作权限代码",
			authorizedOper: "授权操作",
			status: "状态",
			sort: "排序编号",
			valid: "有效",
			invalid: "无效",
			keywordSearch: "关键字查询"
		},
		
		"App.systemMGR.authCFG.operAuth.crud.OperAuthCreate" : {
			name: "操作权限名称",
			code: "操作权限代码",
			authorizedOper: "授权操作",
			status: "状态",
			sort: "排序编号",
			operPermBasicInfo: "操作权限基础信息",
			createOperPermTitle: "添加操作权限"	
		},
		
		"App.systemMGR.authCFG.operAuth.crud.OperAuthUpdate" : {
			name: "操作权限名称",
			code: "操作权限代码",
			authorizedOper: "授权操作",
			status: "状态",
			sort: "排序编号",
			operPermBasicInfo: "操作权限基础信息",
			editOperPermTitle: "编辑操作权限"	
		},
		
		
		"App.systemMGR.authCFG.role.RoleUI" : {
			currentRole: "当前角色：{0}-[{1}]"
		},
		
		"App.systemMGR.authCFG.role.crud.RoleRead" : {
			name: "角色名称",
			code: "角色代码",
			status: "状态",
			valid: "有效",
			invalid: "无效",
			keywordSearch: "关键字查询"
		},
		
		
		"App.systemMGR.authCFG.role.crud.RoleCreate" : {
			name: "角色名称",
			code: "角色代码",
			status: "状态",
			roleBasicInfo: "角色基础信息",
			createRoleTitle: "添加角色"
		},
		
		"App.systemMGR.authCFG.role.crud.RoleUpdate" : {
			name: "角色名称",
			code: "角色代码",
			status: "状态",
			roleBasicInfo: "角色基础信息",
			editRoleTitle: "编辑角色"	
		},
		
		"App.systemMGR.authCFG.role.refMenu.RefMenu" : {
			refMenuTitle: "角色关联菜单配置",
			refMenuWestName: "角色已关联菜单",
			refMenuWestText: "操作",
			refMenuWestTooltip: "撤销菜单关联",
			refMenuEastIconText: "图标",
			refMenuEastIconTooltip: "添加菜单关联",
			refMenuEastNameText: "可选菜单名称",
			refMenuEastLeafText: "菜单类型",
			refMenuEastLeafTextDir: "目录",
			refMenuEastLeafTextLeaf: "叶子菜单",
			refMenuEastActionText: "操作",
			refMenuEastActionTooltip: "添加菜单关联"
		},
		
		"App.systemMGR.authCFG.role.refAccessAuth.RefAccessAuth" : {
			refAccessAuthTitle: "角色关联访问权限配置",
			refAccessAuthWestName: "角色已关联访问权限",
			refAccessAuthWestText: "操作",
			refAccessAuthWestTooltip: "撤销访问权限关联",
			refAccessAuthEastIconTooltip: "添加访问权限关联",
			refAccessAuthEastNameText: "可选访问权限名称",
			refAccessAuthEastActionText: "操作",
			refAccessAuthEastActionTooltip: "添加访问权限关联",
			keywordSearch: "关键字查询",
			accessAuthName: "访问权限名称",
			accessAuthCode: "访问权限代码",
			accessAuthUrlPattern: "URL匹配模式",
			cancelRefs: "撤销当页所有访问权限关联",
			addRefs: "添加当页所有访问权限关联",
			confirmCancelAllRefs: "确认撤销当页所有访问权限关联？",
			confirmAddAllRefs: "确认添加当页所有访问权限关联？"
			
		},
		
		"App.systemMGR.authCFG.role.refOperAuth.RefOperAuth" : {
			refOperAuthTitle: "角色关联操作权限配置",
			refOperAuthWestName: "角色已关联操作权限",
			refOperAuthWestText: "操作",
			refOperAuthWestTooltip: "撤销操作权限关联",
			refOperAuthEastIconTooltip: "添加操作权限关联",
			refOperAuthEastNameText: "可选操作权限名称",
			refOperAuthEastActionText: "操作",
			refOperAuthEastActionTooltip: "添加操作权限关联",
			keywordSearch: "关键字查询",
			operAuthName: "操作权限名称",
			operAuthCode: "操作权限代码",
			operAuthOper: "授权操作",
			cancelRefs: "撤销当页所有操作权限关联",
			addRefs: "添加当页所有操作权限关联",
			confirmCancelAllRefs: "确认撤销当页所有操作权限关联？",
			confirmAddAllRefs: "确认添加当页所有操作权限关联？"
			
		},
		
		"App.systemMGR.authCFG.userGroup.UserGroupUI" : {
			currentUserGroup: "当前用户组：{0}-[{1}]"
		},
		
		"App.systemMGR.authCFG.userGroup.crud.UserGroupRead" : {
			name: "用户组名称",
			code: "用户组代码",
			status: "状态",
			desc: "描述",
			valid: "有效",
			invalid: "无效",
			keywordSearch: "关键字查询"
		},
		
		"App.systemMGR.authCFG.userGroup.crud.UserGroupCreate" : {
			name: "用户组名称",
			code: "用户组代码",
			status: "状态",
			desc: "描述",
			userGroupBasicInfo: "用户组基础信息",
			createUserGroupTitle: "添加用户组"
		},
		
		"App.systemMGR.authCFG.userGroup.crud.UserGroupUpdate" : {
			name: "用户组名称",
			code: "用户组代码",
			status: "状态",
			desc: "描述",
			userGroupBasicInfo: "用户组基础信息",
			editUserGroupTitle: "编辑用户组"	
		},
		
		"App.systemMGR.authCFG.userGroup.refRole.RefRole" : {
			refRoleTitle: "用户组关联角色配置",
			refRoleWestName: "用户组已关联角色",
			refRoleWestText: "操作",
			refRoleWestTooltip: "撤销角色关联",
			refRoleEastIconText: "图标",
			refRoleEastIconTooltip: "添加角色关联",
			refRoleEastNameText: "可选角色名称",
			refRoleEastLeafText: "角色类型",
			refRoleEastActionText: "操作",
			refRoleEastActionTooltip: "添加角色关联",
			cancelRefs: "撤销当页所有角色关联",
			addRefs: "添加当页所有角色关联",
			keywordSearch: "关键字查询",
			confirmCancelAllRefs: "确认撤销当页所有角色关联？",
			confirmAddAllRefs: "确认添加当页所有角色关联？",
			userGroupName: "用户组名称",
			userGroupCode: "用户组代码"
		},
		
		"App.systemMGR.authCFG.app.crud.AppRead" : {
			name: "应用系统名称",
			code: "应用系统代码",
			status: "状态",
			desc: "描述",
			valid: "有效",
			invalid: "无效",
			keywordSearch: "关键字查询"
		},
		
		"App.systemMGR.authCFG.app.crud.AppCreate" : {
			name: "应用系统名称",
			code: "应用系统代码",
			status: "状态",
			desc: "描述",
			appBasicInfo: "应用系统基础信息",
			createAppTitle: "添加应用系统"	
		},
		
		
		"App.systemMGR.authCFG.app.crud.AppUpdate" : {
			name: "应用系统名称",
			code: "应用系统代码",
			status: "状态",
			desc: "描述",
			appBasicInfo: "应用系统基础信息",
			editAppTitle: "编辑应用系统"	
		},
		
		"App.systemMGR.authCFG.user.UserUI" : {
			currentUser: "当前用户：{0}-[{1}]"
		},
		
		"App.systemMGR.authCFG.user.crud.UserRead" : {
			username: "用户名",
			chName: "姓名",
			status: "状态",
			position: "职位",
			dept: "部门",
			valid: "有效",
			invalid: "无效",
			keywordSearch: "关键字查询"
		},
		
		"App.systemMGR.authCFG.user.refApp.RefApp" : {
			refAppTitle: "用户关联应用系统配置",
			refAppWestName: "用户已关联应用系统",
			refAppWestText: "操作",
			refAppWestTooltip: "撤销应用系统关联",
			refAppEastIconTooltip: "添加应用系统关联",
			refAppEastNameText: "可选应用系统名称",
			refAppEastActionText: "操作",
			refAppEastActionTooltip: "添加应用系统关联",
			keywordSearch: "关键字查询",
			appName: "应用系统名称",
			appCode: "应用系统代码",
			cancelRefs: "撤销当页所有应用系统关联",
			addRefs: "添加当页所有应用系统关联",
			confirmCancelAllRefs: "确认撤销当页所有应用系统关联？",
			confirmAddAllRefs: "确认添加当页所有应用系统关联？"
			
		},
		
		"App.systemMGR.authCFG.user.refGroup.RefGroup" : {
			refGroupTitle: "用户关联用户组配置",
			refGroupWestName: "用户已关联用户组",
			refGroupWestText: "操作",
			refGroupWestTooltip: "撤销用户组关联",
			refGroupEastIconTooltip: "添加用户组关联",
			refGroupEastNameText: "可选用户组名称",
			refGroupEastActionText: "操作",
			refGroupEastActionTooltip: "添加用户组关联",
			keywordSearch: "关键字查询",
			groupName: "用户组名称",
			groupCode: "用户组代码",
			cancelRefs: "撤销当页所有用户组关联",
			addRefs: "添加当页所有用户组关联",
			confirmCancelAllRefs: "确认撤销当页所有用户组关联？",
			confirmAddAllRefs: "确认添加当页所有用户组关联？"
			
		},
		
		"App.systemMGR.authCFG.user.refRole.RefRole" : {
			refRoleTitle: "用户关联角色配置",
			refRoleWestName: "用户已关联角色",
			refRoleWestText: "操作",
			refRoleWestTooltip: "撤销角色关联",
			refRoleEastIconTooltip: "添加角色关联",
			refRoleEastNameText: "可选角色名称",
			refRoleEastActionText: "操作",
			refRoleEastActionTooltip: "添加角色关联",
			keywordSearch: "关键字查询",
			roleName: "角色名称",
			roleCode: "角色代码",
			cancelRefs: "撤销当页所有角色关联",
			addRefs: "添加当页所有角色关联",
			confirmCancelAllRefs: "确认撤销当页所有角色关联？",
			confirmAddAllRefs: "确认添加当页所有角色关联？"
			
		},
		
		"App.systemMGR.authCFG.user.crud.UserCreate" : {
			chName: "姓名",
			username: "用户名",
			password: "密码",
			confirmPassword: "确认密码",
			status: "状态",
			position: "职位",
			dept: "部门",
			userBasicInfo: "用户基础信息",
			createUserTitle: "添加用户",
			emptyPositionName : "请选择一个职位",
			emptyDeptName : "请选择一个部门"
		},
		
		"App.systemMGR.authCFG.user.crud.UserUpdate" : {
			chName: "姓名",
			username: "用户名",
			password: "密码",
			confirmPassword: "确认密码",
			status: "状态",
			position: "职位",
			dept: "部门",
			userBasicInfo: "用户基础信息",
			createUserTitle: "添加用户",
			emptyPositionName : "请选择一个职位",
			emptyDeptName : "请选择一个部门"
		},
		
		"App.biz.cads.mySettings.resetPwd.ResetPasswordUI" : {
			oldPassword: "输入旧密码",
			newPassword: "输入新密码",
			confirmNewPassword: "再输入一次新密码",
			resetPasswordBasicInfo: "密码重置",
			resetPasswordTitle: "密码重置"
		},
		
		"App.biz.cads.appSettings.dictMgr.crud.DictMgrRead" : {
			name: "字典类型名称", 
			code: "字典类型代码",
			desc: "字典类型描述",
			showDetail: "字典明细"
		},
		
		"App.biz.cads.appSettings.dictMgr.crud.DictMgrCreate" : {
			name: "字典类型名称", 
			code: "字典类型代码",
			desc: "字典类型描述",
			createDictMgrTitle: "添加字典类型"
		},
		
		"App.biz.cads.appSettings.dictMgr.crud.DictMgrUpdate" : {
			name: "字典类型名称", 
			code: "字典类型代码",
			desc: "字典类型描述",
			editDictMgrTitle: "更新字典类型"
		}
		
	}
});