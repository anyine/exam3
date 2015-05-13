package org.flycloud.web.exam3.controller;

import javax.inject.Inject;

import org.flycloud.web.exam3.service.PlatformService;
import org.flycloud.web.exception.PasswordErrorException;
import org.flycloud.web.exception.UserNotExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunp@sdhuijin.cn:
 * @version 创建时间：2015年5月7日 上午11:24:43 类说明
 */

@Controller
public class UserController {

	@Inject
	private PlatformService platformService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("name") String name,
			@RequestParam("pass") String pass) {
		try {
			platformService.login(name, pass);
			return new ModelAndView("index");
		} catch (UserNotExistsException e) {
			return new ModelAndView("login");
		} catch (PasswordErrorException e) {
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = { "/", "/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
		return new ModelAndView("login");
	}
}
