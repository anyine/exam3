package org.flycloud.web.exam3.model.format;

public class BlankFillingFormat implements IQuestionFormat {

	@Override
	public String getName() {
		return "填空题";
	}

	@Override
	public boolean autoRating() {
		return false;
	}

}
