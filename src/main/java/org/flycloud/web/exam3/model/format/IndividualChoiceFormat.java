package org.flycloud.web.exam3.model.format;

public class IndividualChoiceFormat implements IQuestionFormat {

	@Override
	public String getName() {
		return "单项选择题";
	}

	@Override
	public boolean autoRating() {
		return true;
	}

}
