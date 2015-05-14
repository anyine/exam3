Ext.ns('App.view.plantable');
App.view.plantable.PointEditPanel = Ext.extend(Ext.grid.GridPanel, {
	title : '制定题型分值',
	initComponent : function() {
		var me = this;
		var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
						url : "App/servlet/PlanTableServlet.jss?method=getPoint&id="
								+ me.thisId,
						method : "get"
					}),
			reader : new Ext.data.JsonReader({
						totalProperty : "total",
						root : 'root',
						fields : ['id', 'name', 'value']
					})
		});
		me.columns = [new Ext.grid.RowNumberer({
							width : 40,
							header : '序号'
						}), {
					header : '题型',
					dataIndex : 'name'
				}, {
					header : '分值',
					dataIndex : 'value'
				}];
		me.tbar = [{
					xtype : 'button',
					text : '新增',
					handler : me.create,
					scope : me
				}, {
					xtype : 'button',
					text : '修改',
					handler : me.modify,
					scope : me
				}, {
					xtype : 'button',
					text : '删除',
					handler : me.remove,
					scope : me
				}]
		me.store = store;
		me.on("dblclick", me.modify, me);
		me.store.load();

		App.view.plantable.PointEditPanel.superclass.initComponent.apply(this,
				arguments);
	},
	create : function() {
		var me = this;
		var win = new App.view.plantable.PointEditWindow({
					method : 'createPoint',
					thisId : me.thisId,
					store : me.store
				});
		win.show();
	},
	modify : function() {
		var me = this;
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			var win = new App.view.plantable.PointEditWindow({
						method : 'modifyPoint',
						thisId : me.thisId,
						modelId : model.json.id,
						store : me.store
					});
			win.findByType("combo")[0].setValue(model.get('name'));
			win.findByType("numberfield")[0].setValue(model.get('value'));
			win.show();
		} else {
			Ext.Msg.alert("提示", "请选择一项");
		}
	},
	remove : function() {
		var me = this;
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			Ext.Ajax.request({
						url : 'App/servlet/PlanTableServlet.jss',
						params : {
							method : 'deletePoint',
							modelId : model.json.id,
							id : me.thisId
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
	}
});