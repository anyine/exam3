package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBankDao extends JpaRepository<QuestionBank, String> {

}
