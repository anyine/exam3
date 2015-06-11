package org.flycloud.web.exam3.model.format;

import java.util.Map;

import org.flycloud.web.exam3.model.Question;

public interface IQuestionFormat {

	/**
	 * 能否自动评分
	 * 
	 * @return
	 */
	public boolean autoRating();

	public void setProperties(Question q,
			Map<String, String> map) throws Exception;
	
	public void validate(Question q) throws Exception;

}
