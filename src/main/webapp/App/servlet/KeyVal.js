Ext.ns("Ext.App.servlet");
Ext.App.servlet.KeyVal = function(req, res) {
	if (engine.getKeyReady()) {
		var obj = {
			success : true
		};
	} else {
		var obj = {
			success : false
		};
	}
	Ext.web.Util.resJson(obj);
};