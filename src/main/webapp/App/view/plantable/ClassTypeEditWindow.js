Ext.ns('App.view.plantable');
App.view.plantable.ClassTypeEditWindow = Ext.extend(Ext.Window, {
			modal : true,
			initComponent : function() {
				var me = this;
				me.items = {
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
						xtype : 'numberfield',
						fieldLabel : '分数',
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
				App.view.plantable.ClassTypeEditWindow.superclass.initComponent
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