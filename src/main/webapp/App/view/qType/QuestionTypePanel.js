Ext.ns('App.view.qType');
App.view.qType.QuestionTypePanel = Ext.extend(Ext.grid.GridPanel, {
	title : '题型维护',
	closable : true,
	iconCls : 'user',
	closeAction : "hide",
	initComponent : function() {
		var me = this;
		var store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : "App/servlet/QuestionTypeServlet.jss?method=get",
								method : "get"
							}),
					reader : new Ext.data.JsonReader({
								totalProperty : "total",
								root : 'root',
								fields : ['name', 'id', 'remark']
							})
				});
		me.store = store;

		me.columns = [new Ext.grid.RowNumberer({
							width : 40,
							header : '序号'
						}), {
					header : '名称',
					flex : 1,
					dataIndex : 'name'
				}, {
					header : '备注',
					flex : 1,
					dataIndex : 'remark'
				}];
		// var bbar = new Ext.PagingToolbar({
		// store : store,
		// pageSize : me.pageSize,
		// afterPageText : '页 共{0}页',
		// beforePageText : '当前第',
		// displayInfo : true,
		// displayMsg : '第{0}条-第{1}条 共{2}条',
		// emptyMsg : '没有可以显示的数据',
		// firstText : '首页',
		// lastText : '尾页',
		// prevText : '上一页',
		// nextText : '下一页',
		// refreshText : '刷新'
		// });

		// me.bbar = bbar;
		me.tbar = [{
					xtype : 'button',
					text : '新增',
					handler : me.add,
					iconCls : 'create',
					scope : me
				}, '-', {
					xtype : 'button',
					text : '修改',
					iconCls : 'modify',
					handler : me.modify,
					scope : me
				}, '-', {
					xtype : 'button',
					iconCls : 'delete',
					text : '删除',
					handler : me.remove,
					scope : me
				}, '-'];
		me.on('dblclick', me.modify, me);
		store.load();
		App.view.qType.QuestionTypePanel.superclass.initComponent.apply(this,
				arguments);
	},
	add : function() {
		var win = new App.view.qType.EditWindow({
					method : 'create',
					thisGrid : this
				});
		win.show();
	},
	modify : function() {
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			var win = new App.view.qType.EditWindow({
						method : 'modify',
						id : model.get("id"),
						thisGrid : this
					});
			win.findByType("textfield")[0].setValue(model.get("name"));
			win.findByType("textfield")[1].setValue(model.get("remark"));
			win.show();
		} else {
			Ext.Msg.alert('提示', '请选择一项');
		}
		// console.log(this.getSelectionModel().selections.items[0].get('id'));
	},
	remove : function() {
		var me = this;
		var count = this.getSelectionModel().selections.items.length;
		if (count >= 1) {
			var model = this.getSelectionModel().selections.items[0];
			Ext.Ajax.request({
						url : 'App/servlet/QuestionTypeServlet.jss',
						params : {
							method : 'delete',
							id : model.get("id")
						},
						success : function(result) {

							var obj = Ext.decode(result.responseText);
							Ext.Msg.alert("提示", obj.message);

							me.store.load();
						}
					});
		} else {
			Ext.Msg.alert('提示', '请选择一项');
		}
	}
});