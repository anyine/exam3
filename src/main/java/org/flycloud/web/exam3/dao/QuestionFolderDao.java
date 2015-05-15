package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.QuestionFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionFolderDao extends JpaRepository<QuestionFolder, String> {

	QuestionFolder findByNameAndParent(String name, QuestionFolder p);

}
