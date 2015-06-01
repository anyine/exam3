package org.flycloud.web.exam3.model.format;

import java.util.HashMap;
import java.util.Map;

public class QuestionFormatFactory {
	Map<String, IQuestionFormat> formats = new HashMap<String, IQuestionFormat>();

	public Map<String, IQuestionFormat> getFormats() {
		return formats;
	}

	public void setFormats(Map<String, IQuestionFormat> formats) {
		this.formats = formats;
	}

	public IQuestionFormat fromName(String name) {
		return formats.get(name);
	}
}
