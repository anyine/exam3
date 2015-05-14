package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionBank {
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
