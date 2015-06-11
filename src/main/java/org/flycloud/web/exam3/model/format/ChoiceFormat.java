package org.flycloud.web.exam3.model.format;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.flycloud.web.exam3.model.Question;

public abstract class ChoiceFormat implements IQuestionFormat {

	protected static String getChoiceAnswer(String answer) {
		answer = answer.toUpperCase().replaceAll("[^A-Z]", "");
		if (answer.length() == 0) {
			return null;
		}
		Set<String> as = new HashSet<String>();
		for (int i = 0; i < answer.length(); i++) {
			as.add(answer.substring(i, i + 1));
		}
		String[] answers = as.toArray(new String[0]);
		Arrays.sort(answers);
		answer = Arrays.toString(answers).replaceAll("[^A-Z]", "");
		return answer;
	}

	@Override
	public boolean autoRating() {
		return true;
	}
	
	protected void validateAnswer(Question q) throws Exception {
		String a = q.getProperties().get("答案");
		for(char x : a.toCharArray()) {
			if (!q.getProperties().containsKey(""+x)) {
				throw new Exception("答案中的选项不存在:"+x);
			}
		}
	}

	@Override
	public void setProperties(Question q, Map<String, String> map)
			throws Exception {
		Map<String, String> properties = q.getProperties();
		properties.clear();
		properties.put("题干", map.remove("题干"));
		properties.put("答案", getChoiceAnswer(map.remove("答案")));
		String xx = map.remove("选项");
		if (xx != null && xx.trim().length() > 8) {
			String[] xxes = xx.trim().split(";");
			char c = 'A';
			for (String x : xxes) {
				properties.put("" + c, x.trim());
				c++;
			}
		}
		for (String k : map.keySet()) {
			properties.put(k, map.get(k));
		}
		this.validate(q);
	}

}
