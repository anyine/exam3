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

@Controller
@RequestMapping(value = { "/import" })
public class ImportController {

	@Inject
	private PlatformService platformService;

	@RequestMapping(value = { "/excel" }, method = RequestMethod.POST)
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
}
