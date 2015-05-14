Ext.ns('Ext.App.servlet');
Ext.App.servlet.ExaminationPaper = function(req, res) {
	var fileName = '/App/exam.json';
	println(fileName);
	var exam = Ext.web.Util.readJson(fileName);
	Ext.web.Util.resTemp('/App/templates/ExaminationPaper.ftl',
			"application/xml; charset='utf-8'", {
				exam : exam
			});
};