var ExtRES="/WEB-INF/views/";Ext.define("Pub.Style",{statics:{grayBody:"background-color:#f1f1f1;",fieldIcon:function(b,c,a){a=(a||20)+"px";return c=="left"?{background:"url(".concat(b).concat(") no-repeat ").concat(c),paddingLeft:a}:c=="right"?{background:"url(".concat(b).concat(") no-repeat ").concat(c),paddingRight:a}:null}}});Ext.define("Pub.Data",{statics:{menuIcons:function(){var a=[];Ext.Object.each(Ext.ux.BizIcons,function(c,b,d){if(Ext.String.startsWith(b,"icon-")){a.push([b,c])}});return a}}});Ext.define("Pub.Utils",{statics:{getMessage:function(b){var a;if(arguments.length>1&&Array.isArray(arguments[1])){a=arguments[1]}else{a=Ext.Array.toArray(arguments,1)}return b.replace(/\{(\d+)\}/g,function(c,d){return a[d]})},fastUUID:function(a){var b=Ext.data.IdGenerator.get("uuid").generate();return a?b:b.replace(/\-/g,"")},focus:function(b,a){b.getFields().getAt(a).focus()},fieldTips:function(a){Ext.form.Field.prototype.msgTarget=a},quickTips:function(a){a=a||5000;Ext.tip.QuickTipManager.init();Ext.apply(Ext.tip.QuickTipManager.getQuickTip(),{dismissDelay:a})},cellTips:function(a,b){b.attr+='ext:qtip="'+a+'"';return a}}});Ext.define("Pub.Notification",{statics:{INFO:"info",ERROR:"error",showNotification:function(c,b,e){var d,a;switch(c){case this.INFO:d=Msg.Prompt.infoTitle;a="ux-notification-icon-information";break;case this.ERROR:d=Msg.Prompt.errorTitle;a="ux-notification-icon-error";break;default:throw new Error(Ext.String.format("Pub.Notification.showNotification - invalid type : {0}",c))}Ext.create("widget.uxNotification",{title:d,position:e,paddingX:10,cls:"ux-notification-light",iconCls:a,minWidth:220,html:b,autoCloseDelay:3500,slideBackDuration:500,slideInAnimation:"bounceOut",slideBackAnimation:"easeIn"}).show()}}});Ext.define("Pub.MsgBox",{statics:{INFO:"info",WARN:"warn",ERROR:"error",CONFIRM:"confirm",PROMPT:"prompt",showMsgBox:function(c,i,d,g,h){var b=Pub.Utils.getMessage(i,Array.isArray(d)?d:[d]);switch(c){case this.INFO:var f=Msg.Prompt.infoTitle;var e=Ext.Msg.INFO;var a=Ext.Msg.OK;break;case this.WARN:var f=Msg.Prompt.warnTitle;var e=Ext.Msg.WARNING;var a=Ext.Msg.OK;break;case this.ERROR:var f=Msg.Prompt.errorTitle;var e=Ext.Msg.ERROR;var a=Ext.Msg.OK;break;case this.CONFIRM:var f=Msg.Prompt.confirmTitle;var e=Ext.Msg.QUESTION;var a=Ext.Msg.YESNO;break;default:throw new Error(Ext.String.format("Pub.MsgBox.showMsgBox - invalid msgType : {0}",c))}Ext.Msg.show({title:f,msg:b,icon:e,buttons:a,fn:g,scope:h})}}});Ext.define("Pub.ResLoader",{statics:{_LoadUrlCfg:{jsLoadUrl:_ctxPath+"/common/loadJs.jsp?js=",cssLoadUrl:_ctxPath+"/common/loadCss.jsp?css=",moduleLoadUrl:_ctxPath+"/common/loadModule.jsp?"},jsPack:function(e,f,d,c){c=c||window;d=d||{};if(typeof e=="string"){this._LoadSingleJs(this._srcTagWrap(e),f,d,c)}else{if(e instanceof Array&&e.length>0){var a="";for(var b in e){a+=this._srcTagWrap(e[b])}this._LoadSingleJs(a,f,d,c)}else{Ext.callback(f,c,d instanceof Array?d:[d])}}},cssPack:function(a,d,c,b){b=b||window;c=c||{};if(typeof a=="string"){this._LoadSingleCss(a,d,c,b)}else{if(a instanceof Array&&a.length>0){this._cssBatchPack({index:0,size:a.length,css:a,callback:d,param:c,scope:b,self:this})}else{Ext.callback(d,b,c instanceof Array?c:[c])}}},resourcePack:function(a,d,e,c,b){this.cssPack(a,function(){this.jsPack(d,e,c,b)},null,this)},loadModule:function(module,callback,params){var moduleLoadUrl=this._LoadUrlCfg.moduleLoadUrl.concat("xml=",module);module=module.replace(/(\w+\.xml)$/,"");Ext.Ajax.request({method:"POST",url:moduleLoadUrl,scope:this,success:function(response){var r;try{r=eval("(".concat(response.responseText,")"))}catch(e){Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.moduleLoadFailed,[response.responseText]);return}var config={};config.moduleId=r.module.moduleId||Pub.Utils.fastUUID(false);config.moduleName=r.module.moduleName;if(!config.moduleName){throw new Error("value of tag[moduleName] is required.")}config.moduleText=r.module.moduleText||"";config.moduleIcon=r.module.moduleIcon||"icon-table";var css=[],js=[];if(r.module.files.css&&r.module.files.css.file){if((r.module.files.css.file) instanceof Array){for(var i=0;i<r.module.files.css.file.length;i++){css.push(module+r.module.files.css.file[i])}config.css=css.join(",")}else{css.push(module+r.module.files.css.file);config.css=css[0]}}if(r.module.files.js&&r.module.files.js.file){if((r.module.files.js.file) instanceof Array){for(var i=0;i<r.module.files.js.file.length;i++){js.push(module+r.module.files.js.file[i])}config.js=js.join(",")}else{js.push(module+r.module.files.js.file);config.js=js[0]}}config.params=params||{};if(callback){this.resourcePack(css,js,callback,config)}else{this.resourcePack(css,js,function(c){var moduleObj=Ext.create(c.moduleName,c);if(moduleObj.beforeInit){moduleObj.beforeInit()}if(moduleObj.init){moduleObj.init()}if(moduleObj.afterInit){moduleObj.afterInit()}},config)}},failure:function(response){Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.requestFailed,[response.status,response.responseText])}})},_cssBatchPack:function(p){with(p){if(index<=size){var originalIdx=index;index+=1;if(index==size){self._LoadSingleCss(css[originalIdx],callback,param,scope)}else{self._LoadSingleCss(css[originalIdx],self._cssBatchPack,p,scope)}}}},_LoadSingleJs:function(js,callback,p,scope){scope=scope||window;var jsURL=this._LoadUrlCfg.jsLoadUrl+js;var f=arguments.callee;if(!("queue" in f)){f.queue={}}var queue=f.queue;if(js in queue){if(callback){Ext.callback(callback,scope,p instanceof Array?p:[p])}return}queue[js]=callback?[callback]:[];Ext.Ajax.request({method:"GET",url:jsURL,success:function(response){var result=eval("("+response.responseText+")");var url=result.js.replace(/.+src=\"(.+\.pack)\".+/ig,"$1");var script=document.createElement("script");script.type="text/javascript";script.charset="UTF-8";script.onload=script.onreadystatechange=function(){if(script.readyState&&script.readyState!="loaded"&&script.readyState!="complete"){return}script.onreadystatechange=script.onload=null;while(queue[js].length){Ext.callback(queue[js].shift(),scope,p instanceof Array?p:[p])}queue[js]=null};script.src=url;document.getElementsByTagName("head")[0].appendChild(script)}})},_LoadSingleCss:function(css,callback,p,scope){scope=scope||window;var cssURL=this._LoadUrlCfg.cssLoadUrl+css;var f=arguments.callee;if(!("queue" in f)){f.queue={}}var queue=f.queue;if(css in queue){if(callback){Ext.callback(callback,scope,p instanceof Array?p:[p])}return}queue[css]=callback?[callback]:[];Ext.Ajax.request({method:"GET",url:cssURL,success:function(response){var result=eval("("+response.responseText+")");var url=result.css.replace(/.+href=\"(.+\.pack)\".+/ig,"$1");var link=document.createElement("link");link.type="text/css";link.rel="stylesheet";link.onload=link.onreadystatechange=function(){if(link.readyState&&link.readyState!="loaded"&&link.readyState!="complete"){return}link.onreadystatechange=link.onload=null;while(queue[css].length){Ext.callback(queue[css].shift(),scope,p instanceof Array?p:[p])}queue[css]=null};link.href=url;document.getElementsByTagName("head")[0].appendChild(link)}})},_srcTagWrap:function(a){return"<src>"+a+"</src>"}}});Ext.define("Pub.Ajax",{statics:{initAllAjaxEvents:function(){this.initBeforerequestEvent();this.initRequestcompleteEvent();this.initRequestexceptionEvent()},initBeforerequestEvent:function(){Ext.Ajax.on("beforerequest",function(e,d,a){if(d.jsonData){var b={REQ_HEAD:{},REQ_BODY:{}};b.REQ_BODY=d.jsonData;d.jsonData=b}})},initRequestcompleteEvent:function(){},initRequestexceptionEvent:function(){var a=this;Ext.Ajax.on("requestexception",function(g,d,e,b){var f=function(){if(Ext.getCmp("_captcha")){Ext.getCmp("_captcha").loadImage()}};if(e.failure){e.failure=function(c,h){Ext.MessageBox.hide(null,function(){if(d.timedout){Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.requestTimeout,null,f)}else{if(d.status==Msg.HttpCode.SC_SESSION_TIMEOUT){a._showSessionTimeoutMsgBox(d)}else{if(d.status==Msg.HttpCode.SC_FORBIDDEN){a._showForbiddenMsgBox(d)}else{Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.requestFailed,[d.status,d.responseText],f)}}}})}}else{if(d.status==Msg.HttpCode.SC_SESSION_TIMEOUT){a._showSessionTimeoutMsgBox(d)}else{if(d.status==Msg.HttpCode.SC_FORBIDDEN){a._showForbiddenMsgBox(d)}else{Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.requestFailed,[d.status,d.responseText],null)}}}},this)},_showSessionTimeoutMsgBox:function(r){Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.sessionTimeout,null,function(){var redirectUrl=eval("("+r.responseText+")").RSP_BODY.loginUrl;window.top.location=_ctxPath+redirectUrl})},_showForbiddenMsgBox:function(r){Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.SC_403,null,function(){var redirectUrl=eval("("+r.responseText+")").RSP_BODY.defaultUrl;location.href=_ctxPath+redirectUrl})},overrideAllResponseHandlers:function(){this.overrideSubmitResponseHandler()},overrideSubmitResponseHandler:function(){Ext.override(Ext.form.action.Submit,{onSuccess:function(c){var d=this.form,a=this.processResponse(c),b=true;if(a.RSP_HEAD&&a!==true&&a.RSP_HEAD.TRAN_SUCCESS=="0"){if(a.RSP_HEAD.ERROR_CODE||a.RSP_HEAD.ERROR_MESSAGE||a.RSP_HEAD.ERROR_FIELDS){if(a.RSP_HEAD.ERROR_CODE=="_SHIRO_AUTHENTICATE_FAILED"){window.location.href=_ctxPath+a.RSP_BODY.loginUrl}else{if(a.RSP_HEAD.ERROR_CODE=="_SHIRO_AUTHORIZED_FAILED"){Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR,Msg.Prompt.SC_403,null,function(){window.top.location=_ctxPath+a.RSP_BODY.defaultUrl})}}d.markInvalid(a.errors=a.RSP_HEAD.ERROR_FIELDS)}this.failureType=Ext.form.action.Action.SERVER_INVALID;b=false}d.afterAction(this,b)}})}}});Ext.Ajax.timeout=3600000;Pub.Ajax.initAllAjaxEvents();Pub.Ajax.overrideAllResponseHandlers();Ext.define("Ext.data.proxy.JsonAjaxProxy",{extend:"Ext.data.proxy.Ajax",alias:"proxy.jsonajax",limitParam:null,startParam:null,pageParam:null,actionMethods:{create:"POST",read:"POST",update:"POST",destroy:"POST"},buildRequest:function(a){var b=this.callParent(arguments);b.jsonData=b.params||{};b.params={};return b},applyEncoding:function(a){return a}});Ext.define("Ext.data.proxy.JsonPagingProxy",{extend:"Ext.data.proxy.JsonAjaxProxy",alias:"proxy.jsonpaging",limitParam:"limit",startParam:"start",pageParam:"page"});Ext.override(Ext.data.Connection,{request:function(a){if(a.loadMask){if(!a.loadMaskEl){a.loadMaskEl=Ext.getBody()}if(!a.loadMaskMsg){a.loadMaskMsg="Please wait..."}a.loadMaskEl.mask(a.loadMaskMsg)}a.headers={"Content-Type":"application/json"};this.callParent(arguments)},onComplete:function(request,xdrResult){if(request.options.loadMaskEl){with(request.options){if(loadMaskEl&&loadMaskEl.isMasked()){loadMaskEl.unmask()}}}this.callParent(arguments)}});