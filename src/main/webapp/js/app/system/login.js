/**
 * 首页登录组件
 * 
 * author sunping
 */

Ext.ns('App.system');
App.system.Login = Ext.extend(Ext.Window,{
					buttonAlign : 'center',
					width : 400,
					autoHeight : true, // 高度
					closable : false,
					resizable : false, // 不容许任意伸缩大小
					// layout : 'column', // 该面板布局类型
					draggable : false, // 窗体拖拽 默认是TRUE
					frame : true,
					border : false, // 容器内元素是否显示边框
					shadow : false,
					bodyStyle : "background-image:url('js/app/resources/image/login_bg.jpg');padding:80px 0 0",
					initComponent : function() {
						var me = this;
						me.items = {
							xtype : 'form',
							labelAlign : 'right',
							frame : true,
							defaults : {
								labelWidth : 40,
							},
							items : [
									{
										xtype : 'textfield',
										fieldLabel : '<font color=#2a4997><b>用&nbsp;户&nbsp; 名</b></font>',
										name : 'userName',
										allowBlank : false,
										emptyText:'手机号',
										blankText : '手机号不允许为空',
										msgTarget : "qtip"
									},
									{
										xtype : 'textfield',
										fieldLabel : '<font color=#2a4997><b>登录密码</b></font>',
										inputType : 'password',
										name : 'password',
										allowBlank : false,
										blankText : '密码不允许为空',
										msgTarget : "qtip",
										listeners : {
											specialkey : function(field, e) {
												if (e.getKey() == Ext.EventObject.ENTER) {
													me.login();
												}
											}
										}
									}, {  
									    html:"<a style='float:right' href='findpassword'>忘记密码</a>"  
									  } ]
						}

						me.buttons = [ {
							text : '<font size = 2><b>登  录</b></font>',
							handler : me.login,
							scope : me
						}, {
							text : '<font size = 2><b>重  置</b></font>',
							handler : function() {
								me.findByType('form')[0].getForm().reset();
							}
						}, {
							text : '<font size = 2><b>导入数据</b></font>',
							handler : me.import,
							scope : me
						}, {
							text : '<font size = 2><b>导入用户</b></font>',
							handler : me.importuser,
							scope : me
						} ]

						App.system.Login.superclass.initComponent.apply(this,
								arguments);
					},

					login : function() {
						var form = this.findByType('form')[0];
						if (form.getForm().isValid()) {
							var me = this;
							/**
							form.getForm().submit({
								url : 'system/login',
								method : 'POST',
								success : function(form, action) {
									var result = action.result;
									if (result.success) {
										me.close();
										var viewport = new Ext.View.MainView({
											"currentUser" : result.name,
											"currentBranch" : result.branch,
											"currentUserType" : result.userType
										});
									} else {
										Ext.alert.msg("提示", result.message);
									}
									me.close();
								},
								failure : function(form, action) {
									var result = action.result;
									Ext.Msg.alert("提示", result.message);
								}
							});
							**/
						} else {
							Ext.Msg.alert("提示", '请完善登录信息！');
						}
					},
					
					import : function() {
						var win = new App.View.ImportWindow({
							title : '导入机构及人员信息',
						});
						win.show();
					},
					
					importuser : function() {
//						var win = new App.View.ImportUserWindow({
//							title : '导入用户',
//						});
						var win = new App.View.InputStandardItem({
							title : '录入评分项',
						});
						win.show();
					}
				});