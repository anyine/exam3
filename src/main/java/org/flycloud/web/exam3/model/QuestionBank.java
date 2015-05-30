package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class QuestionBank {
	@Id
	private String id;

	@Column(unique = true)
	private String name;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "bank", fetch = FetchType.LAZY)
	private List<QuestionType> types = new ArrayList<QuestionType>();

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "bank", fetch = FetchType.LAZY)
	@OrderBy("serial ASC")
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

	public List<QuestionType> getTypes() {
		return types;
	}

	public void setTypes(List<QuestionType> types) {
		this.types = types;
	}

	public List<QuestionFolder> getFolders() {
		return folders;
	}

	public void setFolders(List<QuestionFolder> folders) {
		this.folders = folders;
	}

}
