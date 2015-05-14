Ext.ns("App.view.plan");
App.view.plan.WaitWindow = Ext.extend(Ext.Window, {
	title : '处理中......',
	modal : true,
	width : 300,
	initComponent : function() {
		var me = this;

		var bar = new Ext.ProgressBar({
			width : me.width
		});

		me.items = [ bar ];
		bar.wait({
			interval : 1000,
			duration : 6000000000000,
			increment : 15
		});

		App.view.plan.WaitWindow.superclass.initComponent
				.apply(this, arguments);
	}
});
