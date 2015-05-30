package org.flycloud.web.exam3.service;

import java.util.List;

import javax.inject.Inject;

import org.flycloud.web.exam3.dao.QuestionBankDao;
import org.flycloud.web.exam3.dao.QuestionDao;
import org.flycloud.web.exam3.model.Question;
import org.flycloud.web.exam3.model.QuestionBank;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Inject
	private QuestionDao questionDao;
	@Inject
	private QuestionBankDao questionBankDao;
	
	@Override
	public List<Question> queryAllQuestions() {
		return questionDao.findAll();
	}

	@Override
	public QuestionBank queryQuestionBankByName(String name) {
		QuestionBank bank = questionBankDao.findByName(name);
		return bank;
	}

}
