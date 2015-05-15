package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.model.QuestionFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionFolderDao extends
		JpaRepository<QuestionFolder, String> {

	QuestionFolder findByNameAndParentAndBank(String name, QuestionFolder parent,
			QuestionBank bank);

}
