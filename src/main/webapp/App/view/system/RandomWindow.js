Ext.ns('App.view.system');
App.view.system.RandomWindow = Ext.extend(Ext.Window, {
			width : 500,
			height : 500,
			buttonAlign : 'center',
			layout : 'center',
			num : ['1', '2', '4', '121', '118', '105', '98', '33', '4', '0',
					'32', '1', '9', '67', '23', '34', '76', '45', '50', '93',
					'26', '21', '74', '27', '62', '29', '119', '102'],
			initComponent : function() {
				var me = this;
				me.items = [{
							bodyStyle : 'padding: 20px; background-color: #DFE8F6',
							region : 'center',
							html : ''
						}, {
							bodyStyle : 'padding: 20px; background-color: #DFE8F6',
							region : 'north',
							html : '',
							height : 70
						}]
				me.buttons = [{
							text : '开始',
							handler : me.start,
							scope : me
						}, {
							text : '结束',
							handler : me.stop,
							scope : me
						}];
				me.on('beforeclose', function() {
							if (me.work != null) {
								window.clearInterval(me.work);
							}
						});
				App.view.system.RandomWindow.superclass.initComponent.apply(
						this, arguments);
			},
			start : function() {
				var me = this;
				var count = 0;
				me.work = window.setInterval(function() {
							if (count < me.num.length)
								me.updateNorth(me.num[count++], me);
							else
								count = 0;
						}, 20);
			},
			stop : function() {
				var me = this;
				if (me.work != null) {
					window.clearInterval(me.work);
				}
				me.updateCenter(Math.round(121 * Math.random()) + "", me);
			},
			updateNorth : function(str, me) {
				var panel = me.findByType('panel')[1];
				console.log(panel);
				var htmlStart = "<center><font size='6' style='font-family:verdana'>";
				var htmlEnd = "</font></center>";
				panel.body.update(htmlStart + str + htmlEnd);
			},
			updateCenter : function(str, me) {
				var panel = me.findByType('panel')[0];
				panel.body.update(str);
			}
		});