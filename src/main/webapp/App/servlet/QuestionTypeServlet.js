Ext.ns("Ext.App.servlet");
Ext.App.servlet.QuestionTypeServlet = function(req, res) {
	var method = req.getParameter("method");
	var fac = engine.wrapper.factory;
	var svc = fac.getServiceManager("platform")
			.getService("QuestionTypeService");
	if (method == "get") {
		svc.transSelectAll(0, 1000, function(total, list) {
					Ext.web.Util.resJson({
								total : total,
								root : Ext.web.Util.simpleJson(list),
								success : true
							});
				});
	} else if (method == "create") {
		try {
			var name = req.getParameter("name");
			var remark = req.getParameter("remark");
			var type = engine.createEntity("cn.wizool.exam.model.QuestionType");
			type.id = engine.UUID;
			type.name = name;
			type.remark = remark;
			svc.transCreate(type);
			Ext.web.Util.resJson({
						success : true,
						message : '操作成功'
					});
		} catch (e) {
			Ext.web.Util.resJson({
						success : true,
						message : '操作失败'
					});
		}
	} else if (method == "modify") {
		try {
			var name = req.getParameter("name");
			var remark = req.getParameter("remark");
			var id = req.getParameter("id");
			var type = engine.createEntity("cn.wizool.exam.model.QuestionType");
			type.id = id;
			type.name = name;
			type.remark = remark;
			svc.transUpdate(type);
			Ext.web.Util.resJson({
						success : true,
						message : '操作成功'
					});
		} catch (e) {
			Ext.web.Util.resJson({
						success : true,
						message : '操作失败'
					});
		}
	} else if (method == "delete") {
		try {
			var id = req.getParameter("id");
			svc.trandDelete(id);
			Ext.web.Util.resJson({
						success : true,
						message : '操作成功'
					});
		} catch (e) {
			Ext.web.Util.resJson({
						success : true,
						message : '操作失败'
					});
		}
	}
};