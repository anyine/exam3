package org.flycloud.web.exam3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flycloud.web.exam3.model.Question;
import org.flycloud.web.exam3.view.ExaminePdfView;
import org.flycloud.web.exam3.view.QuestionExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/export" })
public class ExportController {

	
	@RequestMapping(value = "/pdf/examine/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView examine(@PathVariable String id) {
		ExaminePdfView view = new ExaminePdfView();
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(view, model);
	}

	@RequestMapping(value = "/excel/questionfolder/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView questionfolder(@PathVariable String id) {
		QuestionExcelView view = new QuestionExcelView();
		Map<String, Object> model = new HashMap<String, Object>();
		List<Question> list = new ArrayList<Question>();
		model.put("root", list);
		return new ModelAndView(view, model);
	}

}
