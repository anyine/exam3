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
//				var task = {
//					run : function() {
//						Ext.Ajax.request({
//									url : 'App/servlet/KeyVal.jss',
//									success : function(result) {
//										var obj = Ext
//												.decode(result.responseText);
//										if (!obj.success) {
//											var cp = new Ext.state.CookieProvider(
//													{
//														path : "/"
//													});
//											cp.clear("loginName");
//											window.location.href = 'index.html';
//											alert("请插入UsbKey再继续操作");
//										}
//									}
//								});
//					},
//					interval : 3000
//				}
//				var runner = new Ext.util.TaskRunner();
//				runner.start(task);
			}
			win.show();
		});

