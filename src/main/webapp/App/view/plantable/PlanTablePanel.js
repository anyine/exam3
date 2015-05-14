Ext.ns('App.view.plantable');
App.view.plantable.PlanTablePanel = Ext.extend(Ext.grid.GridPanel, {
	title : '方案维护（新）',
	closable : true,
	initComponent : function() {
		var me = this;
		var store = new Ext.data.Store({
			autoLoad : true,
			proxy : new Ext.data.HttpProxy({
						url : "App/servlet/PlanTableServlet.jss?method=getPlan",
						method : "get"
					}),
			reader : new Ext.data.JsonReader({
						root : 'root',
						fields : ['id', 'name']
					})
		});
		me.store = store;
		me.columns = [new Ext.grid.RowNumberer({
							width : 40,
							header : '序号'
						}), {
					header : '方案名称 ',
					flex : 1,
					dataIndex : 'name'
				}];
		me.tbar = [{
					text : '新增',
					handler : me.create,
					scope : me
				}, {
					text : '删除',
					handler : me.remove,
					scope : me
				}, {
					text : '名称编辑',
					handler : me.modify,
					scope : me
				}, {
					text : '约束编辑',
					handler : me.edit,
					scope : me
				}]
		me.store.load();
		me.on("dblclick", me.modify, me);
		App.view.plantable.PlanTablePanel.superclass.initComponent.apply(this,
				arguments);
	},
	create : function() {
		var me = this;
		var win = new App.view.plantable.EditWindow({
					method : 'createPlan',
					store : me.store
				});
		win.show();
	},
	remove : function() {
		var me = this;
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			Ext.Ajax.request({
						url : 'App/servlet/PlanTableServlet.jss',
						params : {
							method : 'removePlan',
							id : model.json.id
						},
						success : function(result) {
							var obj = Ext.decode(result.responseText);
							Ext.Msg.alert("提示", obj.message);
							me.store.load();
						}
					});
		} else {
			Ext.Msg.alert("提示", "请选择一项");
		}
	},
	modify : function() {
		var me = this;
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			var win = new App.view.plantable.EditWindow({
						method : 'modifyPlan',
						thisId : model.json.id,
						store : me.store
					});
			win.findByType("textfield")[0].setValue(model.get("name"));
			win.show();
		} else {
			Ext.Msg.alert("提示", "请选择一项");
		}
	},
	edit : function() {
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			var win = new App.view.plantable.EditTablePanel({
						thisId : model.json.id
					});
			win.show();
		} else {
			Ext.Msg.alert("提示", "请选择一项")
		}
	}
});