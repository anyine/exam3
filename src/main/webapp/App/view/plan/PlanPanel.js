Ext.ns('App.view.plan');
App.view.plan.PlanPanel = Ext.extend(Ext.grid.GridPanel,
		{
			title : '方案维护',
			closable : true,
			iconCls : 'user',
			closeAction : "hide",
			initComponent : function() {
				var me = this;
				var store = new Ext.data.Store({
					autoLoad : true,
					proxy : new Ext.data.HttpProxy({
						url : "App/servlet/PlanServlet.jss?method=get",
						method : "get"
					}),
					reader : new Ext.data.JsonReader({
						root : 'root',
						fields : [ 'id', 'name' ]
					})
				});
				me.store = store;
				me.columns = [ new Ext.grid.RowNumberer({
					width : 40,
					header : '序号'
				}), {
					header : '方案名称 ',
					flex : 1,
					dataIndex : 'name'
				} ];
				me.tbar = [ {
					text : '新增',
					handler : me.create,
					iconCls : 'create',
					scope : me
				}, '-', {
					text : '删除',
					iconCls : 'delete',
					handler : me.remove,
					scope : me
				}, '-', {
					text : '名称编辑',
					iconCls : 'modify',
					handler : me.modify,
					scope : me
				}, '-', {
					text : '约束编辑',
					iconCls : 'modify',
					handler : me.edit,
					scope : me
				}, '-', {
					text : '生成试题',
					iconCls : 'download',
					handler : me.make,
					scope : me
				} ]
				me.on('dblclick', me.modify, me);
				me.store.load();
				App.view.plan.PlanPanel.superclass.initComponent.apply(this,
						arguments);
			},
			create : function() {
				var me = this;
				var win = new App.view.plan.EditWindow({
					method : 'create',
					store : me.store
				});
				win.show();
			},
			remove : function() {
				var me = this;
				var count = this.getSelectionModel().selections.items.length;
				if (count >= 1) {
					var model = this.getSelectionModel().selections.items[0];
					Ext.Ajax.request({
						url : 'App/servlet/PlanServlet.jss',
						params : {
							method : 'deletePlan',
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
			},
			modify : function() {
				var me = this;
				var me = this;
				var count = this.getSelectionModel().selections.items.length;
				if (count >= 1) {
					var model = this.getSelectionModel().selections.items[0];
					var win = new App.view.plan.EditWindow({
						method : 'modifyPlan',
						store : me.store,
						thisId : model.json.id
					});
					win.findByType("textfield")[0].setValue(model.get('name'));
					win.show();
				} else {
					Ext.Msg.alert('提示', '请选择一项');
				}
			},
			edit : function() {
				var count = this.getSelectionModel().selections.items.length;
				if (count == 1) {
					var model = this.getSelectionModel().selections.items[0];
					var win = new App.view.plan.PlanShowWindow({
						id : model.get("id")
					});
					win.show();
				} else {
					Ext.Msg.alert("提示", "请选择一项")
				}
			},
			make : function() {
				var me = this;
				var count = this.getSelectionModel().selections.items.length;
				var toolbar = me.topToolbar.items.items[8];
				if (count == 1) {
					var model = this.getSelectionModel().selections.items[0];
					Ext.Msg.confirm('提示窗口？', '确定要生成试卷及答案吗?', function(btn) {
						if (btn == 'yes') {
							toolbar.disable();// 按钮不可用
							var wait = new App.view.plan.WaitWindow({
								width : 300,
								title : '根据方案抽取试卷，请耐心等待......'
							});
							wait.show();
							Ext.Ajax.request({
								url : 'App/servlet/PlanServlet.jss?id='
										+ model.get("id") + "&method=make",
								timeout : 1200000,// 超时时间：20分钟
								params : {
									id : model.get("id"),
									method : "make"
								},
								success : function(result) {
									wait.close();
									toolbar.enable();// 按钮可用
									var obj = Ext.decode(result.responseText);
									if (obj.message == '成功') {
										window.frames["iframe1"].location.href="df?name="+model.get('name')+"&type=page";
										window.frames["iframe2"].location.href="df?name="+model.get('name')+"&type=answer";
									}
								},
								failure : function(form, action) {
									var result = action.result;
									if (result) {
										Ext.Msg.alert("提示", result.message);
									}
									wait.close();
									toolbar.enable();// 按钮可用
								}
							});
						}
					});
				} else {
					Ext.Msg.alert("提示", "请选择一项")
				}
			}
		});