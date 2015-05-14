Ext.ns('App.view.plantable');
App.view.plantable.PointEditWindow = Ext.extend(Ext.Window, {
	modal : true,
	initComponent : function() {
		var me = this;
		var st = new Ext.data.Store({
					autoLoad : true,
					proxy : new Ext.data.HttpProxy({
								url : "App/servlet/QuestionTypeServlet.jss?method=get",
								method : "get"
							}),
					reader : new Ext.data.JsonReader({
								root : 'root',
								fields : ['id', 'name']
							})
				});
		me.items = {
			xtype : 'form',
			bodyStyle : 'padding: 20px; background-color: #DFE8F6',
			defaults : {
				labelWidth : 60
			},
			items : [{
						xtype : 'combo',
						name : 'name',
						store : st,
						editable : false,
						valueField : 'id',
						mode : 'local',
						triggerAction : 'all',
						displayField : 'name',
						fieldLabel : '题型',
						width : 120,
						allowBlank : false
					}, {
						xtype : 'numberfield',
						fieldLabel : '分值 ',
						name : 'value',
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
		App.view.plantable.PointEditWindow.superclass.initComponent.apply(this,
				arguments);
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