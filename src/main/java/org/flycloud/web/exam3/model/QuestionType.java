package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 试题类型（不同的类型的显示格式可以相同）
 * 
 * @author zhangbo
 *
 */
@Entity
public class QuestionType {
	@Id
	private String id;

	@ManyToOne
	private QuestionBank bank;

	private String name;

	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QuestionBank getBank() {
		return bank;
	}

	public void setBank(QuestionBank bank) {
		this.bank = bank;
	}

}
