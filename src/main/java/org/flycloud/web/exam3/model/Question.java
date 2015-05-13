package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	private String id;

	@ManyToOne
	private QuestionFolder folder;

	@ManyToOne
	private QuestionType type;

	@ManyToMany
	private List<QuestionTag> tags = new ArrayList<QuestionTag>();

	@OneToMany
	private List<Resource> resources = new ArrayList<Resource>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	private Integer difficult;

	public List<QuestionTag> getTags() {
		return tags;
	}

	public void setTags(List<QuestionTag> tags) {
		this.tags = tags;
	}

	public QuestionFolder getFolder() {
		return folder;
	}

	public void setFolder(QuestionFolder folder) {
		this.folder = folder;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Integer getDifficult() {
		return difficult;
	}

	public void setDifficult(Integer difficult) {
		this.difficult = difficult;
	}
}
