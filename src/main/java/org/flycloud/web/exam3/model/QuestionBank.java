package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionBank {
	@Id
	private String id;

	@Column(unique = true)
	private String name;

	@OneToMany
	private List<QuestionType> types = new ArrayList<QuestionType>();

	@OneToMany
	private List<QuestionFolder> folders = new ArrayList<QuestionFolder>();
	
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

}
