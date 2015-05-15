package org.flycloud.web.exam3.controller;

import javax.inject.Inject;

import org.flycloud.web.exam3.service.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = { "/import" })
public class ImportController {

	@Inject
	private ImportService importService;

	@RequestMapping(value = "/excel/question", method = RequestMethod.POST)
	public String excel(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			importService.importQuestion(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

}
