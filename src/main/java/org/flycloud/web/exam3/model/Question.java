package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Question {
	@Id
	private String id;

	@ManyToOne
	private QuestionFolder folder;

	@Enumerated(EnumType.ORDINAL)
	private QuestionLevel level;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();

	@ManyToOne
	private QuestionType type;

	@ManyToMany
	private List<QuestionTag> tags = new ArrayList<QuestionTag>();

	@ElementCollection(targetClass = java.lang.String.class)
	@MapKeyColumn(name = "k")
	@Column(name = "v", length = 1024 * 1024)
	@CollectionTable(name = "property", joinColumns = @JoinColumn(name = "id"))
	private Map<String, String> properties = new HashMap<String, String>();

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

	public QuestionLevel getLevel() {
		return level;
	}

	public void setLevel(QuestionLevel level) {
		this.level = level;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
