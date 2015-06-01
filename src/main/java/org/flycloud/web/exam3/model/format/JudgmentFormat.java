package org.flycloud.web.exam3.model.format;

public class JudgmentFormat implements IQuestionFormat {

	@Override
	public String getName() {
		return "判断题";
	}

	@Override
	public boolean autoRating() {
		return true;
	}

}
