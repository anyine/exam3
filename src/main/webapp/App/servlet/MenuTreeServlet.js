Ext.ns("Ext.App.servlet");
Ext.App.servlet.MenuTreeServlet = function(req, res) {
	obj = [
            { text: '菜单',
                children: [
                { text: '分类维护',leaf: true,file:'App.view.type.TypePanel'},
                { text: '题型维护',leaf: true,file:'App.view.qType.QuestionTypePanel'},
                { text: '试题维护',leaf: true,file:'App.view.question.QuestionPanel'},
                { text: '方案维护',leaf: true,file:'App.view.plan.PlanPanel'},
                { text: '密码修改',leaf: true,file:'App.view.password.PasswordPanel'},
                { text: '现场摇号',leaf: true,file:'App.view.system.RandomWindow'}
            ]}
        ];
	Ext.web.Util.resJson(obj);
};