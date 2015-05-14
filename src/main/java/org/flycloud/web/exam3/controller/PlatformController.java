package org.flycloud.web.exam3.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.flycloud.web.exam3.service.PlatformService;
import org.flycloud.web.exception.PasswordErrorException;
import org.flycloud.web.exception.UserNotExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlatformController {

	@Inject
	private PlatformService platformService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("name") String name,
			@RequestParam("pass") String pass) {
		Map<String, Object> result = new HashMap<String,Object>();
		try {
			platformService.login(name, pass);
			result.put("success", "true");
		} catch (UserNotExistsException e) {
			result.put("message", "用户名不存在，请检查登录名称是否正确！");
		} catch (PasswordErrorException e) {
			result.put("message", "用户密码错误，请检查登录密码是否正确！");
		}
		return result;
	}

	@RequestMapping(value = { "/", "/logout" }, method = RequestMethod.GET)
	public String init() {
		platformService.init("15169021589");
		return "index";
	}

	@RequestMapping(value = { "/tree" }, method = RequestMethod.POST)
	@ResponseBody
	public String tree() {
		return "["+
            "{ text: '菜单',"+
            "    children: ["+
            "    { text: '分类维护',leaf: true,file:'App.view.type.TypePanel'},"+
            "    { text: '题型维护',leaf: true,file:'App.view.qType.QuestionTypePanel'},"+
            "    { text: '试题维护',leaf: true,file:'App.view.question.QuestionPanel'},"+
            "    { text: '方案维护',leaf: true,file:'App.view.plan.PlanPanel'},"+
            "    { text: '密码修改',leaf: true,file:'App.view.password.PasswordPanel'},"+
            "    { text: '现场摇号',leaf: true,file:'App.view.system.RandomWindow'}"+
            "]}"+
        "]";
	}
}
