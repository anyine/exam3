package org.flycloud.web.exam3.model.format;

import org.flycloud.web.exam3.model.Question;

public class MultipleChoiceFormat extends ChoiceFormat {

	@Override
	public void validate(Question q) throws Exception {
		this.validateAnswer(q);
	}

}
