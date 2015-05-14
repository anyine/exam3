Ext.ns('App.view.plan');
App.view.plan.PlanShowPanel = Ext.extend(Ext.grid.GridPanel, {
	closable : true,
	initComponent : function() {
		var me = this;
		console.log(me.thisId);
		var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
						url : "App/servlet/PlanServlet.jss?method=getShowPanel&id="
								+ me.thisId,
						method : "get"
					}),
			reader : new Ext.data.JsonReader({
						root : 'root',
						fields : ['type', 'remark', 'point', 'allPoint',
								'count']
					})
		});
		me.columns = [new Ext.grid.RowNumberer({
							width : 40,
							header : '序号'
						}), {
					header : '题型',
					dataIndex : 'type'
				}, {
					header : '描述',
					dataIndex : 'remark'
				}, {
					header : '分值',
					dataIndex : 'point'
				}, {
					header : '总分 ',
					dataIndex : 'allPoint'
				}, {
					header : '数量',
					dataIndex : 'count'
				}];
		me.store = store;
		me.store.load();
		me.on('dblclick', me.dblaction, me);
		Ext.applyIf(me, {
					bbar : new Ext.PagingToolbar({
								store : me.store,
								pageSize : 25,
								displayInfo : false,
								emptyMsg : "",
								prevText : "上一页",
								nextText : "下一页",
								refreshText : "刷新",
								lastText : "最后页",
								firstText : "第一页",
								beforePageText : "当前页",
								displayInfo: true,
								displayMsg : '第{0} 到 {1} 条，共 {2}条',
								afterPageText : "共{0}页"
							})
				});
		App.view.plan.PlanShowPanel.superclass.initComponent.apply(this,
				arguments);
	},
	dblaction : function(a, b) {
		var me = this;
		var model = this.getSelectionModel().selections.items[0];
		var win = new App.view.plan.TypeEditWindow({
					model : model,
					thisId : me.thisId,
					showStore : me.store
				});
		win.show();
	}
});