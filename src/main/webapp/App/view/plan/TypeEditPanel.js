Ext.ns('App.view.plan');
App.view.plan.TypeEditPanel = Ext.extend(Ext.grid.GridPanel, {
	initComponent : function() {
		var me = this;
		me.tbar = [{
					xtype : 'button',
					text : '新增',
					handler : me.create,
					iconCls : 'create',
					scope : me
				}, {
					xtype : 'button',
					text : '修改',
					handler : me.modify,
					iconCls : 'modify',
					scope : me
				}, {
					xtype : 'button',
					text : '删除',
					iconCls : 'delete',
					handler : me.remove,
					scope : me
				}, {
					xtype : 'button',
					text : '编辑描述',
					iconCls : 'modify',
					handler : me.edit,
					scope : me
				}];
		var store = new Ext.data.Store({
					autoLoad : true,
					proxy : new Ext.data.HttpProxy({
								url : "App/servlet/PlanServlet.jss?method=getEdit&id="
										+ me.thisId
										+ "&type="
										+ me.model.get("type"),
								method : "get"
							}),
					reader : new Ext.data.JsonReader({
								root : 'root',
								fields : ['classType', 'count', 'hard']
							})
				});
		me.columns = [new Ext.grid.RowNumberer({
							width : 40,
							header : '序号'
						}), {
					header : '分类',
					dataIndex : 'classType'
				}, {
					header : '数量',
					dataIndex : 'count'
				}, {
					header : '难易度',
					dataIndex : 'hard'
				}];
		me.store = store;
		me.on("destroy", me.closeAction, me);
		me.on("dblclick", me.modify, me);
		App.view.plan.TypeEditPanel.superclass.initComponent.apply(this,
				arguments);
	},
	create : function() {
		var me = this;
		var win = new App.view.plan.TypeEditForm({
					method : 'createEdit',
					thisId : me.thisId,
					type : me.model.get("type"),
					store : me.store,
					showStore : me.showStore
				});
		win.show();
	},
	modify : function() {
		var me = this;
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			var win = new App.view.plan.TypeEditForm({
						method : 'modifyEdit',
						thisId : me.thisId,
						type : me.model.get("type"),
						store : me.store,
						itemId : model.json.id,
						showStore : me.showStore
					});
			win.findByType("textfield")[0].setValue(model.get('classType'));
			win.findByType("numberfield")[0].setValue(model.get('count'));
			win.findByType("combo")[0].setValue(model.get('hard'));

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
						url : 'App/servlet/PlanServlet.jss',
						params : {
							method : 'delete',
							thisId : me.thisId,
							type : me.model.get("type"),
							store : me.store,
							itemId : model.json.id
						},
						success : function() {
							me.store.load();
							me.showStore.load();
							Ext.Msg.alert('提示', '操作成功');
						}
					});
		} else {
			Ext.Msg.alert("提示", "请选择一项");
		}
	},
	edit : function() {
		var me = this;
		var win = new App.view.plan.DespEditWindow({
					method : 'editDesp',
					thisId : me.thisId,
					type : me.model.get("type"),
					store : me.store,
					showStore : me.showStore
				});
		win.findByType('textfield')[0].setValue(me.model.json.remark);
		win.findByType('textfield')[1].setValue(me.model.json.point);
		win.show();
	},
	closeAction : function() {
		console.log(this.store.data);
	}
});