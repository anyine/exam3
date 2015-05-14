Ext.ns("Ext.App.servlet");
Ext.App.servlet.TypeServlet = function(req, res) {

	var method = req.getParameter('method');
	var fac = engine.wrapper.factory;
	var svc = fac.getServiceManager("platform").getService("ClassTypeService");
	if (method == 'get') {
		var fn = function(param) {
			var arr = [];
			for (var i = 0; i < param.size(); i++) {
				var obj = {};
				obj.text = String(param.get(i).name);
				obj.remark = String(param.get(i).remark);
				obj.id = String(param.get(i).id);
				if (param.get(i).childNodes.size() == 0) {
					obj.leaf = true;
				} else {
					obj.children = fn(param.get(i).childNodes);
				}
				arr.push(obj);
			}
			return arr;
		}
		var result = [];
		svc.transSelectById("root", function(obj) {
					result = fn(obj.childNodes);
					Ext.web.Util.resJson(result);
				});

	} else if (method == 'create') {
		try {
			var name = req.getParameter('name');
			var remark = req.getParameter('remark');
			var parentId = req.getParameter("parentId");
			var fac = engine.wrapper.factory;
			var svc = fac.getServiceManager("platform")
					.getService("ClassTypeService");
			var classType = engine
					.createEntity("cn.wizool.exam.model.ClassType");
			classType.id = engine.UUID;
			classType.name = name;
			classType.remark = remark;
			svc.transCreate(classType, parentId);
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
			var name = req.getParameter('name');
			var remark = req.getParameter('remark');
			var id = req.getParameter("parentId");
			var classType = engine
					.createEntity("cn.wizool.exam.model.ClassType");
			classType.name = name;
			classType.remark = remark;
			svc.transUpdate(id, classType);
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
			svc.transDelete(id);
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