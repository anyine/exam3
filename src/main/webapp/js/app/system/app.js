Ext.BLANK_IMAGE_URL = "js/extjs/resources/images/default/s.gif";
Ext.QuickTips.init();// 显示消息
Ext.form.Field.prototype.msgTarget = 'qtip';// 再设置提示样式。
Ext.onReady(function() {
			var win = new App.system.Login();
			win.show();
});