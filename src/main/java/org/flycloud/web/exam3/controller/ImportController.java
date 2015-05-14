package org.flycloud.web.exam3.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.flycloud.util.ReadExcel;
import org.flycloud.web.exam3.service.PlatformService;
import org.flycloud.web.exception.PasswordErrorException;
import org.flycloud.web.exception.UserNotExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/import" })
public class ImportController {

//	@Inject
//	private PlatformService platformService;

	@RequestMapping(value = "/upload")
	public String excel(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			List<List<Object>> list = ReadExcel.readExcel(file, 0, 4);
			for (List<Object> l : list) {
				for (Object o : l) {
					System.out.println(o.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "result";
	}

}
