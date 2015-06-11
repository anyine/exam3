package org.flycloud.web.exam3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.flycloud.util.Exporter;
import org.flycloud.web.exam3.model.Question;
import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.service.QuestionService;
import org.flycloud.web.exam3.view.ExamineModelExcelView;
import org.flycloud.web.exam3.view.ExaminePdfView;
import org.flycloud.web.exam3.view.QuestionBankExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/export" })
public class ExportController {

	@Inject
	private QuestionService questionService;

	@Inject
	private Exporter exporter;

	// 导出数据库（SQL格式）
	@RequestMapping(value = "/sql", method = RequestMethod.GET)
	public String sqldump() {
		exporter.export();
		return "index";
	}
	
	// 导出试题（PDF格式）
	@RequestMapping(value = "/pdf/examine/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView examine(@PathVariable String id) {
		ExaminePdfView view = new ExaminePdfView();
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(view, model);
	}

	// 导出某个题库（excel格式）
	@RequestMapping(value = "/excel/questionbank/{id}", method = RequestMethod.GET)
	public ModelAndView questionBank(@PathVariable String id) {
		QuestionBankExcelView view = new QuestionBankExcelView();
		Map<String, Object> model = new HashMap<String, Object>();
		List<Question> list = questionService.queryAllQuestions();
		model.put("root", list);
		return new ModelAndView(view, model);
	}

	// 导出所有题库（excel格式）
	@RequestMapping(value = "/excel/questions", method = RequestMethod.GET)
	public ModelAndView questions() {
		QuestionBankExcelView view = new QuestionBankExcelView();
		Map<String, Object> model = new HashMap<String, Object>();
		List<Question> list = questionService.queryAllQuestions();
		model.put("root", list);
		return new ModelAndView(view, model);
	}

	// 导出所有题库（text格式）
	@RequestMapping(value = "/text/questions", method = RequestMethod.GET)
	public ModelAndView texts() {
		QuestionBankExcelView view = new QuestionBankExcelView();
		Map<String, Object> model = new HashMap<String, Object>();
		List<Question> list = questionService.queryAllQuestions();
		model.put("root", list);
		return new ModelAndView(view, model);
	}

	// 根据题库名称，导出某个题库的抽题模板（excel格式）
	@RequestMapping(value = "/excel/examine/{name}", method = RequestMethod.GET)
	public ModelAndView examinemodel(@PathVariable String name) {
		ExamineModelExcelView view = new ExamineModelExcelView();
		Map<String, Object> model = new HashMap<String, Object>();
		QuestionBank bank = questionService.queryQuestionBankByName(name);
		model.put("root", bank);
		return new ModelAndView(view, model);
	}

}
