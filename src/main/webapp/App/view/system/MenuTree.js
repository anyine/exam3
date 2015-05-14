Ext.ns('App.view.system');
App.view.system.MenuTree = Ext.extend(Ext.tree.TreePanel, {
	title : '导航栏',
	initComponent : function() {
		var me = this;
		
		var root = new Ext.tree.AsyncTreeNode({
					id : 'root',
					expand:true,
					expanded : true
				});
		me.loader = new Ext.tree.TreeLoader({
			dataUrl : 'App/servlet/MenuTreeServlet.jss',
			baseParams : {}
		});
		me.root = root;
		me.rootVisible = false;
		App.view.system.MenuTree.superclass.initComponent.apply(this, arguments);
	}
});