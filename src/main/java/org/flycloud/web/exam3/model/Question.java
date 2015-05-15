package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Question {
	@Id
	private String id;

	@ManyToOne
	private QuestionFolder folder;

	private Double difficult;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();

	@ManyToOne
	private QuestionType type;

	@ManyToMany
	private List<QuestionTag> tags = new ArrayList<QuestionTag>();

	@OneToMany
	private List<Resource> resources = new ArrayList<Resource>();
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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

	public Double getDifficult() {
		return difficult;
	}

	public void setDifficult(Double difficult) {
		this.difficult = difficult;
	}
}
