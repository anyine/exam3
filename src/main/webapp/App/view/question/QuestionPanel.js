Ext.ns('App.view.question');
App.view.question.QuestionPanel = Ext.extend(Ext.grid.GridPanel, {
	title : '试题维护',
	iconCls : 'user',
	closable : true,
	initComponent : function() {
		var me = this;
		var me = this;
		var sm = new Ext.grid.CheckboxSelectionModel();
		me.sm = sm;
		var store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : "App/servlet/QuestionServlet.jss?method=get"
							}),
					reader : new Ext.data.JsonReader({
								totalProperty : "total",
								root : 'root',
								fields : ['package', 'classType', 'type',
										'content', 'anContent', 'answer',
										'hard', 'remark']
							})
				});
		var st = new Ext.data.Store({
					autoLoad : true,
					proxy : new Ext.data.HttpProxy({
								url : "App/servlet/QuestionTypeServlet.jss?method=get",
								method : "get"
							}),
					reader : new Ext.data.JsonReader({
								root : 'root',
								fields : ['id', 'name']
							})
				});
		var hardStore = new Ext.data.SimpleStore({
					fields : ['id', 'name'],
					data : [[0.1, '难'], [0.5, '中'], [0.9, '易']]
				});
		me.store = store;

		me.tbar = [{
					xtype : 'button',
					text : '新增',
					iconCls : 'create',
					handler : me.create,
					scope : me
				},{
					xtype : 'button',
					text : '修改',
					iconCls : 'modify',
					handler : me.modify,
					scope : me
				}, {
					xtype : 'button',
					text : '删除',
					iconCls : 'delete',
					handler : me.remove,
					scope : me
				}, {
					xtype : 'button',
					text : '清空',
					iconCls : 'delete',
					handler : me.removeAll,
					scope : me
				},{
					xtype : 'button',
					text : '导入',
					iconCls : 'import',
					handler : me.importFile,
					scope : me
				}, {
					xtype : 'button',
					text : '导出',
					iconCls : 'import',
					handler : me.output,
					scope : me
				},  '-',"->", {
					xtype : 'label',
					text : '分类：'
				}, {
					xtype : 'textfield',
					width : 100,
					name : '',
					readOnly : true,
					listeners : {
						focus : function() {
							var theField = this;
							var win = new App.view.type.TypePanelWindow();
							win.on("close", function() {
										theField.setValue(win.nodeName);
										me.typeId = win.nodeId;
									});
							win.show();
						}
					}
				}, '-', {
					xtype : 'label',
					text : '题型：'
				}, {
					xtype : 'combo',
					name : 'type',
					width : 120,
					store : st,
					// editable : false,
					valueField : 'id',
					mode : 'local',
					triggerAction : 'all',
					displayField : 'name',
					listeners : {
						select : function() {
							var combo = this;
							me.type = this.getRawValue();
						},
						blur:function() {
							var combo = this;
							me.type = this.getRawValue();
						}
					}
				}, '-', {
					xtype : 'label',
					text : '难易度：'
				}, {
					xtype : 'combo',
					store : hardStore,
					width : 120,
					// editable : false,
					triggerAction : 'all',
					valueField : 'name',
					mode : 'local',
					displayField : 'name',
					name : 'hard',
					listeners : {
						select : function() {
							var combo = this;
							me.hard = combo.getRawValue();
						},
						blur:function() {
							var combo = this;
							me.hard = combo.getRawValue();
						}
					}
				}, '-', {
					xtype : 'label',
					text : '题干：'
				}, {
					xtype : 'textfield',
					width : 150,
					name : 'content',
					listeners:{
						blur:function(){
							me.content = this.getValue();
						}					
					}
				}, '-', {
					xtype : 'button',
					text : '查询',
					iconCls : 'query',
					handler : me.query,
					scope : me
				}, '-']

		me.columns = [new Ext.grid.RowNumberer({
							width : 40,
							header : '序号'
						}),sm
//						, {
//					header : '题库',
//					flex : 1,
//					dataIndex : 'package'
//				} ,
						
					,{
					header : '分类',
					width:200,
					dataIndex : 'classType'
				}, {
					header : '题型',
					flex : 1,
					dataIndex : 'type'
				}, {
					header : '题干',
					flex : 1,
					dataIndex : 'content'
				}, {
					header : '选项',
					flex : 1,
					dataIndex : 'anContent'
				}, {
					header : '答案',
					flex : 1,
					dataIndex : 'answer'
				}, {
					header : '难易度',
					flex : 1,
					dataIndex : 'hard'
				}, {
					header : '备注',
					flex : 1,
					dataIndex : 'remark'
				}];
		me.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});
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
		me.on('dblclick', me.modify, me);
		App.view.question.QuestionPanel.superclass.initComponent.apply(this,
				arguments);
	},
	create : function() {
		var me = this;
		var win = new App.view.question.EditWindow({
					method : 'create',
					store : me.store
				});
		win.show();
	},
	getIds:function(model){
		var ids = '';
		for(var i =0;i<model.length;i++){
			ids +=model[i].json.id+" ";
		}
		return ids;
	},
	remove : function() {
		var me = this;
		var model = this.getSelectionModel().selections.items;
		if (model.length >= 1) {
			Ext.Ajax.request({
						url : 'App/servlet/QuestionServlet.jss',
						params : {
							id : me.getIds(model),
							method : "delete"
						},
						success : function(result) {
							var obj = Ext.decode(result.responseText);
							me.store.load({
										params : {
											start : 0,
											limit : 25
										}
									});
							Ext.Msg.alert("提示", obj.message);
						}
					});
		}else{
			Ext.Msg.alert('提示', '请选择至少一项');
		}
	},
	modify : function() {
		var count = this.getSelectionModel().selections.items.length;
		if (count == 1) {
			var model = this.getSelectionModel().selections.items[0];
			var win = new App.view.question.EditWindow({
						method : 'modify',
						store : this.store,
						idParam : model.json.id
					});
//			win.findByType("textfield")[0].setValue(model.get("package"));
			win.findByType("textfield")[0].readOnly = true;
			win.findByType("hidden")[0].setValue(model.get("classType"));
			win.findByType("textfield")[0].setValue(model.get("classType"));
			win.findByType("textfield")[0].setDisabled(true);
			win.findByType("textfield")[0].removeListener("focus");
			win.findByType("combo")[0].setValue(model.get("type"));
			win.findByType("combo")[0].setVisible(false);
			win.findByType("textarea")[0].setValue(model.get("content"));
			win.findByType("textarea")[1].setValue(model.get("anContent"));
			win.findByType("textfield")[1].setValue(model.get("answer"));
			win.findByType("combo")[1].setValue(model.get("hard"));
			win.findByType("combo")[1].setVisible(false);
			win.findByType("textfield")[2].setValue(model.get("remark"));
			win.show();

		} else {
			Ext.Msg.alert('提示', '请选择一项');
		}
	},
	query : function() {
		var me = this;
		console.log(me.hard);
		console.log(me.type);
		console.log(me.typeId);
		me.store.load({
					params : {
						start : 0,
						limit : 25,
						hard : me.hard,
						type : me.type,
						classType : me.typeId,
						content:me.content
					}
				});
	},
	removeAll:function(){
		var me = this;
		Ext.Ajax.request({
						url : 'App/servlet/QuestionServlet.jss',
						params : {
							method : "deleteAll"
						},
						success : function(result) {
							var obj = Ext.decode(result.responseText);
							me.store.load({
										params : {
											start : 0,
											limit : 25
										}
									});
							Ext.Msg.alert("提示", obj.message);
						}
					});
	},
	output:function(){
		var me = this;
		Ext.Ajax.request({
						url : 'App/servlet/QuestionServlet.jss',
						params : {
							method : "output"
						},
						success : function(result) {
							var obj = Ext.decode(result.responseText);
							me.store.load({
										params : {
											start : 0,
											limit : 25
										}
									});
							Ext.Msg.alert("提示", obj.message);
						}
					});
	},
	importFile : function() {
		var win = new App.view.question.ImportFild({
					store : this.store
				});
		win.show();
	}
});