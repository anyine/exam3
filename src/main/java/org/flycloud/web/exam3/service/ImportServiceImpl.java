package org.flycloud.web.exam3.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import jxl.Sheet;
import jxl.Workbook;

import org.flycloud.web.exam3.dao.QuestionBankDao;
import org.flycloud.web.exam3.dao.QuestionDao;
import org.flycloud.web.exam3.dao.QuestionFolderDao;
import org.flycloud.web.exam3.dao.QuestionTypeDao;
import org.flycloud.web.exam3.dao.ResourceDao;
import org.flycloud.web.exam3.model.Question;
import org.flycloud.web.exam3.model.QuestionBank;
import org.flycloud.web.exam3.model.QuestionFolder;
import org.flycloud.web.exam3.model.QuestionLevel;
import org.flycloud.web.exam3.model.QuestionType;
import org.flycloud.web.exam3.model.Resource;
import org.flycloud.web.exam3.model.ResourceType;
import org.flycloud.web.exam3.model.format.QuestionFormatFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImportServiceImpl implements ImportService {

	@Inject
	private QuestionFormatFactory formatFactory;
	
	@Inject
	private QuestionFolderDao questionFolderDao;

	@Inject
	private QuestionDao questionDao;

	@Inject
	private QuestionBankDao questionBankDao;

	@Inject
	private QuestionTypeDao questionTypeDao;

	@Inject
	private ResourceDao resourceDao;

	@Override
	public void importUser(List<List<Object>> llo) throws Exception {

	}

	@Override
	public void importQuestion(MultipartFile file) throws Exception {
		try {
			Workbook book = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = book.getSheet(0);
			for (int row = 1; row < sheet.getRows(); row++) {
				Question q = new Question();
				q.setId(UUID.randomUUID().toString());
				QuestionBank bank = getQuestionBank(sheet.getCell(1, row)
						.getContents());
				q.setFolder(getQuestionFolder(bank, sheet.getCell(2, row)
						.getContents()));// 章节
				q.setType(getQuestionType(bank, sheet.getCell(3, row)
						.getContents(), sheet.getCell(4, row).getContents()));// 题型

				q.setLevel(QuestionLevel.getByName(sheet.getCell(5, row).getContents()));// 难易度
				
				List<Resource> resources = new ArrayList<Resource>();
				resources.add(getResource(ResourceType.Question, "题干",
						"text/plain", sheet.getCell(6, row).getContents()));
				resources.add(getResource(ResourceType.Question, "选项",
						"text/plain", sheet.getCell(7, row).getContents()));
				resources.add(getResource(ResourceType.Answer, "答案",
						"text/plain", sheet.getCell(8, row).getContents()));
				q.setResources(resources);

				questionDao.save(q);
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Resource getResource(ResourceType type, String name,
			String mimeType, String contents) {
		Resource r = new Resource();
		r.setCharset("UTF-8");
		try {
			r.setContent(contents.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		r.setId(UUID.randomUUID().toString());
		r.setMimeType(mimeType);
		r.setName(name);
		r.setType(type);
		r = resourceDao.save(r);
		return r;
	}

	private QuestionType getQuestionType(QuestionBank bank, String type,
			String format) {
		QuestionType t = questionTypeDao.findByBankAndName(bank, type);
		if (t == null) {
			t = new QuestionType();
			t.setId(UUID.randomUUID().toString());
			t.setBank(bank);
			t.setFormat(format);
			t.setName(type);
			t.setSubjective(formatFactory.fromName(format).autoRating());
			t = questionTypeDao.save(t);
		}
		return t;
	}

	private QuestionBank getQuestionBank(String name) {
		QuestionBank bank = questionBankDao.findByName(name);
		if (bank == null) {
			bank = new QuestionBank();
			bank.setId(UUID.randomUUID().toString());
			bank.setName(name);
			questionBankDao.save(bank);
		}
		return bank;
	}

	public static void main(String[] args) {
		String name = "fkjsad/fsdfjls/fsdfsd";
		if (name.matches("^.+\\/[^\\/]*$")) {
			System.out.println(name.replaceFirst("/[^\\/]+$", ""));
		}
	}

	private QuestionFolder getQuestionFolder(QuestionBank bank, String name) {
		QuestionFolder p = null;
		if (name.matches("^.+\\/[^\\/]*$")) {
			String parent = name.replaceFirst("/[^\\/]+$", "");
			p = getQuestionFolder(bank, parent);
			String[] s = name.split("\\/");
			name = s[s.length - 1];
		}
		QuestionFolder f = questionFolderDao.findByNameAndParentAndBank(name, p, bank);

		if (f == null) {
			f = new QuestionFolder();
			f.setId(UUID.randomUUID().toString());
			f.setBank(bank);
			f.setParent(p);
			f.setName(name);
			f.setSerial(questionFolderDao.count() + 1);
			f = questionFolderDao.save(f);
		}
		return f;
	}

	private Double getDifficult(String name) {
		return Double.valueOf(name);
	}
}
