package org.flycloud.web.exam3.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import jxl.Sheet;
import jxl.Workbook;

import org.flycloud.util.ExcelReader;
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
import org.flycloud.web.exam3.model.format.IQuestionFormat;
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
			Sheet sheet1 = book.getSheet(0);
			ExcelReader reader = new ExcelReader(sheet1);

			for (int row = 1; row < reader.getRows() - 1; row++) {
				Map<String, String> map = reader.getRow(row);
				this.createQuestion(map);
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

	private QuestionFolder getQuestionFolder(QuestionBank bank, String name) {
		QuestionFolder p = null;
		if (name.matches("^.+\\/[^\\/]*$")) {
			String parent = name.replaceFirst("/[^\\/]+$", "");
			p = getQuestionFolder(bank, parent);
			String[] s = name.split("\\/");
			name = s[s.length - 1];
		}
		QuestionFolder f = questionFolderDao.findByNameAndParentAndBank(name,
				p, bank);

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

	@Override
	public void importTextQuestion(MultipartFile file) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				file.getInputStream(), "UTF-8"));
		String line;
		Map<String, String> map = new HashMap<String, String>();
		String value = "";
		String key = "";
		while ((line = reader.readLine()) != null) {
			if (line.equals("")) {
				continue;
			}
			if (line.matches("^(题库|章节|题型|格式|难易|题干|答案|[A-Za-z])[）．：,、 .，].*")) {
				if(!key.equals("")) {
					map.put(key, value);
				}
				key = line.replaceAll("[）．：,、 .，].*$", "");
				value = line.replaceAll(
						"^(题库|章节|题型|格式|题干|难易|答案|[A-Za-z])[）．：,、 .，]", "");
				if (key.equals("题干") && map.containsKey("题干")) {
					map = this.createQuestion(map);
				}
			} else {
				value += "\r\n" + line;
			}
		}
		map.put(key, value);
		map = this.createQuestion(map);

		reader.close();

	}

	private Map<String, String> createQuestion(Map<String, String> map)
			throws Exception {
		Map<String, String> dirmap = new HashMap<String, String>();
		dirmap.put("序号", map.remove("序号"));
		dirmap.put("题库", map.remove("题库"));
		dirmap.put("章节", map.remove("章节"));
		dirmap.put("题型", map.remove("题型"));
		dirmap.put("格式", map.remove("格式"));
		dirmap.put("难易", map.remove("难易"));

		Question q = new Question();
		q.setId(UUID.randomUUID().toString());
		QuestionBank bank = getQuestionBank(dirmap.get("题库"));
		q.setFolder(getQuestionFolder(bank, dirmap.get("章节")));
		q.setType(getQuestionType(bank, dirmap.get("题型"), dirmap.get("格式")));
		q.setLevel(QuestionLevel.getByName(dirmap.get("难易")));
		IQuestionFormat iqf = formatFactory.fromName(dirmap.get("格式"));
		if (iqf == null) {
			throw new Exception(dirmap.get("格式") + ":不是合法的试题格式名称！");
		}
		iqf.setProperties(q, map);
		questionDao.save(q);
		return dirmap;
	}
}
