Ext.ns('App.view.type');
App.view.type.TypePanel = Ext.extend(Ext.tree.TreePanel, {
			title : '分类维护',
			closable : true,
			iconCls : 'user',
			closeAction : "hide",
			autoScroll : true,
			containerScroll : true,
			initComponent : function() {
				var me = this;
				var root = new Ext.tree.AsyncTreeNode({
							id : 'root',
							expanded : true
						});
				me.loader = new Ext.tree.TreeLoader({
							dataUrl : 'App/servlet/TypeServlet.jss',
							baseParams : {
								method : 'get'
							}
						});
				me.tbar = [{
							xtype : 'button',
							text : "新增",
							handler : me.add,
							iconCls : 'create',
							scope : me
						}, '-', {
							xtype : 'button',
							text : "新增下级",
							handler : me.addChild,
							iconCls : 'create',
							scope : me
						}, '-', {
							xtype : 'button',
							text : '修改',
							handler : me.modify,
							iconCls : 'modify',
							scope : me
						}, '-', {
							xtype : 'button',
							text : '删除',
							handler : me.remove,
							iconCls : 'delete',
							scope : me
						}];
				me.on('dblclick', me.modify, me);
				me.root = root;
				me.rootVisible = false;
				App.view.system.MenuTree.superclass.initComponent.apply(this,
						arguments);
			},
			add : function() {
				var win = new App.view.type.EditWindow({
							method : 'create',
							parentId : 'root',
							thisTree : this
						});
				win.show();
			},
			addChild : function() {
				var me = this;
				var node = this.getSelectionModel().getSelectedNode();
				if (node == null) {
					Ext.Msg.alert('提示', '请选择一项');
				} else {
					var win = new App.view.type.EditWindow({
								method : 'create',
								parentId : node.attributes.id,
								thisTree : this
							});
					win.show();
				}
			},
			modify : function() {
				var me = this;
				var node = this.getSelectionModel().getSelectedNode();
				if (node == null) {
					Ext.Msg.alert('提示', '请选择一项');
				} else {
					var win = new App.view.type.EditWindow({
								method : 'modify',
								parentId : node.attributes.id,
								thisTree : this
							});
					win.findByType('textfield')[0]
							.setValue(node.attributes.text);
					win.findByType('textfield')[1]
							.setValue(node.attributes.remark);
					win.show();
				}
			},
			remove : function() {
				var me = this;
				var node = this.getSelectionModel().getSelectedNode();
				if (node == null) {
					Ext.Msg.alert('提示', '请选择一项');
				} else {
					Ext.Ajax.request({
								url : 'App/servlet/TypeServlet.jss',
								params : {
									method : 'delete',
									id : node.attributes.id
								},
								success : function(result) {
									var obj = Ext.decode(result.responseText);
									me.root.reload();
									Ext.Msg.alert("提示", obj.message);
								}
							});
				}
			}
		});