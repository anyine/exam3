Ext.ns('App.view.plantable');
App.view.plantable.EditTablePanel = Ext.extend(Ext.Window, {
			table : '约束编辑',
			layout : 'fit',
			width : 520,
			height : 400,
			initComponent : function() {
				var me = this;
				var tab1 = new App.view.plantable.ClassTypeEditPanel({
							thisId : me.thisId
						});
				var tab2 = new App.view.plantable.QuestionTypeEditPanel({
							thisId : me.thisId
						});
				var tab3 = new App.view.plantable.MixEditPanel({
							thisId : me.thisId
						});
				var tab4 = new App.view.plantable.HardEditPanel({
							thisId : me.thisId
						});
				var tab5 = new App.view.plantable.PointEditPanel({
							thisId : me.thisId
						});
				me.items = [{
							xtype : 'tabpanel',
							activeTab : '0',
							items : [tab1, tab2, tab3, tab4, tab5]
						}]
				App.view.plantable.EditTablePanel.superclass.initComponent
						.apply(this, arguments);
			}
		});