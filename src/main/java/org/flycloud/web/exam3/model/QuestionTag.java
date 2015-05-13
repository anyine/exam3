package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 试题标签
 * 
 * @author zhangbo
 *
 */
@Entity
public class QuestionTag {
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
