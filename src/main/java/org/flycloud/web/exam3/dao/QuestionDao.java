package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDao extends JpaRepository<Question, String> {

}
