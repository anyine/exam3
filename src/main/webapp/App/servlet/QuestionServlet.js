Ext.ns("Ext.App.servlet");
Ext.App.servlet.QuestionServlet = function(req, res) {
	var method = req.getParameter('method');
	var fac = engine.wrapper.factory;
	var svc = fac.getServiceManager("platform").getService("QuestionService");
	var svc1 = fac.getServiceManager("platform").getService("ExcelService");

	var start = req.getParameter('start');
	var limit = req.getParameter('limit');
	var examFile = "questions";
	if (method == "get") {
		var classType = req.getParameter("classType");
		var type = req.getParameter("type");
		var hard = req.getParameter("hard");
		var content = req.getParameter("content");
		svc.transSelect(start, limit, classType, type, hard, function(total,
						list) {
					var resArr = [];
					for (var i = 0; i < list.size(); i++) {
						var obj = list.get(i);
						var url = svc.transGetUrl(obj.classType);
						var jsonObj = Ext.web.Util.readJson(examFile + "\\"
								+ obj.pack + "\\" + url + obj.id);
						jsonObj.classType = String(svc
								.transGetUrl(obj.classType));
						jsonObj.type = String(obj.type);
						jsonObj.hard = String(obj.hard);
						jsonObj.id = String(obj.id);
						jsonObj["package"] = String(obj.pack);
						if (content == null) {
							resArr.push(jsonObj);
						} else {
							if (jsonObj.content.indexOf(String(content)) != -1) {
								resArr.push(jsonObj);
							}
						}

					}
					Ext.web.Util.resJson({
								total : content == null || content == ""
										? total
										: resArr.length,
								root : resArr,
								success : true
							});
				});

	} else if (method == "output") {
		svc.transSelectAll(function(total,
						list) {
					for (var i = 0; i < list.size(); i++) {
						var obj = list.get(i);
						var url = svc.transGetUrl(obj.classType);
						var jsonObj = Ext.web.Util.readJson(examFile + "\\"
								+ obj.pack + "\\" + url + obj.id);
						obj.content = jsonObj.content;
						obj.anContent = jsonObj.anContent;
						obj.answer = jsonObj.answer;
						obj.remark = jsonObj.remark;
					}
					svc.transOutput(list);
				});

	} else if (method == "create") {
		try {
			var id = engine.UUID;
			var question = {
				content : String(req.getParameter("content")),
				anContent : String(req.getParameter("anContent")),
				answer : String(req.getParameter("answer")),
				remark : String(req.getParameter("remark"))
			};

			var url = svc.transGetUrl(req.getParameter("classType"));
			Ext.web.Util.writeJson(question, examFile + "\\"
							+ req.getParameter("package") + "\\" + url + id);

			var obj = engine.createEntity("cn.wizool.exam.model.Question");
			obj.id = id;
			obj.classType = req.getParameter("classType");
			obj.type = req.getParameter("type");
			obj.hard = req.getParameter("hard");
			obj.pack = req.getParameter("package");
			svc.transCreate(obj);
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
			var id = req.getParameter("id");
			var question = {
				content : String(req.getParameter("content")),
				anContent : String(req.getParameter("anContent")),
				answer : String(req.getParameter("answer")),
				remark : String(req.getParameter("remark"))
			};
			print(examFile + "\\" + req.getParameter("package") + "\\"
					+ req.getParameter("classType") + id);
			Ext.web.Util.writeJson(question, examFile + "\\"
							+ req.getParameter("package") + "\\"
							+ req.getParameter("classType") + id);
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
	} else if (method == "deleteAll") {
		try {
			svc.transDeleteAll();
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
	} else {
		try {
			engine.println("fsdf" + req.getPartMap().keySet());
			if (req.getPart("url").isFile()) {
				println(req.getPart("url").fileName);
				req.getPart("url").writeTo("c://test.xls");
				svc1
						.transImport("c://test.xls",
								"C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\root\\questions");
			}
			Ext.web.Util.resUploadJson({
						success : true,
						message : '操作成功'
					});
		} catch (e) {
			Ext.web.Util.resUploadJson({
						success : true,
						message : '操作失败'
					});
		}
	}
};