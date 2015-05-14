Ext.ns('App.view.qType');
App.view.qType.EditWindow = Ext.extend(Ext.Window, {
			title : '编辑窗口',
			buttonAlign:'center',
			method:'',
			initComponent : function() {
				var me = this;
				me.items = {
					xtype : 'form',
					bodyStyle : 'padding: 20px; background-color: #DFE8F6',
					defaults : {
						labelWidth : 40
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '名称',
								name : 'name',
								allowBlank : false
							}, {
								fieldLabel : '备注',
								name : 'remark'
							},{
								xtype:'hidden',
								name:'parent'
							},{
								xtype:'hidden',
								name:'edit'
							}]
				}
				
				me.buttons =[{
					text:'确定',
					handler:me.save,
					scope:me
				},{
					text:'取消',
					handler:function(){
						me.close();
					}
				}]
				App.view.type.EditWindow.superclass.initComponent.apply(this,
						arguments);
			},
			save:function(the){
				var me = this;
				var form = me.findByType('form')[0];
				form.getForm().submit({
					url:'App/servlet/QuestionTypeServlet.jss',
					params:{
						method:me.method,
						id:me.id
					},
					success:function(form,action){
						var result =action.result;
						Ext.Msg.alert("提示", result.message);
						 me.thisGrid.store.load();
						 me.close();
					}
				});
			}
		});