package org.flycloud.web.exam3.model.format;

import java.util.Map;

import org.flycloud.web.exam3.model.Question;

public class JudgmentFormat implements IQuestionFormat {
	private static String getJudgmentAnswer(String answer) {
		answer = answer.toUpperCase();
		if ((answer.contains("错误") && !answer.contains("不错误"))
				|| answer.contains("RIGHT") || answer.contains("A")
				|| answer.contains("X")
				|| (answer.contains("错") && !answer.contains("不错"))
				|| answer.contains("不正确") || answer.contains("不对")) {
			return "错误";
		} else if ((answer.contains("正确") && !answer.contains("不正确"))
				|| answer.contains("WRONG") || answer.contains("B")
				|| answer.contains("V")
				|| (answer.contains("对") && !answer.contains("不对"))
				|| answer.contains("不错误") || answer.contains("不错")) {
			return "正确";
		} else {
			return null;
		}
	}
	
	@Override
	public boolean autoRating() {
		return true;
	}

	@Override
	public void setProperties(Question q, Map<String, String> map) {
		Map<String, String> properties = q.getProperties();
		properties.clear();
		properties.put("题干", map.get("题干"));
		properties.put("答案", getJudgmentAnswer(map.get("答案")));
	}

	@Override
	public void validate(Question q) throws Exception {
		
	}

}
