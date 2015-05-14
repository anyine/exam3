Ext.onReady(function() {
	var cp = new Ext.state.CookieProvider({
		path : "/",
		expires : new Date(new Date().getTime() + (1000 * 60))
	});
	var cookie = cp.get("loginName");
	if (cookie == null) {
		var win = new App.view.system.Login();
	} else {
		var win = new App.view.system.Panel();
	}
	win.show();
});
