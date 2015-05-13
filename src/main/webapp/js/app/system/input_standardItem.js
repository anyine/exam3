/**
 * 录入评分项
 * 
 * author sunping
 */

Ext.ns('App.View');
App.View.InputStandardItem = Ext.extend(Ext.Window, {
	title : '录入评分项',
	iconCls : 'icon_create',
	width : 800,
	buttonAlign : 'center',
	modal : true,
	datas : [],
	initComponent : function() {
		var me = this;

		var cum = new Ext.grid.ColumnModel([ {
			header : '对象',
			dataIndex : 'targetId',
			width : 10,
			editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
				allowBlank : false,
				sortable : false,
			})),
		}, {
			header : '考核',
			dataIndex : 'standardId',
			width : 10,
			editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
				allowBlank : false,
				sortable : false,
			}))
		}, {
			header : '考核项',
			dataIndex : 'standardItem',
			width : 10,
			editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
				allowBlank : false,
				sortable : false,
			}))
		}, {
			header : '分数',
			dataIndex : 'score',
			width : 10,
			editor : new Ext.grid.GridEditor(new Ext.form.NumberField({
				allowBlank : false,
				sortable : false,
			}))
		}, {
			header : '描述',
			dataIndex : 'description',
			width : 10,
			editor : new Ext.grid.GridEditor(new Ext.form.TextField({
				allowBlank : true,
			}))
		} ]);

		var cumdata = [];

		var store = new Ext.data.Store({
			proxy : new Ext.data.MemoryProxy(cumdata),
			reader : new Ext.data.ArrayReader({}, [ {
				name : 'targetId'
			}, {
				name : 'standardId'
			}, {
				name : 'standardItem'
			}, {
				name : 'score'
			}, {
				name : 'description'
			} ])
		});

		var p = new Ext.data.Record.create([ {
			targetId : '',
			standardId : '',
			standardItem : '',
			score : '',
			description : ''
		} ]);

		store.load();

		var cumgrid = new Ext.grid.EditorGridPanel({
			id : 'cumgrid',
			layout : 'fit',
			anchor : '100%',
			bodyStyle : 'border:1px ;border-color: #99BBE8;',
			frame : true,
			store : store,
			stripeRows : true,
			viewConfig : {
				forceFit : true,
				scrollOffset : 10,
				sortAscText : '升序',
				sortDescText : '降序'
			},
			height : 200,
			colModel : cum,
			tbar : new Ext.Toolbar([ {
				text : '添加',
				handler : function() {
					cumgrid.stopEditing();
					store.insert(0, new p({
						targetId : '',
						standardId : '',
						standardItem : '',
						score : '',
						description : ''
					}));
					cumgrid.startEditing(0, 0); // 激活该行的编辑状态
				}
			}, '-', {
				text : '删除',
				handler : function() {
					Ext.Msg.confirm('信息', '确定要删除', function(btn) {
						if (btn == 'yes') {
							var sm = cumgrid.getSelectionModel(); // 得到表格的选择模型
							var cell = sm.getSelectedCell(); // 通过选择模型得到选择的单元格
							var record = store.getAt(cell[0]); // 得到store对应的Record
							store.remove(record);
						}
					})
				}
			} ])
		});

		me.items = [ {
			xtype : 'form',
			frame : true,
			bodyBorder : true,
			bodyStyle : 'background-color: #DFE8F6; border-color: #DFE8F6;',
			items : [ cumgrid, {
				xtype : 'hidden',
				id : 'items'
			} ]
		} ];

		me.buttons = [ {
			xtype : 'button',
			text : '保存',
			handler : me.save,
			hidden : me.hidden,
			scope : me
		}, {
			xtype : 'button',
			text : '取消',
			handler : function(button) {
				me.close();
			}
		} ];

		App.View.InputStandardItem.superclass.initComponent.apply(this,
				arguments);
	},

	convertToJson : function(win) {
		var vRecords = Ext.getCmp('cumgrid').store.data.items;
		var vCount = vRecords.length;
		if (vCount > 0) {
			var vDatas = '[';
			for (var i = 0; i < vCount; i++) {
				vDatas += Ext.util.JSON.encode(vRecords[i].data) + ',';
			}
			vDatas = vDatas.substr(0, vDatas.length - 1) + ']';
		}
		Ext.getCmp("items").setValue(vDatas);
	},

	save : function(button) {
		var win = button.findParentByType();
		win.convertToJson(win);

		var form = win.findByType('form')[0];

		if (form.getForm().isValid()) {
			button.setDisabled(true);
			form.getForm().submit({
				url : 'deductionsave',
				method : 'POST',
				timeout : 300,
				success : function(form, action) {
					var result = action.result;
					if (result.success) {
						Ext.Msg.alert("提示", "操作成功");
					} else {
						Ext.Msg.alert("提示", result.message);
						button.setDisabled(false);
					}
					win.close();
				},
				failure : function(form, action) {
					// console.log(action);
					var result = action.result;
					if (result) {
						Ext.Msg.alert("提示", result.message);
						button.setDisabled(false);
					} else {
						Ext.Msg.alert("提示", "请求失败");
						button.setDisabled(false);
					}
				}
			})
		} else {
			Ext.Msg.alert("提示", '请完善登录信息！');
		}
	}
});
