Ext.ns('App.view.system');
App.view.system.Login = Ext.extend(Ext.Window, {
			title : '登录窗口',
			buttonAlign : 'center',
			width : 300,
			height : 158,
			initComponent : function() {
				var me = this;
				me.items = {
					xtype : 'form',
					bodyStyle : 'padding: 20px; background-color: #DFE8F6',
					defaults : {
						labelWidth : 40,
						allowBlank : false
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '登录帐户',
								name : 'name',
								blankText : "用户不允许为空"
							}, {
								fieldLabel : '登录密码',
								inputType : 'password',
								name : 'pass',
								listeners : {
									specialkey : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											me.login();
										}
									}
								}
							}]
				}

				me.buttons = [{
							text : '登录',
							handler : me.login,
							scope : me
						}, {
							text : '重置'
						}]

				App.view.system.Login.superclass.initComponent.apply(this,
						arguments);
			},
			login : function() {
				var form = this.findByType('form')[0];
				var me = this;
				form.getForm().submit({
					url : 'login',
					success : function(form, action) {
						var result = action.result;
						if (result.success) {
							var cp = new Ext.state.CookieProvider({
										path : "/",
										expires : new Date(new Date().getTime()
												+ (1000 * 60 * 20))
									});
							cp.set("loginName", "admin");
							window.location.href = 'index.html';
							me.close();
						} else {
							Ext.Msg.alert("提示", result.message);
						}
					},
					failure : function(form, action) {
						var result = action.result;
						Ext.Msg.alert("提示", result.message);
					}
				});
			}
		});
