Ext.ns('App.view.password.PasswordPanel');
App.view.password.PasswordPanel = Ext.extend(Ext.Window, {
			title : '密码修改',
			layout : 'fit',
			buttonAlign : 'center',
			width : 300,
			height : 150,
			initComponent : function() {
				var me = this;
				me.items = [{
					xtype : 'form',
					bodyStyle : 'padding: 5px; background-color: #DFE8F6',
					items : [{
								fieldLabel : '原密码',
								xtype : 'textfield',
								name : 'oldPassword'
							}, {
								fieldLabel : '新密码',
								xtype : 'textfield',
								inputType : 'password',
								name : 'password'
							}, {
								fieldLabel : '确认密码',
								xtype : 'textfield',
								inputType : 'password',
								name : 'passwordok',
								validator : function() {
									var value1 = me.findByType('textfield')[1]
											.getValue();
									var value2 = me.findByType('textfield')[2]
											.getValue();
									if (value1 == value2) {
										return true;
									} else {
										return false;
									}
								}
							}]
				}];
				me.buttons = [{
							text : '提交',
							handler : me.save,
							scope : me
						}, {
							text : '提交',
							handler : function() {
								me.close();
							}
						}];
				App.view.password.PasswordPanel.superclass.initComponent.apply(
						this, arguments);
			},
			save : function() {
				var me = this;
				var form = me.findByType('form')[0];
				form.getForm().submit({
							url : 'App/servlet/PasswordServlet.jss',
							success : function(form, action) {
								var result = action.result;
								me.close();
								Ext.Msg.alert("提示", result.message);
							}
						});
			}
		});