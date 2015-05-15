package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Enumerated(EnumType.ORDINAL)
	private QuestionFormat format;

	private Boolean subjective;

	public QuestionFormat getFormat() {
		return format;
	}

	public void setFormat(QuestionFormat format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSubjective() {
		return subjective;
	}

	public void setSubjective(Boolean subjective) {
		this.subjective = subjective;
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
