Ext.ns("App.view.plan");
App.view.plan.PlanShowWindow = Ext.extend(Ext.Window, {
			title : '方案内容',
			layout : 'fit',
			width : 600,
			height : 250,
			modal : true,
			initComponent : function() {
				var me = this;
				var panel = new App.view.plan.PlanShowPanel({thisId:me.id});
				me.items = [panel]
				App.view.plan.PlanShowWindow.superclass.initComponent.apply(
						this, arguments);
			}
		});