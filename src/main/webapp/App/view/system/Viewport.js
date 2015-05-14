Ext.ns("App.view.system");
App.view.system.Panel = Ext.extend(Ext.Viewport, {
	style : 'margin:2px',
	layout : 'border',
	initComponent : function() {
		var me = this;
		var menuTree = new App.view.system.MenuTree();
		var tabPanel = new App.view.system.TablePanel();
		var chObj = {};
		var ch = '';
		this.items = [{
			region : 'north',
			height : 100,
			html : '<img src="App/resource/image/t.jpg" style="height:100%;width:100%"></img>'
		}, {
			region : 'west',
			width : 300,
			layout : 'fit',
			collapsible : true,
			split : true,
			items : [menuTree]
		}, {
			region : 'center',
			layout : 'fit',
			items : [tabPanel]
		}]
		menuTree.on('click', function(node, e) {
					var attr = node.attributes;
					var fileUrl = attr.file;
					if (fileUrl == "App.view.password.PasswordPanel") {
						var win = new App.view.password.PasswordPanel();
						win.show();
					} else if (fileUrl == "App.view.system.RandomWindow") {
						var win = new App.view.system.RandomWindow();
						win.show();
					} else {
						var tabItem = eval("new " + fileUrl + "();");

						if (tabPanel.getComponent(chObj[tabItem.title]) == null) {
							tabPanel.add(tabItem);
							tabPanel.setActiveTab(tabItem);
							chObj[tabItem.title] = tabItem.id;
						} else {
							tabPanel.setActiveTab(chObj[tabItem.title]);
						}
					}
				});
		App.view.system.Panel.superclass.initComponent.apply(this, arguments);
	}
});
