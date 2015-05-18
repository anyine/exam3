package org.flycloud.web.exam3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ExamPaperItem {
	@Id
	private String id;
	
	@ManyToOne
	private ExamPaper paper;
	
	@ManyToOne
	private QuestionType type;
	
	private String title;
	
	private Double itemScore;
	
	@ManyToMany
	private List<Question> questions = new ArrayList<Question>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExamPaper getPaper() {
		return paper;
	}

	public void setPaper(ExamPaper exam) {
		this.paper = exam;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getItemScore() {
		return itemScore;
	}

	public void setItemScore(Double itemScore) {
		this.itemScore = itemScore;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
