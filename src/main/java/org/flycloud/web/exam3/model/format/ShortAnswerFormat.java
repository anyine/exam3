package org.flycloud.web.exam3.model.format;

public class ShortAnswerFormat implements IQuestionFormat {

	@Override
	public String getName() {
		return "简答题";
	}

	@Override
	public boolean autoRating() {
		return false;
	}

}
