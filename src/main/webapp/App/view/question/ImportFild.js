Ext.ns('App.view.question');
App.view.question.ImportFild = Ext.extend(Ext.Window, {
			title : '导入',
			buttonAlign : 'center',
			initComponent : function() {
				var me = this;
				me.items = {
					xtype : 'form',
					fileUpload: true, 
					bodyStyle : 'padding: 20px; background-color: #DFE8F6',
					defaults : {
						labelWidth : 40,
						allowBlank : false
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel:'文件名称',
								name : 'url',
								inputType:'file',
								validator:function(a){
									if(a.indexOf(".xls")==-1)
										return false
									else
										return true;
								}
							}]
				}
				
				me.buttons = [{
							text : '上传',
							handler : me.save,
							scope : me
						},{
					text:'取消',
					handler:function(){
						me.close();
					}
				}]
				
				App.view.question.ImportFild.superclass.initComponent.apply(this,
						arguments);
			},
			save:function(){
				var me = this;
				me.findByType("form")[0].getForm().submit({
					url:'App/servlet/QuestionServlet.jss',
					params:{
						method:"import"
					},
					success:function(form,action){
						console.log(action);
						var result =action.result;
						Ext.Msg.alert("提示", result.message);
						 me.thisGrid.store.load();
						 me.close();
						 me.store.load();
					}
				});
			}
		});
