Ext.ns('App.view.type');
App.view.type.TypePanelWindow = Ext.extend(Ext.Window, {
			title : '分类选择',
			height : 500,
			width : 400,
			modal : true,
			buttonAlign:'center',
			layout : 'fit',
			initComponent : function() {
				var me = this;
				var treePanel = new App.view.type.TypePanel({
							title : null
						});
				me.items = [treePanel];
				
				me.buttons = [{
					text:'确定',
					handler:me.save,
					scope:me
				},{
					text:'取消',
					handler:function(){
						me.close();
					}
				}]
				App.view.type.TypePanelWindow.superclass.initComponent.apply(
						this, arguments);
			},
			save:function(the){
				var me = this;
				var node = this.items.items[0].getSelectionModel().getSelectedNode();
				if(node!=null){
					me.nodeId = node.attributes.id;
					me.nodeName = node.attributes.text;
					me.close();
				}else{
					Ext.Msg.alert("提示","请选择一项");
				}
			}
		});