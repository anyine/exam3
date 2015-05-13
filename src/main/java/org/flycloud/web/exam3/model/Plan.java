package org.flycloud.web.exam3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 抽题方案
 * 
 * @author zhangbo
 *
 */
@Entity
public class Plan {
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
