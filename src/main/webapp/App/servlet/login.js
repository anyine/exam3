Ext.ns("Ext.App.servlet");
Ext.App.servlet.login = function(req, res) {
	if (engine.getKeyReady()) {
		try {
			var obj = Ext.web.Util.readJson("users\\user.js");
			if (obj) {
				var name = req.getParameter('name');
				var password = req.getParameter('password');
				if (name == obj.username && password == obj.password) {
					var obj = {
						success : true
					};
				} else {
					var obj = {
						success : false,
						message : "用户名或密码错误"
					};
				}
			}
		} catch (e) {
			Ext.web.Util.writeJson({
						username : 'admin',
						password : 'admin'
					}, "users\\user.js");
			var name = req.getParameter('name');
			var password = req.getParameter('password');
			if (name == 'admin' && password == 'admin') {
				var obj = {
					success : true
				};
			} else {
				var obj = {
					success : false,
					message : "用户名或密码错误"
				};
			}
		}
	} else {
		var obj = {
			success : false,
			message : '请插入UsbKey再继续操作'
		};
	}
	Ext.web.Util.resJson(obj);
};