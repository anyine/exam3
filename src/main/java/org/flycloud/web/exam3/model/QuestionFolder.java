package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class QuestionFolder {

	@Id
	private String id;

	private String name;

	private Long serial;

	@ManyToOne
	private QuestionBank bank;
	
	@ManyToOne
	private QuestionFolder parent;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("serial ASC")
	private List<QuestionFolder> children = new ArrayList<QuestionFolder>();

	public QuestionFolder getParent() {
		return parent;
	}

	public void setParent(QuestionFolder parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public QuestionBank getBank() {
		return bank;
	}

	public void setBank(QuestionBank bank) {
		this.bank = bank;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<QuestionFolder> getChildren() {
		return children;
	}

	public void setChildren(List<QuestionFolder> children) {
		this.children = children;
	}

}
