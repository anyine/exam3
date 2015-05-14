Ext.ns("App.view.plan");
App.view.plan.TypeEditWindow = Ext.extend(Ext.Window, {
			layout : 'fit',
			width : 600,
			height : 400,
			modal : true,
			initComponent : function() {
				var me = this;
				me.setTitle(me.model.get('type'));

				var panel = new App.view.plan.TypeEditPanel({
							thisId : me.thisId,
							model : me.model,
							showStore : me.showStore
						});
				me.items = [panel];
				App.view.plan.TypeEditWindow.superclass.initComponent.apply(
						this, arguments);
			}
		});