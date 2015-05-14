Ext.ns('App.view.plan');
App.view.plan.DespEditWindow = Ext.extend(Ext.Window, {
			title : '描述编辑',
			buttonAlign : 'center',
			initComponent : function() {
				var me = this;
				me.items = [{
							xtype : 'form',
							bodyStyle : 'padding: 20px; background-color: #DFE8F6',
							defaults : {
								labelWidth : 60
							},
							defaultType : 'textfield',
							items : [{
										fieldLabel : '题型描述',
										name : 'desp'
									}, {
										fieldLabel : '分值',
										name : 'point'
									}]
						}]
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
				App.view.plan.DespEditWindow.superclass.initComponent.apply(
						this, arguments);
			},
			save : function() {
				var me = this;
				me.findByType('form')[0].getForm().submit({
							url : 'App/servlet/PlanServlet.jss',
							params : {
								method : me.method,
								id : me.thisId,
								type:me.type
							},
							success : function() {
								me.store.load();
								me.showStore.load();
								me.close();
								Ext.Msg.alert("提示", "操作成功");
							}
						});
			}
		})