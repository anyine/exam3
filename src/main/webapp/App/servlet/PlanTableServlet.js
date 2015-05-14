Ext.ns("Ext.App.servlet");
Ext.App.servlet.PlanTableServlet = function(req, res) {
	var method = req.getParameter("method");
	var fac = engine.wrapper.factory;
	var svc = fac.getServiceManager("platform").getService("PlanService");
	var svc1 = fac.getServiceManager("platform").getService("QuestionService");
	if (method == "getClassType") {
		var id = req.getParameter("id");
		svc.transSelectById(id, function(obj) {
					var property = Ext.web.Util.simpleJson(obj.classTypes);
					for (var p in property) {
						property[p].name = String(svc1
								.transGetUrl(property[p].name));
					}
					Ext.web.Util.resJson({
								total : obj.classTypes.size(),
								root : property,
								success : true
							});
				});
	} else if (method == "createClassType") {
		try {
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var id = req.getParameter("id");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = name;
			property.value = value;
			property.id = engine.UUID;
			svc.transCreateProperty(id, property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "modifyClassType") {
		try {
			var id = req.getParameter("id");
			var modelId = req.getParameter("modelId");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.id = modelId;
			property.name = String(req.getParameter("name"));
			property.value = String(req.getParameter("value"));
			svc.transUpdateProperty(property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "deleteClassType") {
		try {
			var id = req.getParameter("id");
			var modelId = req.getParameter("modelId");
			respSuccess();
		} catch (e) {
			respFail();
		}
		svc.transDeleteClassType(id, modelId);
	} else if (method == "getQuestionType") {
		var id = req.getParameter("id");
		svc.transSelectById(id, function(obj) {
					var property = Ext.web.Util.simpleJson(obj.questionTypes);
					Ext.web.Util.resJson({
								total : obj.classTypes.size(),
								root : property,
								success : true
							});
				});
	} else if (method == "createQuestionType") {
		try {
			var id = req.getParameter("id");
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(engine.UUID);
			svc.transCreateQuestionType(id, property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "modifyQuestionType") {
		try {
			var modelId = req.getParameter("modelId");
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(modelId);
			svc.transUpdateQuestionType(property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "deleteQuestionType") {
		try {
			var id = req.getParameter("id");
			var modelId = req.getParameter("modelId");
			svc.transDeleteQuestionType(id, modelId);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "getMix") {
		var id = req.getParameter("id");
		svc.transSelectById(id, function(obj) {
					var property = Ext.web.Util.simpleJson(obj.mix);
					for (var p in property) {
						property[p].name = String(svc1
								.transGetUrl(property[p].name));
					}
					Ext.web.Util.resJson({
								total : obj.mix.size(),
								root : property,
								success : true
							});
				});
	} else if (method == "createMix") {
		try {
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var id = req.getParameter("id");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(engine.UUID);
			svc.transCreateMix(id, property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "modifyMix") {
		try {
			var modelId = req.getParameter("modelId");
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(modelId);
			svc.transUpdateMix(property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "deleteMix") {
		try {
			var id = req.getParameter("id");
			var modelId = req.getParameter("modelId");
			svc.transDeleteMix(id, modelId);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "getHard") {
		var id = req.getParameter("id");
		svc.transSelectById(id, function(obj) {
					var property = Ext.web.Util.simpleJson(obj.hard);
					for (var p in property) {
						property[p].name = String(svc1
								.transGetUrl(property[p].name));
					}
					Ext.web.Util.resJson({
								total : obj.hard.size(),
								root : property,
								success : true
							});
				});
	} else if (method == "createHard") {
		try {
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var id = req.getParameter("id");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(engine.UUID);
			svc.transCreateHard(id, property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "modifyHard") {
		try {
			var modelId = req.getParameter("modelId");
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(modelId);
			svc.transUpdateHard(property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "deleteHard") {
		try {
			var id = req.getParameter("id");
			var modelId = req.getParameter("modelId");
			svc.transDeleteHard(id, modelId);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "removePlan") {
		try {
			var id = req.getParameter("id");
			svc.transDeletePlan(id);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "createPlan") {
		try {
			var id = engine.UUID;
			var plan = engine.createEntity("cn.wizool.exam.model.Plan");
			plan.id = id;
			plan.name = req.getParameter("name");
			svc.transCreate(plan);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "getPlan") {
		svc.transSelect(0, 1000, function(total, list) {
					Ext.web.Util.resJson({
								total : total,
								root : Ext.web.Util.simpleJson(list),
								success : true
							});
				});
	} else if (method == "modifyPlan") {
		try {
			var id = req.getParameter("id");
			var name = req.getParameter("name");
			var plan = engine.createEntity("cn.wizool.exam.model.Plan");
			plan.id = id;
			plan.name = name;
			svc.transUpdatePlan(plan);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "createPoint") {
		try {
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var id = req.getParameter("id");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(engine.UUID);
			svc.transCreatePoint(id, property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "getPoint") {
		var id = req.getParameter("id");
		svc.transSelectById(id, function(obj) {
					var property = Ext.web.Util.simpleJson(obj.point);
					Ext.web.Util.resJson({
								total : obj.point.size(),
								root : property,
								success : true
							});
				});
	} else if (method == "modifyPoint") {
		try {
			var modelId = req.getParameter("modelId");
			var name = req.getParameter("name");
			var value = req.getParameter("value");
			var property = engine.createEntity("cn.wizool.exam.model.Property");
			property.name = String(name);
			property.value = String(value);
			property.id = String(modelId);
			svc.transUpdatePoint(property);
			respSuccess();
		} catch (e) {
			respFail();
		}
	} else if (method == "deletePoint") {
		try {
			var id = req.getParameter("id");
			var modelId = req.getParameter("modelId");
			svc.transDeletePoint(id, modelId);
			respSuccess();
		} catch (e) {
			respFail();
		}
	}

	function respSuccess() {
		Ext.web.Util.resJson({
					success : true,
					message : '操作成功'
				});
	}

	function respFail() {
		Ext.web.Util.resJson({
					success : true,
					message : '操作失败'
				});
	}
};
