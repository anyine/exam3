package org.flycloud.web.exam3.service;

import java.util.List;

import org.flycloud.web.exam3.model.Question;
import org.flycloud.web.exam3.model.QuestionBank;

public interface QuestionService {

	List<Question> queryAllQuestions();

	QuestionBank queryQuestionBankByName(String name);

}
