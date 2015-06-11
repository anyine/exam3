package org.flycloud.web.exam3.model.format;

import java.util.Map;

import org.flycloud.web.exam3.model.Question;

public class ShortAnswerFormat implements IQuestionFormat {

	@Override
	public boolean autoRating() {
		return false;
	}

	@Override
	public void setProperties(Question q, Map<String, String> map) {
		Map<String, String> properties = q.getProperties();
		properties.clear();
		properties.put("题干", map.get("题干"));
		properties.put("答案", map.get("答案"));
	}

	@Override
	public void validate(Question q) throws Exception {
		
	}

}
