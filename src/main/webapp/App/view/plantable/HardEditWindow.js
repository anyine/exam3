Ext.ns('App.view.plantable');
App.view.plantable.HardEditWindow = Ext.extend(Ext.Window, {
			modal : true,
			initComponent : function() {
				var me = this;
				var hardStore = new Ext.data.SimpleStore({
							fields : ['id', 'name'],
							data : [[0.1, '难'], [0.5, '中'], [0.9, '易']]
						});
				me.items = {
					xtype : 'form',
					bodyStyle : 'padding: 20px; background-color: #DFE8F6',
					defaults : {
						labelWidth : 60
					},
					items : [{
						xtype : 'textfield',
						fieldLabel : '章节',
						name : '',
						readOnly : true,
						allowBlank : false,
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
						xtype : 'combo',
						fieldLabel : '难易度',
						store : hardStore,
						width : 100,
						editable : false,
						triggerAction : 'all',
						valueField : 'name',
						mode : 'local',
						displayField : 'name',
						name : 'value',
						allowBlank : false
					}, {
						xtype : 'hidden',
						name : 'name'
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
				App.view.plantable.HardEditWindow.superclass.initComponent
						.apply(this, arguments);
			},
			save : function() {
				var me = this;
				me.findByType("form")[0].getForm().submit({
							url : 'App/servlet/PlanTableServlet.jss',
							params : {
								method : me.method,
								id : me.thisId,
								modelId : me.modelId
							},
							success : function(form, action) {
								var result = action.result;
								me.close();
								me.store.load();
								Ext.Msg.alert("提示", result.message);
							}
						});
			}
		});