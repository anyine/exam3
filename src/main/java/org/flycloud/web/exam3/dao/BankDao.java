package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDao extends JpaRepository<QuestionBank, String> {

}
