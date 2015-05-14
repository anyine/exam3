Ext.ns('App.view.plantable');
App.view.plantable.EditWindow = Ext.extend(Ext.Window, {
			title : '编辑窗口',
			buttonAlign : 'center',
			method : '',
			initComponent : function() {
				var me = this;
				me.items = {
					xtype : 'form',
					bodyStyle : 'padding: 20px; background-color: #DFE8F6',
					defaults : {
						labelWidth : 60
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '方案名称',
								name : 'name',
								allowBlank : false
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
				App.view.plantable.EditWindow.superclass.initComponent.apply(
						this, arguments);
			},
			save : function(the) {
				var me = this;
				me.findByType("form")[0].getForm().submit({
							url : 'App/servlet/PlanTableServlet.jss',
							params : {
								method : me.method,
								id : me.thisId
							},
							success : function(form, action) {
								console.log(action);
								var result = action.result;
								me.store.load();
								me.close();
								Ext.Msg.alert("提示", result.message);
							}
						});
			}
		});