package org.flycloud.web.exam3.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnswerUtil {

	public static String getJudgmentAnswer(String answer) {
		answer = answer.toUpperCase();
		if ((answer.contains("错误") && !answer.contains("不错误"))
				|| answer.contains("RIGHT")
				|| answer.contains("A")
				|| answer.contains("X")
				|| (answer.contains("错") && !answer.contains("不错"))
				|| answer.contains("不正确") || answer.contains("不对")) {
			return "错误";
		} else if ((answer.contains("正确") && !answer.contains("不正确"))
				|| answer.contains("WRONG")
				|| answer.contains("B")
				|| answer.contains("V")
				|| (answer.contains("对") && !answer.contains("不对"))
				|| answer.contains("不错误") || answer.contains("不错")) {
			return "正确";
		} else {
			return null;
		}
	}

	public static String getChoiceAnswer(String answer) {
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

	public static void main(String[] args) {
		System.out.println(AnswerUtil.getChoiceAnswer(" 可能是 CCAcdD "));

		System.out.println(AnswerUtil.getJudgmentAnswer("正确"));
		System.out.println(AnswerUtil.getJudgmentAnswer("V"));
		System.out.println(AnswerUtil.getJudgmentAnswer("对"));
		System.out.println(AnswerUtil.getJudgmentAnswer("不错"));
		System.out.println(AnswerUtil.getJudgmentAnswer("不错误"));

		System.out.println(AnswerUtil.getJudgmentAnswer("错误"));
		System.out.println(AnswerUtil.getJudgmentAnswer("X"));
		System.out.println(AnswerUtil.getJudgmentAnswer("错"));
		System.out.println(AnswerUtil.getJudgmentAnswer("不对"));
		System.out.println(AnswerUtil.getJudgmentAnswer("不正确"));
	}
}
