Ext.ns("Ext.App.servlet");
Ext.App.servlet.PasswordServlet = function(req, res) {
	var oldpassword = req.getParameter("oldPassword");
	var password = req.getParameter("password");
	var obj = Ext.web.Util.readJson("users\\user.js");
	if (obj.password != oldpassword) {
		Ext.web.Util.resJson({
					success : true,
					message : "原密码输入有误"
				});
	} else {
		Ext.web.Util.writeJson({
					username : 'admin',
					password : String(password)
				}, "users\\user.js");
		Ext.web.Util.resJson({
					success : true,
					message : "修改成功"
				});
	}
};