Ext.ns("Ext.App.servlet");
Ext.App.servlet.FileUpload = function(req, res) {

	if (req.getPart("file").isFile()) {
		println(req.getPart("file").fileName);
		req.getPart("file").writeTo("c://a.ocf");
	}

	if (!req.getPart("submit").isFile()) {
		println(req.getPart("submit").value);
	}
};