Ext.ns("Ext.App.servlet");
Ext.App.servlet.PlanServlet = function(req, res) {
	var method = req.getParameter("method");
	var fac = engine.wrapper.factory;
	var svc = fac.getServiceManager("platform").getService("PlanService");
	var svc1 = fac.getServiceManager("platform").getService("QuestionService");
	var url = "plans"
	if (method == "get") {
		svc.transSelect(0, 1000, function(total, list) {
			Ext.web.Util.resJson({
				total : total,
				root : Ext.web.Util.simpleJson(list),
				success : true
			});
		});
	} else if (method == "create") {
		try {
			var id = engine.UUID;
			var plan = engine.createEntity("cn.wizool.exam.model.Plan");
			plan.id = id;
			plan.name = req.getParameter("name");
			svc.transCreate(plan);

			var plan = [ {
				type : '单项选择题'
			}, {
				type : '多项选择题'
			}, {
				type : '判断题'
			}, {
				type : '简答题'
			}, {
				type : '技能实务题'
			} ];
			Ext.web.Util.writeJson(plan, url + "\\" + id);
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
	} else if (method == "modifyPlan") {
		try {
			var id = req.getParameter("id");
			var name = req.getParameter("name");
			svc.transUpdatePlan(id, name);
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

	} else if (method == "getShowPanel") {
		var id = req.getParameter("id");
		var obj = Ext.web.Util.readJson(url + "\\" + id);
		Ext.web.Util.resJson({
			total : 0,
			root : obj,
			success : true
		});
	} else if (method == "getEdit") {
		var id = req.getParameter("id");
		var type = req.getParameter("type");
		var obj = Ext.web.Util.readJson(url + "\\" + id);
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].type == type) {
				Ext.web.Util.resJson({
					total : 0,
					root : obj[i].items,
					success : true
				});
			}
		}
	} else if (method == "createEdit") {
		try {
			var id = req.getParameter("id");
			var type = req.getParameter("type");
			var item = {
				id : String(engine.UUID),
				classType : String(svc1.transGetUrl(req
						.getParameter("classType"))),
				// point : String(req.getParameter("point")),
				count : String(req.getParameter("count")),
				// allPoint : String(req.getParameter("allPoint")),
				hard : String(req.getParameter("hard"))
			};
			var obj = Ext.web.Util.readJson(url + "\\" + id);

			var allPoint = 0;
			var count = 0;
			for ( var i = 0; i < obj.length; i++) {
				if (obj[i].type == type) {
					if (obj[i].items == null) {
						var items = [];
						items.push(item);
						obj[i].items = items;
					} else {
						obj[i].items.push(item);
					}
					// break;
				}
			}
			Ext.web.Util.writeJson(obj, url + "\\" + id);
			setShowPanel(id, type);
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
	} else if (method == "modifyEdit") {
		try {
			var id = req.getParameter("id");
			var type = req.getParameter("type");
			var itemId = req.getParameter("itemId");

			var obj = Ext.web.Util.readJson(url + "\\" + id);

			for ( var i = 0; i < obj.length; i++) {
				if (obj[i].type == type) {
					for ( var j = 0; j < obj[i].items.length; j++) {
						if (obj[i].items[j].id == itemId) {
							if (req.getParameter("classType").length() > 0) {
								obj[i].items[j].classType = String(svc1
										.transGetUrl(req
												.getParameter("classType")));
							}
							// obj[i].items[j].point = String(req
							// .getParameter("point"));
							obj[i].items[j].count = String(req
									.getParameter("count"));
							// obj[i].items[j].allPoint = String(req
							// .getParameter("allPoint"));
							obj[i].items[j].hard = String(req
									.getParameter("hard"));
							Ext.web.Util.writeJson(obj, url + "\\" + id);
							setShowPanel(id, type);
							break;
						}
					}
				}
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
	} else if (method == "delete") {
		try {
			var id = req.getParameter("thisId");
			var type = req.getParameter("type");
			var itemId = req.getParameter("itemId");

			var obj = Ext.web.Util.readJson(url + "\\" + id);

			for ( var i = 0; i < obj.length; i++) {
				if (obj[i].type == type) {
					for ( var j = 0; j < obj[i].items.length; j++) {
						if (obj[i].items[j].id == itemId) {
							obj[i].items.splice(j, 1);
							Ext.web.Util.writeJson(obj, url + "\\" + id);
							setShowPanel(id, type);
							break;
						}
					}
				}
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
	} else if (method == "make") {
		var id = req.getParameter("id");
		var obj = Ext.web.Util.readJson(url + "\\" + id);
		var questions = engine.createEntity("cn.wizool.exam.model.Questions");
		svc.transSelectById(id, function(obj) {
			questions.title = obj.name;
		});

		for ( var i = 0; i < obj.length; i++) {
			var group = engine
					.createEntity("cn.wizool.exam.model.QuestionGroup");
			group.type = obj[i].type;
			group.remark = obj[i].remark == null ? "" : obj[i].remark;
			group.count = obj[i].count == null ? 0 : obj[i].count;
			group.point = obj[i].point == null ? "0" : obj[i].point;

			if (obj[i].items != null) {
				for ( var j = 0; j < obj[i].items.length; j++) {
					var ret = ret
							+ svc1
									.transMake(
											obj[i].type,
											obj[i].items[j].classType,
											obj[i].items[j].hard,
											obj[i].items[j].count,
											function(total, list) {
												for ( var ii = 0; ii < list
														.size(); ii++) {
													var obj = list.get(ii);
													var url = svc1
															.transGetUrl(obj.classType);
													var jsonObj = Ext.web.Util
															.readJson("questions"
																	+ "\\"
																	+ obj.pack
																	+ "\\"
																	+ url
																	+ obj.id);
													var question = engine
															.createEntity("cn.wizool.exam.model.QuestionItem");
													question
															.setContent(jsonObj.content);
													question
															.setAnswer(jsonObj.answer);
													question
															.setRemark(jsonObj.remark);
													question
															.setAnContent(jsonObj.anContent);
													group.getQuestions().add(
															question);
												}
											});
				}
			}
			questions.getQgs().add(group);
		}
		ret = String(ret);
		if (ret.length == 0 || "undefined".equals(ret)) {
			engine.start("c:\\" + questions.title.split("，")[0] + "试卷.doc",
					questions);
			// engine.start("c:\\试卷.doc", questions);
			Ext.web.Util.resUploadJson({
				success : true,
				message : '成功'
			});
		} else {
			ret = ret.replace(/undefined/, "");
			Ext.web.Util.resUploadJson({
				success : true,
				message : "错误"
			});
		}
	} else if (method == "deletePlan") {
		try {
			var id = req.getParameter("id");
			svc.transDeletePlan(id);
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
	} else if (method == "editDesp") {
		try {
			var id = req.getParameter("id");
			var type = req.getParameter("type");
			var desp = String(req.getParameter("desp"));
			var point = String(req.getParameter("point"));
			var obj = Ext.web.Util.readJson(url + "\\" + id);
			for ( var i = 0; i < obj.length; i++) {
				if (obj[i].type == type) {
					obj[i].remark = desp;
					obj[i].point = point;
					obj[i].allPoint = obj[i].point * obj[i].count;
				}
			}
			Ext.web.Util.writeJson(obj, url + "\\" + id);
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

	function setShowPanel(id, type) {
		var obj = Ext.web.Util.readJson(url + "\\" + id);
		var allPoint = 0;
		var count = 0;
		for ( var i = 0; i < obj.length; i++) {
			var point = obj[i].point;
			if (obj[i].type == type) {
				var arr = obj[i].items
				for ( var j = 0; j < arr.length; j++) {
					count += Number(arr[j].count);
				}
				obj[i].allPoint = count * point;
				println(point);
				println(count);
				obj[i].count = count;
				break;
			}
		}

		Ext.web.Util.writeJson(obj, url + "\\" + id);
	}

	function replace(arr, old, newS) {
		arr[newS] = arr[old].split(";");

		if (arr[newS].length == 1 && arr[newS][0].length == 0) {
			arr[newS] = [];
		}
		delete arr[old];
	}
};