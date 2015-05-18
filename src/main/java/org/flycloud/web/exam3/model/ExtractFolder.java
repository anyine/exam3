package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExtractFolder {
	@Id
	private String id;

	@ManyToOne
	private ExtractType type;

	@ManyToOne
	private QuestionFolder folder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QuestionFolder getFolder() {
		return folder;
	}

	public void setFolder(QuestionFolder folder) {
		this.folder = folder;
	}

	public ExtractType getExtractType() {
		return type;
	}

	public void setExtractType(ExtractType type) {
		this.type = type;
	}

}
