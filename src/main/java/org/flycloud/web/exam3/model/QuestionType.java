package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * 试题类型（不同的类型的显示格式可以相同）
 * 
 * @author zhangbo
 *
 */
@Entity
public class QuestionType {
	@Id
	private String name;

	@Enumerated(EnumType.STRING)
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
}
