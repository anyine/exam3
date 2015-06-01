package org.flycloud.web.exam3.model.format;

public class MultipleChoiceFormat implements IQuestionFormat {

	@Override
	public String getName() {
		return "多项选择题";
	}

	@Override
	public boolean autoRating() {
		return true;
	}

}
