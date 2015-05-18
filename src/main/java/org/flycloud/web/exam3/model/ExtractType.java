package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ExtractType {
	@Id
	private String id;

	@ManyToOne
	private ExtractMethod method;

	@ManyToOne
	private QuestionType type;
	
	@OneToMany
	private List<ExtractFolder> folders = new ArrayList<ExtractFolder>();

	private Double itemScore;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExtractMethod getMethod() {
		return method;
	}

	public void setMethod(ExtractMethod method) {
		this.method = method;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Double getItemScore() {
		return itemScore;
	}

	public void setItemScore(Double score) {
		this.itemScore = score;
	}

	public List<ExtractFolder> getFolders() {
		return folders;
	}

	public void setFolders(List<ExtractFolder> folders) {
		this.folders = folders;
	}

}
