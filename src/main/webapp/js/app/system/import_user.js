Ext.ns('App.View');
App.View.ImportUserWindow = Ext.extend(Ext.Window, {
	title : '上传文件',
	width : 450,
	height : 120,
	modal : true,
	layout : 'form',
	autoScroll : true,
	constrain : true,
	bodyStyle : 'padding:10px 10px 10px 10px;',
	closable : true,
	draggable : false,
	resizable : false,
	buttonAlign:'center',
	initComponent : function() {
		var me = this;
		var form = new Ext.form.FormPanel({
			baseCls : 'x-plain',
			labelWidth : 70,
			labelHeight : 70,
			fileUpload : true,
			defaultType : 'textfield',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '选择文件',
				name : 'uploadfile',
				id : 'uploadfile',
				inputType : 'file',
				anchor : '95%' // anchor width by percentage
			} ]

		});
		me.items = form, 
		me.buttons = [
				{
					text : '确认导入',
					handler : function() {
						if (form.form.isValid()) {
							if (Ext.getCmp('uploadfile').getValue() == '') {
								Ext.Msg.alert('溫馨提示', '请选择要导入的文件');
								return;
							}
							Ext.MessageBox.show({
								title : '请稍等....',
								msg : '文件正在导入中....',
								progressText : '',
								width : 300,
								progress : true,
								closable : false,
								animEl : 'loding'
							});
							form.getForm().submit(
									{
										url : 'userupload',
//										url : 'objectupload',
										method : 'post',
										success : function(form, action) {
											var result = action.result;
											if(result.success){
												Ext.Msg.alert('提示', result.message);
												me.close();
//												me.parentComp.getLoader().load(me.parentComp.getRootNode(),function(treeNode){});
											}else{
												Ext.Msg.alert('提示', result.message);
											}
										},
										failure : function(form, action) {
											var result = action.result;
											Ext.Msg.alert('提示',result.message);
										}
									})
						}
					}
				}, {
					text : '取消',
					handler : function() {
						me.close();
					}
				} ]
		App.View.ImportUserWindow.superclass.initComponent.apply(
				this, arguments);
	}
});