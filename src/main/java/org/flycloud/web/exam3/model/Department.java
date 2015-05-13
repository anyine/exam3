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
public class Department {
	@Id
	private String id;
	private String name;
	private Long serial;
	private String remark;

	@ManyToOne
	private Department parent;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("serial ASC")
	private List<Department> children = new ArrayList<Department>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	public List<Department> getChildren() {
		return children;
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

}
