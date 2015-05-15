package org.flycloud.web.exam3.service;

import java.util.List;

import javax.inject.Inject;

import org.flycloud.web.exam3.dao.QuestionDao;
import org.flycloud.web.exam3.model.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Inject
	private QuestionDao questionDao;
	
	@Override
	public List<Question> queryAll() {
		return questionDao.findAll();
	}

}
