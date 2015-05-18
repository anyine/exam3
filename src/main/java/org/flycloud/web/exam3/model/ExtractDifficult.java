package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExtractDifficult {
	@Id
	private String id;

	@ManyToOne
	private ExtractFolder folder;

	private Double startLevel = Double.NEGATIVE_INFINITY;
	
	private Double endLevel = Double.POSITIVE_INFINITY;
	
	private Long number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExtractFolder getFolder() {
		return folder;
	}

	public void setFolder(ExtractFolder folder) {
		this.folder = folder;
	}

	public Double getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(Double startLevel) {
		this.startLevel = startLevel;
	}

	public Double getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Double endLevel) {
		this.endLevel = endLevel;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}



}
