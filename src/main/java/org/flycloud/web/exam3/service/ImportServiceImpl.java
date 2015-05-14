package org.flycloud.web.exam3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.flycloud.web.exam3.dao.QuestionDao;
import org.flycloud.web.exam3.dao.QuestionFolderDao;
import org.flycloud.web.exam3.model.Question;
import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.model.QuestionFolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImportServiceImpl implements ImportService {

	@Inject
	private QuestionFolderDao questionFolderDao;

	@Inject
	private QuestionDao questionDao;

	@Override
	public void importUser(List<List<Object>> llo) throws Exception {

	}
	
	private QuestionBank getQuestionBank(String f) {
		return null;
	}
	
	private QuestionFolder getQuestionFolder(String f) {
		return null;
	}
	
	public void importQuestion(MultipartFile file) throws Exception {
		long c = questionFolderDao.count();
		if (c == 0) {
			QuestionFolder pct = new QuestionFolder();
			pct.setName("默认题库");
			questionFolderDao.save(pct);
		}
		try {
			Workbook book = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = book.getSheet(0);
			int column = sheet.getColumns();
			int row = sheet.getRows();
			List<Question> questions = new ArrayList<Question>();
			for (int i = 1; i < row; i++) {
				Question q = new Question();
				q.setId(UUID.randomUUID().toString());
				for (int j = 1; j < column; j++) {
					Cell cell1 = sheet.getCell(j, i);
					String result = cell1.getContents();
					switch (j) {
//					case 1:
//						q.set
//					case 2:
//						q.setFolder(this.getQuestionFolder(result));
//						break;
//					case 3:
//						q.setType(result);
//						createType(result);
//						break;
//					case 4:
//						q.setContent(result);
//						break;
//					case 5:
//						result = transCheckString(result);
//						q.setAnContent(result);
//						break;
//					case 6:
//						result = transCheckString(result);
//						q.setAnswer(result);
//						break;
//					case 7:
//						q.setDifficult();
//						break;
//					case 8:
//						q.setRemark(result);
//						break;
					}
				}
				questions.add(q);
			}
			book.close();

			for (Question q : questions) {
				questionDao.save(q);
			}
		} catch (Exception e) {
		}
	
	}
}
