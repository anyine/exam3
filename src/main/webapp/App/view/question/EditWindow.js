Ext.ns('App.view.question');
App.view.question.EditWindow = Ext.extend(Ext.Window, {
	title : '编辑窗口',
	buttonAlign : 'center',
	method : '',
	idParam : '',
	initComponent : function() {
		var me = this;
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
		me.items = {
			xtype : 'form',
			bodyStyle : 'padding: 20px; background-color: #DFE8F6',
			defaults : {
				labelWidth : 40,
				width : 500
			},
			defaultType : 'textfield',
			items : [
//				{
//						fieldLabel : '题库',
//						name : 'package'
//					}, 
						{
						fieldLabel : '分类',
						allowBlank : false,
						name : '',
						readOnly : true,
						listeners : {
							focus : function() {
								var theField = this;
								var win = new App.view.type.TypePanelWindow();
								win.on("close", function() {
											theField.setValue(win.nodeName);
											me.findByType("hidden")[0]
													.setValue(win.nodeId);
										});
								win.show();
							}
						}
					}, {
						xtype : 'hidden',
						name : 'classType'
					}, {
						xtype : 'combo',
						name : 'type',
						store : st,
						editable : false,
						valueField : 'id',
						mode : 'local',
						triggerAction : 'all',
						displayField : 'name',
						fieldLabel : '题型',
						allowBlank : false
					}, {
						xtype : 'textarea',
						height : 100,
						fieldLabel : '题干',
						name : 'content',
						allowBlank : false
					}, {
						xtype : 'textarea',
						height : 100,
						fieldLabel : '选项',
						name : 'anContent'
					}, {
						fieldLabel : '答案',
						name : 'answer',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '难易度',
						store : hardStore,
						editable : false,
						triggerAction : 'all',
						valueField : 'name',
						mode : 'local',
						displayField : 'name',
						name : 'hard',
						allowBlank : false
					}, {
						fieldLabel : '备注',
						name : 'remark'
					}]
		}

		me.buttons = [{
					text : '确定',
					handler : me.save,
					scope : me
				}, {
					text : '取消',
					handler : function() {
						me.close();
					}
				}]
		App.view.question.EditWindow.superclass.initComponent.apply(this,
				arguments);
	},
	save : function(the) {
		var me = this;
		this.findByType('form')[0].getForm().submit({
					url : 'App/servlet/QuestionServlet.jss',
					params : {
						method : me.method,
						id : me.idParam
					},
					success : function(form, action) {
						var result = action.result;
						me.store.load({
									params : {
										start : 0,
										limit : 25
									}
								});
						me.close();
						Ext.Msg.alert("提示", result.message);
					}
				});
	}
});