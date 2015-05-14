Ext.ns("App.view.plan");
App.view.plan.TypeEditForm = Ext.extend(Ext.Window, {
			buttonAlign : 'center',
			modal : true,
			initComponent : function() {
				var me = this;
				var hardStore = new Ext.data.SimpleStore({
							fields : ['id', 'name'],
							data : [[0.1, '难'], [0.5, '中'], [0.9, '易']]
						});
				me.items = [{
					xtype : 'form',
					bodyStyle : 'padding: 20px; background-color: #DFE8F6',
					defaults : {
						labelWidth : 60
					},
					items : [{
						xtype : 'textfield',
						fieldLabel : '分类',
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
						xtype : 'numberfield',
						fieldLabel : '数量',
						name : 'count'
					}, {
						xtype : 'combo',
						fieldLabel : '难易度',
						store : hardStore,
						width : 100,
						editable : false,
						triggerAction : 'all',
						valueField : 'name',
						mode : 'local',
						displayField : 'name',
						name : 'hard'
					}, {
						xtype : 'hidden',
						name : 'classType'
					}]
				}];

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
				App.view.plan.TypeEditForm.superclass.initComponent.apply(this,
						arguments);
			},
			save : function() {
				var me = this;
				me.findByType("form")[0].getForm().submit({
							url : 'App/servlet/PlanServlet.jss',
							params : {
								method : me.method,
								id : me.thisId,
								type : me.type,
								itemId : me.itemId
							},
							success : function() {
								me.store.load();
								me.showStore.load();
								me.close();
								Ext.Msg.alert("提示", "操作成功");
							}
						});
			}
		});