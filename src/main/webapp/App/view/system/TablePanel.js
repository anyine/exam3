Ext.ns("App.view.system");
App.view.system.TablePanel = Ext
		.extend(
				Ext.TabPanel,
				{
					activeTab : 0,
					initComponent : function() {
						var me = this;
						me.items = [ {
							xtype : 'panel',
							title : '首页',
							html : '<img src="App/resource/image/r.jpg" style="height:100%;width:100%"></img>'
						} ]

						App.view.system.TablePanel.superclass.initComponent
								.apply(this, arguments);
					}
				});