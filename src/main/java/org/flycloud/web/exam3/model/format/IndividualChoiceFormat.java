package org.flycloud.web.exam3.model.format;

import org.flycloud.web.exam3.model.Question;

public class IndividualChoiceFormat extends ChoiceFormat {

	@Override
	public void validate(Question q) throws Exception {
		String string = q.getProperties().get("答案");
		if (string.length() != 1) {
			String qs = q.getProperties().get("题干");
			throw new Exception("单项选择题《"+qs+"》答案应该只有一个选项:<"+string+">");
		}
	}

}
