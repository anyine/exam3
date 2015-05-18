package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ExtractMethod {
	@Id
	private String id;
	
	@ManyToOne
	private QuestionBank bank;
	
	@OneToMany
	private List<ExtractType> types = new ArrayList<ExtractType>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QuestionBank getBank() {
		return bank;
	}

	public void setBank(QuestionBank bank) {
		this.bank = bank;
	}

	public List<ExtractType> getTypes() {
		return types;
	}

	public void setTypes(List<ExtractType> types) {
		this.types = types;
	}
}
