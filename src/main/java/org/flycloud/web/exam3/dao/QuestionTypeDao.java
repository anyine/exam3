package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.model.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeDao extends JpaRepository<QuestionType, String> {

	QuestionType findByBankAndName(QuestionBank bank, String name);

}
